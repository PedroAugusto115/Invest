package challenge.invest.core.widget

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.Toolbar
import android.util.AttributeSet
import challenge.invest.core.R
import challenge.invest.core.extensions.bindView
import challenge.invest.core.extensions.inflate

class ViewToolbar  @JvmOverloads constructor(
    context: Context,
    attr: AttributeSet? = null,
    defStyleAttr: Int = 0
) : Toolbar(context, attr, defStyleAttr) {

    private val toolbarTitle by bindView<AppCompatTextView>(R.id.toolbar_title)

    init {
        inflate(R.layout.view_toolbar)
        context.theme.obtainStyledAttributes(attr, R.styleable.ViewToolbar, 0, 0).apply {
            try {
                toolbarTitle.text = getText(R.styleable.ViewToolbar_android_text)
            } finally {
                recycle()
            }
        }

        setBackgroundColor(ContextCompat.getColor(context, R.color.white))
        title = ""
        minimumHeight = android.R.attr.actionBarSize
    }
}