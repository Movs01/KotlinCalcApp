package com.example.petproject

class Calculator {

    private var operand1: Double = 0.0
    private var operand2: Double = 0.0
    private var operator: String = ""

    fun setOperand1(operand1: Double) {
        this.operand1 = operand1
    }

    fun setOperand2(operand2: Double) {
        this.operand2 = operand2
    }

    fun setOperator(operator: String) {
        this.operator = operator
    }

    fun calculate(): Double {
        return when (operator) {
            "+" -> operand1 + operand2
            "-" -> operand1 - operand2
            "х" -> operand1 * operand2
            "÷" -> operand1 / operand2
            else -> 0.0
        }
    }

    fun clear() {
        operand1 = 0.0
        operand2 = 0.0
        operator = ""
    }
}
