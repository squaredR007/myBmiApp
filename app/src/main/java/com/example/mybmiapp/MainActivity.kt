package com.example.mybmiapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.button.MaterialButtonToggleGroup
import kotlin.jvm.java

class MainActivity : ComponentActivity() {

    private var isMetric = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        isMetric = savedInstanceState?.getBoolean("isMetric") ?: true

        val weightLayout = findViewById<TextInputLayout>(R.id.weightLayout)
        val heightLayout = findViewById<TextInputLayout>(R.id.heightLayout)
        val weightTextBox = findViewById<TextInputEditText>(R.id.weightTextBox)
        val heightTextBox = findViewById<TextInputEditText>(R.id.heightTextBox)
        val calculateButton = findViewById<Button>(R.id.calculateBTN)
        val unitToggleGroup = findViewById<MaterialButtonToggleGroup>(R.id.unitToggleGroup)

        unitToggleGroup.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (isChecked) {
                isMetric = checkedId == R.id.metricBtn
                weightLayout.hint = if (isMetric) "Weight (kg)" else "Weight (lb)"
                heightLayout.hint = if (isMetric) "Height (m)" else "Height (in)"
            }
        }
        unitToggleGroup.check(if (isMetric) R.id.metricBtn else R.id.imperialBtn)

        calculateButton.setOnClickListener {
            weightLayout.error = null
            heightLayout.error = null

            val weightInput = weightTextBox.text.toString()
            val heightInput = heightTextBox.text.toString()

            val weight = weightInput.toDoubleOrNull()
            val height = heightInput.toDoubleOrNull()

            var hasError = false
            if (weight == null || weight <= 0) {
                weightLayout.error = "Enter a valid weight"
                hasError = true
            }
            if (height == null || height <= 0) {
                heightLayout.error = "Enter a valid height"
                hasError = true
            }
            if (hasError) return@setOnClickListener

            val bmi = if (isMetric) {
                weight!! / (height!! * height)
            } else {
                703 * weight!! / (height!! * height)
            }

            val myIntent = Intent(this, MainActivity2::class.java)
            myIntent.putExtra("BMI", bmi)
            startActivity(myIntent)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("isMetric", isMetric)
    }
}