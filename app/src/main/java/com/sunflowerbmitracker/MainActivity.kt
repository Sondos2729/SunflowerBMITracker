package com.sunflowerbmitracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        // Initializing views and components
        val imageFemale = findViewById<ImageView>(R.id.female_image)
        val imageMale = findViewById<ImageView>(R.id.male_image)
        val weight = findViewById<EditText>(R.id.weight_value)
        val height = findViewById<EditText>(R.id.height_value)
        val calculateButton = findViewById<Button>(R.id.calculate_button)
        val bmi = findViewById<TextView>(R.id.bmi)
        val bmiStatus = findViewById<TextView>(R.id.bmi_status)
        val bmiView = findViewById<LinearLayout>(R.id.bmiView)
        val calculateAgainButton = findViewById<TextView>(R.id.calculate_again)

        // Click listener for female image
        imageFemale.setOnClickListener {
            imageFemale.setImageResource(R.drawable.female)
            imageMale.setImageResource(R.drawable.male_not_select)
        }

        // Click listener for male image
        imageMale.setOnClickListener {
            imageFemale.setImageResource(R.drawable.female_not_select)
            imageMale.setImageResource(R.drawable.male)
        }

        // Click listener for calculate button
        calculateButton.setOnClickListener {
            var weightValue = 0.0
            var heightValue = 0.0

            if (weight.text.toString().isNotEmpty()) {
                weightValue = weight.text.toString().toDouble()
            }

            if (height.text.toString().isNotEmpty()) {
                heightValue = (height.text.toString().toDouble() / 100)
            }

            if (weightValue > 0.0 && heightValue > 0.0) {
                val bmiValue = String.format("%.2f", weightValue / heightValue.pow(2))
                bmi.text = bmiValue
                bmiStatus.text = bmiStatusValue(weightValue / heightValue.pow(2))
                bmiView.visibility = VISIBLE
                calculateButton.visibility = GONE
            } else {
                Toast.makeText(
                    this,
                    "Please input weight and height values greater than 0",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        // Click listener for calculate again button
        calculateAgainButton.setOnClickListener {
            bmiStatus.visibility = GONE
            bmiView.visibility = GONE
            calculateButton.visibility = VISIBLE
            weight.text.clear()
            height.text.clear()
            weight.requestFocus()
            height.requestFocus()
        }
    }

    // Function to determine BMI status based on the calculated BMI value
    private fun bmiStatusValue(bmi: Double): String {
        lateinit var bmiStatus: String
        if (bmi < 18.5)
            bmiStatus = "Underweight"
        else if (bmi >= 18.5 && bmi < 25)
            bmiStatus = "Normal"
        else if (bmi >= 25 && bmi < 30)
            bmiStatus = "Overweight"
        else if (bmi >= 30)
            bmiStatus = "Obese"
        return bmiStatus
    }
}
