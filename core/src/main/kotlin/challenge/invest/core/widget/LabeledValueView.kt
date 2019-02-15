package challenge.invest.core.widget

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.widget.TextView
import challenge.invest.core.R
import challenge.invest.core.extensions.bindView
import challenge.invest.core.extensions.inflate

class LabeledValueView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val label by bindView<TextView>(R.id.txt_labeled_label)
    private val textValue by bindView<TextView>(R.id.txt_labeled_value)

    init {
        inflate(R.layout.view_labeled_value)
        context.theme.obtainStyledAttributes(attrs, R.styleable.LabeledValueView, 0, 0).apply {
            try {
                label.text = getText(R.styleable.LabeledValueView_android_label)
            } finally {
                recycle()
            }
        }
    }

    fun setText(nText: String) {
        textValue.text = nText
    }

}