package com.vhontar.materialcalculator.domain

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ExpressionParserTest {

    private lateinit var parser: ExpressionParser

    @Test
    fun `simple expression is properly parsed`() {
        parser = ExpressionParser("3+5-3x4/3")
        val actual = parser.parse()
        val expected = listOf(
            ExpressionPart.Number(3.0),
            ExpressionPart.Op(Operation.Add),
            ExpressionPart.Number(5.0),
            ExpressionPart.Op(Operation.Subtract),
            ExpressionPart.Number(3.0),
            ExpressionPart.Op(Operation.Multiply),
            ExpressionPart.Number(4.0),
            ExpressionPart.Op(Operation.Divide),
            ExpressionPart.Number(3.0)
        )
        assertThat(expected).isEqualTo(actual)
    }

    @Test
    fun `expression with parentheses is properly parsed`() {
        parser = ExpressionParser("4-(4x5)")
        val actual = parser.parse()
        val expected = listOf(
            ExpressionPart.Number(4.0),
            ExpressionPart.Op(Operation.Subtract),
            ExpressionPart.Parentheses(ParenthesesType.Opening),
            ExpressionPart.Number(4.0),
            ExpressionPart.Op(Operation.Multiply),
            ExpressionPart.Number(5.0),
            ExpressionPart.Parentheses(ParenthesesType.Closing)
        )
        assertThat(expected).isEqualTo(actual)
    }

    @Test
    fun `expression with parentheses and floating numbers is properly parsed`() {
        parser = ExpressionParser("4.33-(4x5)+(3.3/1.5)")
        val actual = parser.parse()
        val expected = listOf(
            ExpressionPart.Number(4.33),
            ExpressionPart.Op(Operation.Subtract),
            ExpressionPart.Parentheses(ParenthesesType.Opening),
            ExpressionPart.Number(4.0),
            ExpressionPart.Op(Operation.Multiply),
            ExpressionPart.Number(5.0),
            ExpressionPart.Parentheses(ParenthesesType.Closing),
            ExpressionPart.Op(Operation.Add),
            ExpressionPart.Parentheses(ParenthesesType.Opening),
            ExpressionPart.Number(3.3),
            ExpressionPart.Op(Operation.Divide),
            ExpressionPart.Number(1.5),
            ExpressionPart.Parentheses(ParenthesesType.Closing),
        )
        assertThat(expected).isEqualTo(actual)
    }
}