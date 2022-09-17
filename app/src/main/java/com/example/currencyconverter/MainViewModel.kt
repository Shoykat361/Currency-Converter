package com.example.currencyconverter


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel:ViewModel() {
    val rates = mapOf("USD" to 86.32, "EUR" to 93.88, "GBP" to 112.37,
    "AUD" to 64.67,"SEK" to 8.8, "INR" to 1.19)
    var rate = rates.keys.first()
    private val result : MutableLiveData<Double> = MutableLiveData()

    fun getResult() : LiveData<Double> = result

    fun calculate(amount: Double) {
        rates[rate]?.run {
            val r = amount.times(this)
            result.value = r
        } ?: throw Exception("Invalid value")
    }
}