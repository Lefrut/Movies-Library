package ru.dashkevich.viewapp.util.ui

import android.content.Context
import android.widget.Toast


fun toast(message: String, context: Context) = Toast.makeText(context, message, Toast.LENGTH_SHORT).show()