package com.vhontar.materialcalculator.domain.writer

import com.vhontar.materialcalculator.domain.Operation
import com.vhontar.materialcalculator.domain.evaluator.ExpressionEvaluator
import com.vhontar.materialcalculator.domain.operationSymbols
import com.vhontar.materialcalculator.domain.parser.ExpressionParser

class ExpressionWriter {
    var expression = ""
        private set

    private var isCalculatedClicked = false

    fun processAction(action: CalculatorAction) {
        if (isCalculatedClicked && action is CalculatorAction.Number) {
            expression = ""
        }
        isCalculatedClicked = false

        when(action) {
            CalculatorAction.Calculate -> {
                val parser = ExpressionParser(prepareForCalculation())
                val evaluator = ExpressionEvaluator(parser.parse())

                val result = evaluator.evaluate()
                expression = when {
                    result.compareTo(result.toInt()) == 0 -> result.toInt().toString()
                    else -> result.toString()
                }

                isCalculatedClicked = true
            }
            CalculatorAction.Clear -> {
                expression = ""
            }
            CalculatorAction.Decimal -> {
                if (canEnterDecimal()) {
                    expression += "."
                }
            }
            CalculatorAction.Delete -> {
                expression = expression.dropLast(1)
            }
            is CalculatorAction.Number -> {
                expression += action.number
            }
            is CalculatorAction.Op -> {
                if (canEnterOperation(action.operation)) {
                    expression += action.operation.symbol
                }
            }
            CalculatorAction.Parentheses -> {
                processParentheses()
            }
        }
    }

    private fun prepareForCalculation(): String {
        // 3+4- -> it's not valid
        val newExpression = expression.dropLastWhile {
            it in "$operationSymbols(."
        }
        if (newExpression.isEmpty()) {
            return "0"
        }

        return newExpression
    }

    private fun processParentheses() {
        val openingCount = expression.count { it == '('}
        val closingCount = expression.count { it == ')'}

        expression += when {
            expression.isEmpty() || expression.last() in "$operationSymbols(" -> "("
            expression.last() in "0123456789)" && openingCount == closingCount -> return
            else -> ")"
        }
    }

    private fun canEnterDecimal(): Boolean {
        if (expression.isEmpty() || expression.last() in "$operationSymbols.()") {
            return false
        }

        // 4+5.56 -> not needed
        // 4+55 -> can make it decimal
        return !expression.takeLastWhile {
            it in "1234567890."
        }.contains(".")
    }

    private fun canEnterOperation(operation: Operation): Boolean {
        // +--3+-5 is possible
        // we always add +/- if it's not 5.+
        if (operation in listOf(Operation.Add, Operation.Subtract)) {
            return expression.isEmpty() || expression.last() in "$operationSymbols()0123456789"
        }

        // for *,/,% we want to make sure that it ends with number or closing parentheses
        // (4+5)*(3*4) -> it's ok
        // 4+*5 -> it's not ok
        return expression.isNotEmpty() && expression.last() in "0123456789)"
    }
}