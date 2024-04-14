package com.rafdev.calculadora.ui.adapter

import android.view.View
import android.widget.LinearLayout
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
            "C" -> binding.floatingButton.setTextColor(ContextCompat.getColor(context, R.color.accent))
            "+/-" -> binding.floatingButton.setTextColor(ContextCompat.getColor(context, R.color.accent))
            "%" -> binding.floatingButton.setTextColor(ContextCompat.getColor(context, R.color.accent))
            "=" -> binding.floatingButton.apply {
                setTextColor(ContextCompat.getColor(context, R.color.white))
                setBackgroundResource(R.drawable.rounded_button_equal)
                val layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT
                )
                layoutParams.setMargins(30, 5, 20, 5)
                binding.floatingButton.layoutParams = layoutParams
            }

        }
    }

}