package com.example.lesson1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * homework com.example.notes
 *
 * @author Amina
 * 11.06.2021
 */
class FibAdapter(fibNum:Int) :
    RecyclerView.Adapter<FibAdapter.BaseViewHolder>() {

    private var fibList: MutableList<Int> =mutableListOf()

    init {
        fibList = addNamToListFib(fibNum)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): BaseViewHolder {
        // Создаём новый элемент пользовательского интерфейса
        // Через Inflater
        val vNote: View = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item, viewGroup, false)
        // Здесь можно установить всякие параметры
        return BaseViewHolder(vNote)
    }

    override fun onBindViewHolder(viewHolder: BaseViewHolder, i: Int) {
        // Получить элемент из источника данных (БД, интернет...)
        // Вынести на экран, используя ViewHolder
        viewHolder.setData(fibList, i)
    }

    // Вернуть размер данных, вызывается менеджером
    override fun getItemCount(): Int {
        return fibList.size
    }

    class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTV: TextView = itemView.findViewById(R.id.item_number)
        fun setData(fibList: MutableList<Int>,position: Int) {
            val fibRes: Int = fibList.get(position)
            titleTV.text=fibRes.toString()
        }
    }
}