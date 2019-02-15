package challenge.invest.simulation.ui

import android.content.Intent
import challenge.invest.FragmentInstrumentedTest
import challenge.invest.extension.isDisabled
import challenge.invest.extension.isDisplayed
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

    infix fun should(func: FormFragmentResult.() -> Unit) {
        FormFragmentResult().apply { func() }
    }
}

class FormFragmentResult {

    fun fieldsAsDisplayedAndButtonDisabled() {
        R.id.frag_form_value.isDisplayed()
        R.id.frag_form_due_date.isDisplayed()
        R.id.frag_form_percent.isDisplayed()
        R.id.frag_form_button.isDisabled()
    }
}