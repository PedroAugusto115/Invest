package challenge.invest.core.widget

import android.support.test.internal.runner.junit4.statement.UiThreadStatement
import challenge.invest.BaseCustomViewTest
import challenge.invest.core.extensions.toBrazilianCurrency
import challenge.invest.extension.isTextDisplayed
import org.junit.Test

class LabeledValueViewTest: BaseCustomViewTest() {

    @Test
    fun shouldShowFormattedCurrency_whenCreatedWithCurrencyValue() {
        createLabeledValueView {
            initialState()
        } should {
            formattedValueIsDisplayed()
        }
    }

    private fun createLabeledValueView(func: LabeledValueViewRobot.() -> Unit) =
        LabeledValueViewRobot().apply {
            UiThreadStatement.runOnUiThread {
                textInputView = startView(LabeledValueView::class.java)
                textInputView.setText(2900.0.toFloat().toBrazilianCurrency())
            }
            func()
        }
}

class LabeledValueViewRobot {
    lateinit var textInputView: LabeledValueView

    fun initialState() {}

    infix fun should(func: LabeledValueViewResult.() -> Unit) {
        LabeledValueViewResult().apply {
            func()
        }
    }
}

class LabeledValueViewResult {

    fun formattedValueIsDisplayed() {
        "R$2.900,00".isTextDisplayed()
    }
}