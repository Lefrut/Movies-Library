package ru.dashkevich.viewapp.utility.log

import android.util.Log

const val FRAGMENT = "Fragment"
const val ACTIVITY = "Activity"

fun logD(tag: String, message: String) = Log.d(tag, message)

fun logI(tag: String, message: String) = Log.i(tag, message)

fun logE(tag: String, message: String) = Log.e(tag, message)


