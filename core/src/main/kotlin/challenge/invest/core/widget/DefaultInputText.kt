package challenge.invest.core.widget

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.support.annotation.ColorRes
import android.support.annotation.DimenRes
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.View
import android.widget.EditText
import android.widget.TextView
import challenge.invest.core.R
import challenge.invest.core.extensions.addTextChanged
import challenge.invest.core.extensions.backgroundColor
import challenge.invest.core.extensions.bindView
import challenge.invest.core.extensions.changeHeight
import challenge.invest.core.extensions.color
import challenge.invest.core.extensions.inflate

class DefaultInputText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val titleLabel by bindView<TextView>(R.id.txt_label)
    private val viewReference by bindView<View>(R.id.txt_reference)
    val inputView by bindView<EditText>(R.id.txt_input)

    var state = MutableLiveData<ValueState>().apply { value = ValueState.DEFAULT }

    private var validationListener: ((String) -> Boolean)? = null
    private var focusState = false

    init {
        inflate(R.layout.view_default_input_text)
        context.theme.obtainStyledAttributes(attrs, R.styleable.DefaultInputText, 0, 0).apply {
            try {
                titleLabel.text = getText(R.styleable.DefaultInputText_android_label)
                inputView.hint = getText(R.styleable.DefaultInputText_android_hint)
            } finally {
                recycle()
            }
        }

        inputView.setOnFocusChangeListener { _, hasFocus -> setIsFocused(hasFocus) }
        inputView.addTextChanged { validateValue() }
    }

    private fun setIsFocused(newFocus: Boolean) {
        focusState = newFocus
        setFieldState()
    }

    fun setValidationListener(validation: (String) -> Boolean) {
        validationListener = validation
    }

    private fun validateValue() {
        state.value = when {
            inputView.text.toString().isNotBlank() -> {
                val isValid = validationListener?.invoke(inputView.text.toString()) ?: true
                if (isValid) ValueState.VALID else ValueState.INVALID
            }
            else -> {
                ValueState.DEFAULT
            }
        }
    }

    private fun setFieldState() {
        if (focusState) {
            titleLabel.color(R.color.colorPrimary)
            setViewReferenceAttributes(
                color = R.color.colorPrimary,
                height = R.dimen.dimen_2dp)
            return
        }

        when (state.value) {
            ValueState.DEFAULT, ValueState.VALID -> {
                titleLabel.color(R.color.light_gray)
                setViewReferenceAttributes()
            }
            ValueState.INVALID -> {
                titleLabel.color(R.color.purple)
                setViewReferenceAttributes(
                    color = R.color.purple,
                    height = R.dimen.dimen_2dp)
            }
        }
    }

    private fun setViewReferenceAttributes(
        @ColorRes color: Int = R.color.light_gray,
        @DimenRes height: Int = R.dimen.dimen_1dp
    ) {
        viewReference.run {
            backgroundColor(color)
            changeHeight(height)
        }
    }

    enum class ValueState {
        DEFAULT,
        VALID,
        INVALID
    }
}