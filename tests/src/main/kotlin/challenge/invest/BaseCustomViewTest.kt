package challenge.invest

import android.content.Context
import android.content.Intent
import android.support.annotation.RestrictTo
import android.view.View
import org.junit.Before

@RestrictTo(RestrictTo.Scope.TESTS)
abstract class BaseCustomViewTest : BaseInstrumentedTest(BaseCustomViewActivityTest::class) {

    @Before
    fun setUp() {
        activityRule.launchActivity(Intent())
    }

    protected fun <T : View> startView(viewClass: Class<T>): T =
            viewClass
                    .getConstructor(Context::class.java)
                    .newInstance(activityRule.activity).apply {
                        (activityRule.activity as BaseCustomViewActivityTest).inflateView(this)
                    }
}