package com.rafdev.calculadora.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.rafdev.calculadora.databinding.ActivityMainBinding
import com.rafdev.calculadora.ui.adapter.MainAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
//        val input = listOf("5", "*", "3", "+", "8", "/", "2", "-", "3")
//
//        viewModel.performCalculation(input)
    }

    private fun initUI() {
        observers()
    }

    private fun observers() {
        viewModel.dataButton.observe(this) {
            binding.rvButtons.apply {
                layoutManager = GridLayoutManager(context, 4).apply {
                    spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                        override fun getSpanSize(position: Int): Int {
                            // Asumiendo que la cantidad de elementos es suficiente para llenar las filas
                            // Si es el último ítem y el total de ítems no es múltiplo de 4
                            return if (position == adapter!!.itemCount - 1 && adapter!!.itemCount % 4 != 0) {
                                2 // Ocupar dos espacios
                            } else {
                                1 // Ocupar un espacio
                            }
                        }
                    }
                }
                val homeAdapter = MainAdapter(it) {
                    viewModel.onButtonClicked(it)
                }
                adapter = homeAdapter

            }
        }
        viewModel.displayValue.observe(this) {
            addTextInScreen(it)
        }
        viewModel.resultData.observe(this) {
            addResultInTextView(it)
        }
    }

    private fun addResultInTextView(it: String) {
        binding.viewResult.text = it
        Log.i("jugando", "resuktadi$it")
    }

    private fun addTextInScreen(values: List<String>?) {
        binding.viewOperation.text = values?.joinToString(separator = "") ?: ""
    }
}