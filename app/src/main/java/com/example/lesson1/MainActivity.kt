package com.example.lesson1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {
    private var button: Button? = null
    private var fibNum: EditText?= null
    private var fibNumInt =0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fibNum  = findViewById(R.id.fibonacci_number)
        button = findViewById(R.id.btnLesson1)
        button?.setOnClickListener{
            val manager: FragmentManager = supportFragmentManager
            val transaction: FragmentTransaction = manager.beginTransaction()
            fibNum?.let {
                fibNumInt =it.text.toString().toInt()
            }
            transaction.replace(R.id.main_container, FibonacciFragment.newInstance(fibNumInt))
            transaction.commit()
        }
    }
}