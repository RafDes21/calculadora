package com.rafdev.calculadora.ui.adapter

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.rafdev.calculadora.R
import com.rafdev.calculadora.data.local.response.ResponseDataButton
import com.rafdev.calculadora.databinding.ButtonBinding

class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ButtonBinding.bind(view)
    fun render(item: ResponseDataButton) {
        binding.floatingButton.text = item.text
        val context = itemView.context
        when (item.text) {
            "+" -> binding.floatingButton.setTextColor(ContextCompat.getColor(context, R.color.accent))
            "-" -> binding.floatingButton.setTextColor(ContextCompat.getColor(context, R.color.accent))
            "*" -> binding.floatingButton.setTextColor(ContextCompat.getColor(context, R.color.accent))
            "/" -> binding.floatingButton.setTextColor(ContextCompat.getColor(context, R.color.accent))
            "=" -> binding.floatingButton.apply {
                setTextColor(ContextCompat.getColor(context, R.color.white))
                setBackgroundResource(R.drawable.rounded_button_equal)
            }
            else -> binding.floatingButton.setTextColor(ContextCompat.getColor(context, R.color.black))
        }
    }

}