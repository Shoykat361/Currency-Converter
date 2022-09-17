package com.example.currencyconverter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter

import androidx.fragment.app.viewModels

import com.example.currencyconverter.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: MainViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        viewModel.getResult().observe(viewLifecycleOwner) {
            binding.showMoney.text = String.format("%.2f", it)
        }
        val adapter = ArrayAdapter<String>(
            requireActivity(), android.R.layout.simple_spinner_dropdown_item,
            viewModel.rates.keys.toList()
        )
        binding.spinnerMenu.adapter = adapter
        binding.spinnerMenu.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    val value = binding.moneyInput.text.toString().toDouble()
                    viewModel.rate = p0!!.getItemAtPosition(p2).toString()
                    viewModel.calculate(value)
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {

                }

            }
          binding.Btn.setOnClickListener {
            val value = binding.moneyInput.text.toString().toDouble()
            viewModel.calculate(value)
        }
        return binding.root
    }

}