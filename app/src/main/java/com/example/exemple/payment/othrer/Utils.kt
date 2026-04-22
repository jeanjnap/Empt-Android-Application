package com.example.exemple.payment.othrer

import com.google.gson.GsonBuilder
import java.util.Date
import java.util.Locale

inline fun <reified T> Any?.cast() = takeIf { this is T }?.let { this as T }

fun Double.toStringOnlyNumbers(): String {
    val formatted = String.format(Locale.getDefault(), "%.2f", this)
    return formatted.replace("[^\\d]".toRegex(), "")
}

inline fun <reified T> String.optionalObjectOf(): T? {
    runCatching {
        val builder = GsonBuilder()
        val gson = builder.create()
        //val gson = builder.registerTypeAdapter(Date::class.java, UtcDateTypeAdapterLegacy()).create()
        return gson.fromJson(this, T::class.java)
    }.onFailure {
        it.printStackTrace()
    }
    return null
}