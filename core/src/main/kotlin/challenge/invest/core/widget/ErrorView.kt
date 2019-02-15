package challenge.invest.core.widget

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.widget.TextView
import challenge.invest.core.R
import challenge.invest.core.extensions.bindView
import challenge.invest.core.extensions.inflate

class ErrorView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val tryAgain by bindView<TextView>(R.id.txt_try)
    private val backButton by bindView<TextView>(R.id.txt_back)

    init {
        inflate(R.layout.view_error)
    }

    fun onTryAgainClick(listener: () -> Unit) {
        tryAgain.setOnClickListener { listener() }
    }

    fun onBackButtonClick(listener: () -> Unit) {
        backButton.setOnClickListener { listener() }
    }
}