package com.example.lesson1.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.annotation.RequiresApi
import com.example.lesson1.R


/**
 * homework com.example.lesson1.service
 *
 * @author Amina
 * 30.07.2021
 */
class ForegroundService: Service(){
    private var messageId = 1000
    private var factorial: Int = 1
    // Признак прекращения расчёта
    private var running = false
    private val CHANNEL_ID = "ForegroundService Kotlin"
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // Для такого сервиса надо обязательно вызвать метод startForeground
        startForeground(messageId++, makeNotification("Foreground Service"))
        createNotificationChannel()
        val running = true
        // Делаем тяжёлую работу в потоке
        Thread {
            while (running) {
                try {
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                factorial *= (factorial++)
                val notification: Notification =
                    makeNotification("Next factorial $factorial")
                val notificationManager =
                    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.notify(messageId, notification)
            }
        }.start()
        return START_NOT_STICKY

    }

    override fun onDestroy() {
        super.onDestroy()
        running =false
    }

    // Вывод уведомления в строке состояния
    @RequiresApi(Build.VERSION_CODES.O)
    private fun makeNotification(message: String): Notification {
        return Notification.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_baseline_calculate_24)
            .setContentTitle("Main service notification")
            .setContentText(message)
            .build()
    }
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(CHANNEL_ID, "Foreground Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT)
            val manager = getSystemService(NotificationManager::class.java)
            manager!!.createNotificationChannel(serviceChannel)
        }
    }
}