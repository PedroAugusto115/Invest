package challenge.invest

import android.support.annotation.RestrictTo
import android.support.test.rule.ActivityTestRule
import android.support.v7.app.AppCompatActivity
import org.junit.Rule
import kotlin.reflect.KClass
import okhttp3.mockwebserver.MockWebServer

@RestrictTo(RestrictTo.Scope.TESTS)
abstract class BaseInstrumentedTest(kClass: KClass<out AppCompatActivity>) {

    var server: MockWebServer = MockWebServer()

    @get:Rule
    val activityRule = ActivityTestRule(kClass.java, false, false)
}