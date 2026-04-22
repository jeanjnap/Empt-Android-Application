package com.example.exemple.payment.x990.printer

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.graphics.Bitmap
import android.os.Bundle
import android.os.IBinder
import android.os.SystemClock
import androidx.core.graphics.scale
import com.vfi.smartpos.deviceservice.aidl.IDeviceService
import com.vfi.smartpos.deviceservice.aidl.IPrinter
import com.vfi.smartpos.deviceservice.aidl.PrinterListener
import printx990.br.apix990.internal.domain.dto.POSITIONIMAGE
import printx990.br.apix990.internal.domain.dto.PrinterData
import printx990.br.apix990.internal.domain.dto.PrinterImageData
import java.io.ByteArrayOutputStream

class SipagX990PrinterManager(context: Context) {

    private var deviceService: IDeviceService? = null
    private var printer: IPrinter? = null
    private var isServiceRunning = false

    init {
        bindDeviceService(context) { isPrinterReady ->
            isServiceRunning = isPrinterReady.first
        }
    }

    fun printBitmap(bitmap: Bitmap) {
        while (isServiceRunning.not()) {
            waitSdk()
        }

        val printerData = PrinterData(
            null,
            PrinterImageData(
                image = null,
                imageBitmap = bitmap,
                position = POSITIONIMAGE.CENTER,
                sizeImage = QUALITY,
                bottomSpace = BOTTOM_SPACE
            )
        )

        val offset = when (printerData.printerImageData?.position) {
            POSITIONIMAGE.LEFT -> ZERO
            POSITIONIMAGE.CENTER -> (PRINTABLE_AREA - bitmap.width) / HALF_SIZE
            POSITIONIMAGE.RIGHT -> PRINTABLE_AREA - bitmap.width
            else -> ZERO
        }

        val scaleWidth = minOf(bitmap.width, bitmap.width)
        val scaleHeight = (bitmap.height * scaleWidth.toFloat() / bitmap.width).toInt()
        val scaledBitmap = bitmap.scale(scaleWidth, scaleHeight)

        val stream = ByteArrayOutputStream()
        scaledBitmap.compress(Bitmap.CompressFormat.PNG, QUALITY, stream)
        val imageData = stream.toByteArray()

        val imageFormat = Bundle().apply {
            putInt(OFFSET, offset)
            putInt(WIDTH, scaleWidth)
            putInt(HEIGHT, scaleHeight)
            putInt(GRAY, GRAY_TONE)
        }
        printer?.addImage(imageFormat, imageData)
        printer?.startPrint(object : PrinterListener.Stub() {
            override fun onFinish() = Unit

            override fun onError(error: Int) {
                throw Throwable(SipagX990PrinterError.fromCode(error).message)
            }
        })
    }

    private fun bindDeviceService(context: Context, callback: (Pair<Boolean, String>) -> Unit) {
        val intent = Intent()
        intent.setAction(SERVICE_ACTION)
        intent.setPackage(SERVICE_PACKAGE)

        val serviceConnection = object : ServiceConnection {
            override fun onServiceConnected(componentName: ComponentName, iBinder: IBinder) {
                deviceService = IDeviceService.Stub.asInterface(iBinder)
                if (deviceService != null) {
                    runCatching {
                        printer = deviceService?.printer
                        val status = checkPrinterStatus(printer)
                        callback(status)
                    }.getOrElse {
                        it.printStackTrace()
                        callback(Pair(false, it.message.toString()))
                    }
                } else {
                    callback(Pair(false, SipagX990PrinterError.ERROR_NOT_CONNECTED.message))
                }
            }

            override fun onServiceDisconnected(componentName: ComponentName) {
                callback(Pair(false, SipagX990PrinterError.ERROR_SERVICE_DISCONNECTED.message))
            }
        }

        context.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
    }

    private fun checkPrinterStatus(printer: IPrinter?): Pair<Boolean, String> {
        return runCatching {
            val status = printer?.status
            if (status == ZERO) {
                Pair(true, EMPTY)
            } else {
                Pair(false, SipagX990PrinterError.fromCode(printer?.status).message)
            }
        }.getOrElse {
            it.printStackTrace()
            Pair(false, it.message.orEmpty())
        }
    }

    private fun waitSdk() {
        SystemClock.sleep(WAIT_TIME)
    }


    private companion object {
        const val WAIT_TIME = 100L
        const val QUALITY = 100
        const val ZERO = 0
        const val BOTTOM_SPACE = 1
        const val HALF_SIZE = 2
        const val PRINTABLE_AREA = 384
        const val EMPTY = ""
        const val SERVICE_ACTION = "com.vfi.smartpos.device_service"
        const val SERVICE_PACKAGE = "com.vfi.smartpos.deviceservice"
        const val OFFSET = "offset"
        const val WIDTH = "width"
        const val HEIGHT = "height"
        const val GRAY = "gray"
        const val GRAY_TONE = 128
    }
}