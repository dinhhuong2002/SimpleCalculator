package com.example.simplecalculator

import android.icu.number.NumberFormatter.DecimalSeparatorDisplay
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    private lateinit var display: EditText
    private var currentDisplay = "0"
    private var operator = ""
    private var firstOperand = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        display = findViewById(R.id.result)

        val button_CE = findViewById<Button>(R.id.button_CE)
        val button_C = findViewById<Button>(R.id.button_C)
        val button_BS = findViewById<Button>(R.id.button_BS)
        val button_equals = findViewById<Button>(R.id.button_equals)

        button_CE.setOnClickListener { clearEntry() }
        button_C.setOnClickListener { clearDisplay() }
        button_BS.setOnClickListener { backspace() }
        button_equals.setOnClickListener { result() }
    }

    fun appendNumber(view:View){
        val button = view as Button
        operator = button.text.toString()
        firstOperand = currentDisplay
        currentDisplay = "0"
    }

    fun clearEntry(){
        currentDisplay="0"
        display.text=currentDisplay
    }

    fun clearDisplay() {
        currentDisplay = "0"
        operator = ""
        firstOperand = ""
        display.text = currentDisplay
    }

    fun backspace() {
        if (currentDisplay.length > 1) {
            currentDisplay = currentDisplay.substring(0, currentDisplay.length - 1)
        } else {
            currentDisplay = "0"
        }
        display.text = currentDisplay
    }

    fun result() {
        if (firstOperand.isNotEmpty() && operator.isNotEmpty()) {
            val operand1 = firstOperand.toDouble()
            val operand2 = currentDisplay.toDouble()

            when (operator) {
                "+" -> currentDisplay = (operand1 + operand2).toString()
                "-" -> currentDisplay = (operand1 - operand2).toString()
                "*" -> currentDisplay = (operand1 * operand2).toString()
                "/" -> {
                    if (operand2 != 0.0) {
                        currentDisplay = (operand1 / operand2).toString()
                    } else {
                        currentDisplay = "Error"
                    }
                }
            }

            display.text = currentDisplay
            operator = ""
            firstOperand = ""
        }
    }
}