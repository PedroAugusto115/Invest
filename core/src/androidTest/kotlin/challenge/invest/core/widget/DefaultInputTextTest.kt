package challenge.invest.core.widget

import android.support.test.internal.runner.junit4.statement.UiThreadStatement
import challenge.invest.BaseCustomViewTest
import challenge.invest.core.R
import challenge.invest.extension.cleanText
import challenge.invest.extension.typeText
import org.junit.Assert.assertTrue
import org.junit.Test

class DefaultInputTextTest: BaseCustomViewTest() {

    @Test
    fun defaultInputTextStatusShouldBeDefault_whenIsCreated() {
        createDefaultInputTextWithIntValidation {
            initialState()
        } should {
            viewStatusIsDefault()
        }
    }

    @Test
    fun defaultInputTextStatusShouldBeDefault_whenTypeAndEraseValue() {
        createDefaultInputTextWithIntValidation {
            typeNumber()
            clearField()
        } should {
            viewStatusIsDefault()
        }
    }

    @Test
    fun defaultInputTextStatusShouldBeValid_whenTypedValidValue() {
        createDefaultInputTextWithIntValidation {
            typeNumber()
        } should {
            viewStatusIsValid()
        }
    }

    private fun createDefaultInputTextWithIntValidation(func: DefaultInputTextRobot.() -> Unit) =
        DefaultInputTextRobot().apply {
            UiThreadStatement.runOnUiThread {
                intInputView = startView(DefaultInputText::class.java).apply {
                    setValidationListener {
                        when (it.toIntOrNull()) {
                            null -> false
                            else -> true
                        }
                    }
                }
            }
            func()
        }
}

class DefaultInputTextRobot {

    lateinit var intInputView: DefaultInputText

    fun initialState() {}

    fun typeNumber() {
        R.id.txt_input.typeText("123")
    }

    fun clearField() {
        R.id.txt_input.cleanText()
    }

    infix fun should(func: DefaultInputTextResult.() -> Unit) {
        DefaultInputTextResult().apply {
            intInputViewResult = intInputView
            func()
        }
    }
}

class DefaultInputTextResult {
    lateinit var intInputViewResult: DefaultInputText

    fun viewStatusIsDefault() {
        assertTrue(intInputViewResult.state.value == DefaultInputText.ValueState.DEFAULT)
    }

    fun viewStatusIsValid() {
        assertTrue(intInputViewResult.state.value == DefaultInputText.ValueState.VALID)
    }
}