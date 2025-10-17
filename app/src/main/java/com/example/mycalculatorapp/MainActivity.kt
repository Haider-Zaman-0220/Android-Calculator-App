package com.example.mycalculatorapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var display: TextView
    private var currentInput: String = "0"
    private var storedValue: Double? = null
    private var pendingOp: Char? = null
    private var justPressedOp = false // New state to track operator presses

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        display = findViewById(R.id.txt_display)

        val digitIds = listOf(
            R.id.btn_0, R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4,
            R.id.btn_5, R.id.btn_6, R.id.btn_7, R.id.btn_8, R.id.btn_9,
            R.id.btn_00
        )
        digitIds.forEach { id ->
            findViewById<Button>(id).setOnClickListener { onDigit((it as Button).text.toString()) }
        }

        findViewById<Button>(R.id.btn_dot).setOnClickListener { onDot() }
        findViewById<Button>(R.id.btn_clear).setOnClickListener { onClear() }
        findViewById<Button>(R.id.btn_add).setOnClickListener { onOp('+') }
        findViewById<Button>(R.id.btn_sub).setOnClickListener { onOp('-') }
        findViewById<Button>(R.id.btn_mul).setOnClickListener { onOp('*') }
        findViewById<Button>(R.id.btn_div).setOnClickListener { onOp('/') }
        findViewById<Button>(R.id.btn_eq).setOnClickListener { onEquals() }
        findViewById<Button>(R.id.btn_percent).setOnClickListener { onPercent() }
        findViewById<Button>(R.id.btn_backspace).setOnClickListener { onBackspace() }

        render()
    }

    private fun onDigit(d: String) {
        if (justPressedOp) {
            currentInput = "0"
        }

        if (currentInput == "Error") {
            currentInput = d
        } else if (currentInput == "0") {
            if (d == "0" || d == "00") return
            currentInput = d
        } else {
            currentInput += d
        }
        justPressedOp = false
        render()
    }

    private fun onBackspace() {
        if (currentInput == "Error" || justPressedOp) {
            // Do nothing if we just pressed an operator or are in an error state
        } else {
            currentInput = currentInput.dropLast(1)
            if (currentInput.isEmpty()) {
                currentInput = "0"
            }
        }
        render()
    }

    private fun onDot() {
        if (currentInput == "Error") return
        if (!currentInput.contains('.')) {
            currentInput += if (currentInput.isEmpty() || justPressedOp) "0." else "."
            justPressedOp = false
            render()
        }
    }

    private fun onClear() {
        currentInput = "0"
        storedValue = null
        pendingOp = null
        justPressedOp = false
        render()
    }

    private fun onOp(op: Char) {
        if (currentInput == "Error") return

        // Allow changing the operator
        if (justPressedOp) {
            pendingOp = op
            render()
            return
        }

        val inputVal = currentInput.toDoubleOrNull()
        if (inputVal != null) {
            if (storedValue == null) {
                storedValue = inputVal
            } else if (pendingOp != null) {
                storedValue = compute(storedValue!!, inputVal, pendingOp!!)
            }
        }
        pendingOp = op
        justPressedOp = true
        render()
    }

    private fun onEquals() {
        if (currentInput == "Error" || storedValue == null || pendingOp == null) return
        val inputVal = currentInput.toDoubleOrNull()
        if (inputVal != null) {
            val result = compute(storedValue!!, inputVal, pendingOp!!)
            if (result.isNaN()) {
                currentInput = "Error"
            } else {
                currentInput = trimDouble(result)
            }
            pendingOp = null
            storedValue = null
        }
        justPressedOp = false
        render()
    }

    private fun onPercent() {
        if (currentInput == "Error") return
        val v = currentInput.toDoubleOrNull() ?: return
        currentInput = trimDouble(v / 100.0)
        justPressedOp = false
        render()
    }

    private fun compute(a: Double, b: Double, op: Char): Double {
        return when (op) {
            '+' -> a + b
            '-' -> a - b
            '*' -> a * b
            '/' -> if (b == 0.0) Double.NaN else a / b
            else -> b
        }
    }

    private fun trimDouble(v: Double): String {
        val s = v.toString()
        return if (s.endsWith(".0")) s.dropLast(2) else s
    }

    private fun render() {
        if (pendingOp == null) {
            display.text = currentInput
        } else {
            val expressionPrefix = "${trimDouble(storedValue!!)}${pendingOp}"
            if (justPressedOp) {
                display.text = expressionPrefix
            } else {
                display.text = expressionPrefix + currentInput
            }
        }
    }
}
