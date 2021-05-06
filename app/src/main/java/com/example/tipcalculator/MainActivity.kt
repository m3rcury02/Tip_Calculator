package com.example.tipcalculator

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calcButton.setOnClickListener { calcTip() }
    }

    private fun calcTip() {
        val costText = binding.costOfService.text.toString()
        val cost = costText.toFloatOrNull()
                ?: return Toast.makeText(this, "Please enter the Cost of Service!", Toast.LENGTH_SHORT).show()
        val tipPercent = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.fifteen_percent -> 0.15
            R.id.twelve_percent -> 0.12
            else -> 0.10
        }
        var tip = tipPercent * cost
        if(binding.roundupSwitch.isChecked){
            tip= kotlin.math.ceil(tip)
        }
        val tipFormatted = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, tipFormatted)
    }
}