package com.vhontar.materialcalculator.presentation.calculator

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.vhontar.materialcalculator.MainActivity
import com.vhontar.materialcalculator.domain.Operation
import org.junit.Rule
import org.junit.Test

class CalculatorScreenKtTest {

    @get:Rule
    val composeRule = createAndroidComposeRule(MainActivity::class.java)

    @Test
    fun enterExpression_correctResultDisplayed() {
        composeRule.onNodeWithText("1").performClick()
        composeRule.onNodeWithText(Operation.Add.symbol.toString()).performClick()
        composeRule.onNodeWithText("2").performClick()
        composeRule.onNodeWithText(Operation.Multiply.symbol.toString()).performClick()
        composeRule.onNodeWithText("()").performClick()
        composeRule.onNodeWithText("4").performClick()
        composeRule.onNodeWithText(Operation.Add.symbol.toString()).performClick()
        composeRule.onNodeWithText("5").performClick()
        composeRule.onNodeWithText("()").performClick()
        composeRule.onNodeWithText("=").performClick()

        composeRule.onNodeWithText("19").assertIsDisplayed()
    }

    @Test
    fun enterExpressionWithNotCorrectValues_correctResultDisplayed() {
        composeRule.onNodeWithText("1").performClick()
        composeRule.onNodeWithText(Operation.Add.symbol.toString()).performClick()
        composeRule.onNodeWithText(Operation.Multiply.symbol.toString()).performClick()
        composeRule.onNodeWithText(Operation.Divide.symbol.toString()).performClick()
        composeRule.onNodeWithText("2").performClick()

        composeRule.onNodeWithText("1+2").assertIsDisplayed()
    }
}