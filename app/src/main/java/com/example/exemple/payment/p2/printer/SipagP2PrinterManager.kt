package com.example.exemple.payment.p2.printer

import android.content.Context
import android.graphics.Bitmap
import android.os.RemoteException
import android.os.SystemClock
import com.sunmi.peripheral.printer.InnerPrinterCallback
import com.sunmi.peripheral.printer.InnerPrinterManager
import com.sunmi.peripheral.printer.InnerResultCallback
import com.sunmi.peripheral.printer.SunmiPrinterService

internal class SipagP2PrinterManager(private val context: Context) {
    private var finishedPrinting = false
    private var sunmiPrinterService: SunmiPrinterService? = null
    private var isModuleInitialized = false

    init {
        runCatching {
            bindPrintService()
        }.getOrElse {
            it.printStackTrace()
            sunmiPrinterService = null
        }
    }

    @Throws(Exception::class)
    fun printImage(image: Bitmap?) {
        runCatching {
            if (isModuleInitialized) {
                finishedPrinting = false
                when (val state = SipagP2PrinterState.fromCode(sunmiPrinterService?.updatePrinterState())) {
                    SipagP2PrinterState.NORMAL,
                    SipagP2PrinterState.PREPARING -> Unit

                    SipagP2PrinterState.NO_PAPER -> {
                        throw Throwable(SipagP2PrinterState.NO_PAPER.name)
                    }

                    SipagP2PrinterState.OVERHEATED,
                    SipagP2PrinterState.COMMUNICATION_ERROR,
                    SipagP2PrinterState.LID_OPEN,
                    SipagP2PrinterState.CUTTER_ERROR,
                    SipagP2PrinterState.CUTTER_RECOVERED,
                    SipagP2PrinterState.NO_BLACK_MARK -> {
                        throw Throwable(SipagP2PrinterState.fromCode(state.code).name)
                    }

                    SipagP2PrinterState.UNKNOWN -> {
                        throw Throwable(SipagP2PrinterState.UNKNOWN.name)
                    }
                }

                sunmiPrinterService?.run {
                    enterPrinterBuffer(true)
                    printBitmap(image, null)
                    lineWrap(WRAP_LINES, null)

                    exitPrinterBufferWithCallback(true, object : InnerResultCallback() {
                        @Throws(RemoteException::class)
                        override fun onRunResult(isSuccess: Boolean) {
                            finishedPrinting = true
                        }

                        @Throws(RemoteException::class)
                        override fun onReturnString(result: String?) {
                            finishedPrinting = true
                        }

                        @Throws(RemoteException::class)
                        override fun onRaiseException(code: Int, msg: String?) {
                            finishedPrinting = true
                        }

                        @Throws(RemoteException::class)
                        override fun onPrintResult(code: Int, msg: String?) {
                            finishedPrinting = true
                        }
                    })
                }

                while (!finishedPrinting) {
                    waitSdk()
                }
            } else {
                waitSdk()
                printImage(image)
            }
        }.getOrElse {
            it.printStackTrace()
        }
    }

    private fun bindPrintService() {
        runCatching {
            if (!isModuleInitialized) {
                sunmiPrinterService = null
                InnerPrinterManager.getInstance().bindService(context, object : InnerPrinterCallback() {
                    override fun onConnected(service: SunmiPrinterService?) {
                        isModuleInitialized = true
                        sunmiPrinterService = service
                    }

                    override fun onDisconnected() {
                        isModuleInitialized = false
                        sunmiPrinterService = null
                    }
                })
            }
        }.getOrElse {
            it.printStackTrace()
        }
    }

    private fun waitSdk() {
        SystemClock.sleep(WAIT_TIME)
    }

    private companion object {
        const val WAIT_TIME = 100L
        const val WRAP_LINES = 2
    }
}
