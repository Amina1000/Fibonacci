package com.example.lesson1

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


private const val FIB_NUM = "fib_num"

class FibonacciFragment : Fragment() {

    private var fibNum: Int = 0
    private val recyclerView: RecyclerView? = null
    private var adapter: FibAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            fibNum = it.getInt(FIB_NUM)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fibonachi, container, false)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        recyclerView!!.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        adapter = FibAdapter(fibNum)
        recyclerView.setAdapter(adapter)
    }

    companion object {
        @JvmStatic
        fun newInstance(fibNum: Int) =
            FibonacciFragment().apply {
                arguments = Bundle().apply {
                    putInt(FIB_NUM, fibNum)
                }
            }
    }
}