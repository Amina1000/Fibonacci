package com.example.lesson1.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import java.util.*


class BoundService : Service() {

    // Для связывания Activity и сервиса
    private val binder: IBinder = ServiceBinder()

    // Random number generator
    private val mGenerator = Random()

    /** method for clients  */
    val randomNumber: Int
        get() = mGenerator.nextInt(100)

    // Вызывается только один раз, при создании сервиса
    override fun onBind(intent: Intent?): IBinder {
        return binder
    }

    // Класс связи между клиентом и сервисом
    inner class ServiceBinder : Binder() {
        val service: BoundService
            get() = this@BoundService
    }

}