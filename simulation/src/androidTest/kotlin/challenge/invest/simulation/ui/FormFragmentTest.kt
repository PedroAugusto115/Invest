package challenge.invest.simulation.ui

import android.content.Intent
import challenge.invest.FragmentInstrumentedTest
import challenge.invest.extension.clearTextInChild
import challenge.invest.extension.isDisabled
import challenge.invest.extension.isDisplayed
import challenge.invest.extension.isEnabled
import challenge.invest.extension.typeTextInChild
import challenge.invest.simulation.R
import org.junit.Test

class FormFragmentTest : FragmentInstrumentedTest() {

    override fun instanceFragment(intent: Intent) = FormFragment()

    @Test
    fun shouldOpenForm_whenCreatesView() {
        form {
            initialState()
        } should {
            fieldsAsDisplayedAndButtonDisabled()
            isButtonDisabled()
        }
    }

    @Test
    fun shouldEnablesButton_whenFillFormCorrectly() {
        form {
            fillFieldsCorrectly()
        } should {
            isButtonEnabled()
        }
    }

    @Test
    fun shouldDisablesButton_whenFillFormWrongly() {
        form {
            fillFieldsWrongly()
        } should {
            isButtonDisabled()
        }
    }

    @Test
    fun shouldDisablesButton_whenErasesSomething() {
        form {
            fillFieldsCorrectly()
            clearField()
        } should {
            isButtonDisabled()
        }
    }

    private fun form(func: FormFragmentRobot.() -> Unit) =
        FormFragmentRobot().apply {
            activityRule.launchActivity(Intent())
            func()
        }
}

class FormFragmentRobot {

    fun initialState() {}

    fun fillFieldsCorrectly() {
        R.id.frag_form_value.typeTextInChild(R.id.txt_input, "290000")
        R.id.frag_form_due_date.typeTextInChild(R.id.txt_input, "15012100")
        R.id.frag_form_percent.typeTextInChild(R.id.txt_input, "100")
    }

    fun fillFieldsWrongly() {
        R.id.frag_form_value.typeTextInChild(R.id.txt_input, "290000")
        R.id.frag_form_due_date.typeTextInChild(R.id.txt_input, "15012000")
        R.id.frag_form_percent.typeTextInChild(R.id.txt_input, "1")
    }

    fun clearField() {
        R.id.frag_form_value.clearTextInChild(R.id.txt_input)
    }

    infix fun should(func: FormFragmentResult.() -> Unit) {
        FormFragmentResult().apply { func() }
    }
}

class FormFragmentResult {

    fun fieldsAsDisplayedAndButtonDisabled() {
        R.id.frag_form_value.isDisplayed()
        R.id.frag_form_due_date.isDisplayed()
        R.id.frag_form_percent.isDisplayed()
    }

    fun isButtonEnabled() {
        R.id.frag_form_button.isEnabled()
    }

    fun isButtonDisabled() {
        R.id.frag_form_button.isDisabled()
    }
}