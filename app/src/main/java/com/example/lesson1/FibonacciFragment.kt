package com.example.lesson1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


private const val FIB_NUM = "fib_num"

class FibonacciFragment : Fragment() {

    private var fibNum = 0
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
    ): View {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_fibonachi, container, false)
        initRecyclerView(view)
        return view
    }

    private fun initRecyclerView(view:View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view_fib)
        recyclerView.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        adapter = FibAdapter(fibNum)
        recyclerView.adapter = adapter
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