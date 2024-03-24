package com.rafdev.calculadora.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rafdev.calculadora.R
import com.rafdev.calculadora.data.local.response.ResponseDataButton

class MainAdapter(private val itemButtons:List<ResponseDataButton>) : RecyclerView.Adapter<MainViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val layout = LayoutInflater.from(parent.context)
        return MainViewHolder(layout.inflate(R.layout.button, parent, false))
    }

    override fun getItemCount(): Int = itemButtons.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.render(itemButtons[position])
    }
}