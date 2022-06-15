package com.example.alexpetrov

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class MainApp : Application() {
    @SuppressLint("StaticFieldLeak")
    object ContextHolder {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        ContextHolder.context = this
    }
}