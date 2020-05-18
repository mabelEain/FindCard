package com.android.findcardgame.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.findcardgame.R
import kotlinx.android.synthetic.main.card_front.view.*


class RecyclerViewAdapter( private val numberList: List<String>, private val randomList: List<Int>, private val callback: Listener) : RecyclerView.Adapter<CardViewHolder>() {

    interface Listener {
        fun onClickItem(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_item, parent, false)
        val height = parent.measuredHeight / 4
        itemView.txt_front.minimumHeight = height
        return CardViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
       holder.updateCardItem(numberList[position], randomList[position], this.callback)
    }


    override fun getItemCount(): Int {
        return numberList.size
    }
}
