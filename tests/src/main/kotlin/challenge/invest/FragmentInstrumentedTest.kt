package challenge.invest

import android.content.Intent
import android.support.annotation.RestrictTo
import android.support.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread
import android.support.test.rule.ActivityTestRule
import android.support.v4.app.Fragment
import org.junit.Rule

@RestrictTo(RestrictTo.Scope.TESTS)
abstract class FragmentInstrumentedTest {

    @get:Rule
    val activityRule = FragmentTestRule {
        runOnUiThread { execBefore() }
        instanceFragment(it)
    }

    abstract fun instanceFragment(intent: Intent): Fragment

    open fun execBefore(func: () -> Unit = {}) = func()
}

@RestrictTo(RestrictTo.Scope.TESTS)
class FragmentTestRule(private val fragmentInstanceFunc: (Intent) -> Fragment
) : ActivityTestRule<BaseTestFragmentActivity>(BaseTestFragmentActivity::class.java, true, false) {

    override fun afterActivityLaunched() {
        super.afterActivityLaunched()
        activity.setFragment(fragmentInstanceFunc(activity.intent))
        Thread.sleep(200)
    }
}
