package challenge.invest

import challenge.invest.core.service.RemoteData
import android.content.Intent
import android.support.test.rule.ActivityTestRule
import android.support.v7.app.AppCompatActivity
import challenge.invest.core.extensions.hideKeyboard
import challenge.invest.extension.clickInId
import challenge.invest.extension.doesNotExists
import challenge.invest.extension.hasText
import challenge.invest.extension.isDisplayed
import challenge.invest.extension.isNotDisplayed
import challenge.invest.extension.typeTextInChild
import challenge.invest.simulation.R as simR
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Test

class MainActivityTest : BaseInstrumentedTest(MainActivity::class) {

    @Test
    fun afterScreenLoad_FieldsAreVisible() {
        main {
            initialState()
        } should {
            formIsVisible()
        }
    }

    @Test
    fun shouldShowError_whenApiReturnsError() {
        main {
            fillFieldsCorrectly()
            enqueueErrorSimulationResponse()
            hideKeyboard()
            clickSimulateButton()
        } should {
            errorIsVisible()
        }
    }

    @Test
    fun shouldShowResult_whenApiReturnsSuccess() {
        main {
            fillFieldsCorrectly()
            enqueueSimulationResponse()
            hideKeyboard()
            clickSimulateButton()
        } should {
            resultIsVisible()
        }
    }

    @Test
    fun shouldBackToForm_whenClickInReturn() {
        main {
            fillFieldsCorrectly()
            enqueueSimulationResponse()
            hideKeyboard()
            clickSimulateButton()
            clickBackButton()
        } should {
            formIsVisible()
        }
    }

    private fun main(func: MainActivityRobot.() -> Unit): MainActivityRobot {
        server.start()
        setupServerUrl()
        activityRule.launchActivity(Intent())
        return MainActivityRobot(activityRule, server).apply { func() }
    }

    private fun setupServerUrl() {
        RemoteData.getInstance(server.url("/").toString())
    }
}

class MainActivityRobot(
    private val activityRule: ActivityTestRule<out AppCompatActivity>,
    private val server: MockWebServer
) {
    fun initialState() { }

    fun fillFieldsCorrectly() {
        simR.id.frag_form_value.typeTextInChild(challenge.invest.simulation.R.id.txt_input, "290000")
        simR.id.frag_form_due_date.typeTextInChild(challenge.invest.simulation.R.id.txt_input, "15012100")
        simR.id.frag_form_percent.typeTextInChild(challenge.invest.simulation.R.id.txt_input, "100")
    }

    fun clickSimulateButton() {
        simR.id.frag_form_button.clickInId()
    }

    fun clickBackButton() {
        simR.id.frag_result_button.clickInId()
    }

    fun hideKeyboard() {
        activityRule.activity.currentFocus?.hideKeyboard()
    }

    fun enqueueErrorSimulationResponse() {
        server.enqueue(MockResponse().setResponseCode(500))
    }

    fun enqueueSimulationResponse() {
        server.enqueue(MockResponse().setResponseCode(200).setBody(getResponseString()))
    }

    infix fun should(func: MainActivityResult.() -> Unit) {
        MainActivityResult().run { func() }
    }
}

class MainActivityResult {

    fun formIsVisible() {
        Thread.sleep(100)
        R.id.frag_form.isDisplayed()
        R.id.error_view.isNotDisplayed()
        R.id.frag_result.doesNotExists()
    }

    fun errorIsVisible() {
        Thread.sleep(100)
        R.id.frag_form.isNotDisplayed()
        R.id.error_view.isDisplayed()
        R.id.frag_result.doesNotExists()
    }

    fun resultIsVisible() {
        Thread.sleep(100)
        R.id.frag_form.doesNotExists()
        R.id.error_view.doesNotExists()
        R.id.frag_result.isDisplayed()
        R.id.txt_result_total_value.hasText("R$7.052,65")
    }
}

fun getResponseString() = "{\n" +
            "  \"investmentParameter\":{\n" +
            "    \"investedAmount\":2900.0,\n" +
            "    \"yearlyInterestRate\":9.0718,\n" +
            "    \"maturityTotalDays\":3621,\n" +
            "    \"maturityBusinessDays\":2579,\n" +
            "    \"maturityDate\":\"2029-01-14T00:00:00\",\n" +
            "    \"rate\":100.0,\n" +
            "    \"isTaxFree\":false\n" +
            "  },\n" +
            "  \"grossAmount\":7052.65,\n" +
            "  \"taxesAmount\":622.90,\n" +
            "  \"netAmount\":6429.75,\n" +
            "  \"grossAmountProfit\":4152.65,\n" +
            "  \"netAmountProfit\":3529.75,\n" +
            "  \"annualGrossRateProfit\":143.19,\n" +
            "  \"monthlyGrossRateProfit\":0.73,\n" +
            "  \"dailyGrossRateProfit\":0.000344647452606317,\n" +
            "  \"taxesRate\":15.0,\n" +
            "  \"rateProfit\":9.0718,\n" +
            "  \"annualNetRateProfit\":121.72\n" +
            "}"
