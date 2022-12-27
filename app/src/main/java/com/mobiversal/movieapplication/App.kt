package com.mobiversal.movieapplication

import android.app.Application
import com.mobiversal.movieapplication.dataBase.DataBase

class App:Application() {
    override fun onCreate() {
        super.onCreate()
        DataBase.instance.initialise(this)
    }
}
