package challenge.invest

import android.os.Bundle
import android.support.annotation.RestrictTo
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import challenge.investtests.R

@RestrictTo(RestrictTo.Scope.TESTS)
open class BaseTestFragmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_AppCompat_Light_NoActionBar)
    }

    fun setFragment(fragmentInstance: Fragment) {
        runOnUiThread {
            supportFragmentManager.beginTransaction()
                    .replace(android.R.id.content, fragmentInstance)
                    .commit()
        }
    }
}
