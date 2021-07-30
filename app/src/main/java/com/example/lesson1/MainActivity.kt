package com.example.lesson1

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.lesson1.service.BoundService
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private var button: Button? = null
    private lateinit var buttonService: Button
    private var fibNum: EditText? = null
    private var fibNumInt = 0
    private lateinit var mService: BoundService
    private var mBound: Boolean = false

    /** Defines callbacks for service binding, passed to bindService()  */
    //Требуется предоставить реализацию интерфейса ServiceConnection для
    // отслеживания подключения к сервису. После подключения к сервису
    // вызывается метод onServiceConnected() этого интерфейса, в котором
    // мы и подключаемся к новому сервису. В качестве параметра этого метода
    // выступает интерфейс IBinder, его надо реализовать в самом сервисе.
    // Общение с сервисом будет происходить через этот интерфейс.

    private val connection = object : ServiceConnection {

        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            val binder = service as BoundService.ServiceBinder
            mService = binder.service
            mBound = true
        }

        override fun onServiceDisconnected(arg0: ComponentName) {
            mBound = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()

    }

    private fun initView() {
        fibNum = findViewById(R.id.fibonacci_number)
        button = findViewById(R.id.btnLesson1)
        button?.setOnClickListener {
            fibNum?.let {
                try {
                    fibNumInt = it.text.toString().toInt()
                } catch (e: Exception) {
                    Toast.makeText(this, "Число указано некорректно", Toast.LENGTH_SHORT).show()
                }
            }
            addFragment(fibNumInt)
        }

        buttonService = findViewById(R.id.btnLesson6)
        buttonService.setOnClickListener {
            if (mBound) {
                // Вызываем мотода сервиса BoundService.
                // Однако, если бы этот вызов был чем-то, что могло бы зависнуть, то этот запрос должен
                // происходит в отдельном потоке, чтобы избежать замедления производительности
                val num: Int = mService.randomNumber
                addFragment(num)
            }
        }
    }

    private fun addFragment(num: Int) {
        val manager: FragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = manager.beginTransaction()
        transaction.replace(R.id.main_container, FibonacciFragment.newInstance(num))
        transaction.commit()
    }

    override fun onStart() {
        super.onStart()
        // Bind to LocalService
        // Чтобы привязать сервис к клиенту (другой части приложения), надо вызвать метод bindService().
        Intent(this, BoundService::class.java).also { intent ->
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
    }

    override fun onStop() {
        super.onStop()
        unbindService(connection)
        mBound = false
    }
}