package com.vhontar.materialcalculator.domain

enum class Operation(val symbol: Char) {
    Add('+'),
    Subtract('-'),
    Multiply('x'),
    Divide('/'),
    Percent('%')
}

val operationSymbols = Operation.values().map { it.symbol }.joinToString("")

fun operationFromSymbol(symbol: Char): Operation {
    return Operation.values().find { it.symbol == symbol }
        ?: throw IllegalArgumentException("Invalid symbol")
}