package challenge.invest

import android.os.Bundle
import android.support.annotation.RestrictTo
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.FrameLayout

@RestrictTo(RestrictTo.Scope.TESTS)
class BaseCustomViewActivityTest : AppCompatActivity() {

    private val container by lazy { FrameLayout(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(container)
    }

    fun inflateView(view: View) {
        runOnUiThread {
            container.addView(view)
        }
    }
}