package com.example.mybmiapp

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity

class MainActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val bmiValueText = findViewById<TextView>(R.id.bmiValueText)
        val statusText = findViewById<TextView>(R.id.statusText)
        val backButton = findViewById<Button>(R.id.backButton)

        val bmi = intent.getDoubleExtra("BMI", 0.0)

        bmiValueText.text = String.format("%.1f", bmi)

        val (status, color) = getStatusAndColor(bmi)
        statusText.text = status
        statusText.setTextColor(Color.parseColor(color))

        backButton.setOnClickListener { finish() }
    }

    private fun getStatusAndColor(bmi: Double): Pair<String, String> {
        return when {
            bmi <= 18.5 -> "Underweight" to "#2196F3"
            bmi <= 24.9 -> "Normal" to "#4CAF50"
            bmi <= 29.9 -> "Overweight" to "#FF9800"
            else -> "Obese" to "#F44336"
        }
    }
}