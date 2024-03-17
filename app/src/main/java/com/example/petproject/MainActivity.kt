package com.example.petproject

import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var calculator: Calculator
    private lateinit var resultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        calculator = Calculator()
        resultTextView = findViewById(R.id.result)

        // Обработчики событий для кнопок цифр
        val numberButtons = listOf<Button>(
            findViewById(R.id.zero), findViewById(R.id.one), findViewById(R.id.two),
            findViewById(R.id.three), findViewById(R.id.four), findViewById(R.id.five),
            findViewById(R.id.six), findViewById(R.id.seven), findViewById(R.id.eight),
            findViewById(R.id.nine)
        )

        // Обработчики событий для кнопок операторов и равно
        val operatorButtons = listOf<Button>(
            findViewById(R.id.plus), findViewById(R.id.minus), findViewById(R.id.multiply),
            findViewById(R.id.division), findViewById(R.id.equals)
        )

        // Обработчик события для кнопки очистки (AC)
        val clearButton = findViewById<Button>(R.id.AC)

        // Обработчики событий для кнопок смены знака числа, процента и десятичной точки
        val changeSignButton = findViewById<Button>(R.id.changeSign)
        val percentButton = findViewById<Button>(R.id.percent)
        val commaButton = findViewById<Button>(R.id.comma)

        // Установка максимальной длины строки в текстовом поле результатов
        resultTextView.maxLines = 1
        resultTextView.ellipsize = TextUtils.TruncateAt.END

        // Установка обработчиков событий для кнопок цифр
        numberButtons.forEach { button ->
            button.setOnClickListener {
                try {
                    val currentText = resultTextView.text.toString()
                    if (currentText.startsWith("0") )
                        resultTextView.text.toString().substring(1)
                    else
                        resultTextView.text.toString()
                    if (currentText.length < 10) { // Ограничение длины строки
                        resultTextView.text = currentText + button.text.toString()
                    } else {
                        Toast.makeText(this, "Достигнут лимит символов", Toast.LENGTH_SHORT).show()
                    }
//
                } catch (e: NumberFormatException) {
                    Toast.makeText(this, "Ошибка ввода числа", Toast.LENGTH_SHORT).show()
                } catch (e: Exception) {
                    Toast.makeText(this, "Произошла ошибка", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // Установка обработчиков событий для кнопок операторов и равно
        operatorButtons.forEach { button ->
            button.setOnClickListener {
                try {
                    val currentText = resultTextView.text.toString()
                    val operator = button.text.toString()
                    calculator.setOperand1(currentText.toDouble())
                    calculator.setOperator(operator)
                    resultTextView.text = "0"
                } catch (e: NumberFormatException) {
                    Toast.makeText(this, "Ошибка ввода числа", Toast.LENGTH_SHORT).show()
                } catch (e: Exception) {
                    Toast.makeText(this, "Произошла ошибка", Toast.LENGTH_SHORT).show()
                }
            }
        }


        // Обработчик события для кнопки равно
        findViewById<Button>(R.id.equals).setOnClickListener {
            try {
                val currentText = resultTextView.text.toString()
                calculator.setOperand2(currentText.toDouble())
                val result = calculator.calculate()
                resultTextView.text = result.toString()
            } catch (e: NumberFormatException) {
                Toast.makeText(this, "Ошибка ввода числа", Toast.LENGTH_SHORT).show()
            } catch (e: ArithmeticException) {
                Toast.makeText(this, "Деление на ноль", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(this, "Произошла ошибка", Toast.LENGTH_SHORT).show()
            }
        }

        // Обработчик события для кнопки очистки (AC)
        clearButton.setOnClickListener {
            resultTextView.text = "0"
            calculator.clear()
        }

        // Обработчик события для кнопки смены знака числа
        changeSignButton.setOnClickListener {
            try {
                val currentText = resultTextView.text.toString()
                if (currentText.isNotEmpty()) {
                    val newValue = -1 * currentText.toDouble()
                    resultTextView.text = newValue.toString()
                }
            } catch (e: NumberFormatException) {
                Toast.makeText(this, "Ошибка ввода числа", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(this, "Произошла ошибка", Toast.LENGTH_SHORT).show()
            }
        }

        // Обработчик события для кнопки процента
        percentButton.setOnClickListener {
            try {
                val currentText = resultTextView.text.toString()
                if (currentText.isNotEmpty()) {
                    val percentValue = currentText.toDouble() / 100
                    resultTextView.text = percentValue.toString()
                }
            } catch (e: NumberFormatException) {
                Toast.makeText(this, "Ошибка ввода числа", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(this, "Произошла ошибка", Toast.LENGTH_SHORT).show()
            }
        }

        // Обработчик события для кнопки десятичной точки
        commaButton.setOnClickListener {
            try {
                val currentText = resultTextView.text.toString()
                if (!currentText.contains("."))
                    resultTextView.text = "$currentText."

            } catch (e: Exception) {
                Toast.makeText(this, "Произошла ошибка", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
