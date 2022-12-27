package com.vhontar.materialcalculator.domain

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ExpressionEvaluatorTest {

    private lateinit var evaluator: ExpressionEvaluator

    @Test
    fun `simple expression is properly evaluated`() {
        // 4+5-3x5/3 = 4
        val expression = listOf(
            ExpressionPart.Number(4.0),
            ExpressionPart.Op(Operation.Add),
            ExpressionPart.Number(5.0),
            ExpressionPart.Op(Operation.Subtract),
            ExpressionPart.Number(3.0),
            ExpressionPart.Op(Operation.Multiply),
            ExpressionPart.Number(5.0),
            ExpressionPart.Op(Operation.Divide),
            ExpressionPart.Number(3.0)
        )
        evaluator = ExpressionEvaluator(expression)
        val actual = evaluator.evaluate()
        val expected = 4
        assertThat(expected).isEqualTo(actual)
    }

    @Test
    fun `expression with decimals is properly evaluated`() {
        // 4.5+5.5-3.5x5.5/3.5 = 4.5
        val expression = listOf(
            ExpressionPart.Number(4.5),
            ExpressionPart.Op(Operation.Add),
            ExpressionPart.Number(5.5),
            ExpressionPart.Op(Operation.Subtract),
            ExpressionPart.Number(3.5),
            ExpressionPart.Op(Operation.Multiply),
            ExpressionPart.Number(5.5),
            ExpressionPart.Op(Operation.Divide),
            ExpressionPart.Number(3.5)
        )
        evaluator = ExpressionEvaluator(expression)
        val actual = evaluator.evaluate()
        val expected = 4.5
        assertThat(expected).isEqualTo(actual)
    }

    @Test
    fun `expression with parentheses is properly evaluated`() {
        // 4.5+5.5-(3.5x5.5/3.5) = 4.5
        val expression = listOf(
            ExpressionPart.Number(4.5),
            ExpressionPart.Op(Operation.Add),
            ExpressionPart.Number(5.5),
            ExpressionPart.Op(Operation.Subtract),
            ExpressionPart.Parentheses(ParenthesesType.Opening),
            ExpressionPart.Number(3.5),
            ExpressionPart.Op(Operation.Multiply),
            ExpressionPart.Number(5.5),
            ExpressionPart.Op(Operation.Divide),
            ExpressionPart.Number(3.5),
            ExpressionPart.Parentheses(ParenthesesType.Closing)
        )
        evaluator = ExpressionEvaluator(expression)
        val actual = evaluator.evaluate()
        val expected = 4.5
        assertThat(expected).isEqualTo(actual)
    }
}