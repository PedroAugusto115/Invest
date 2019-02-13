package challenge.invest.core.extensions

import android.app.Activity
import android.support.annotation.ColorRes
import android.support.annotation.DimenRes
import android.support.annotation.LayoutRes
import android.support.v4.content.ContextCompat
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView

fun View.hideKeyboard() {
    val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

fun ViewGroup.inflate(@LayoutRes layout: Int, attachToRoot: Boolean = true) =
    LayoutInflater
        .from(context)
        .inflate(layout, this, attachToRoot)

fun TextView.color(@ColorRes color: Int) {
    setTextColor(ContextCompat.getColor(context, color))
}

fun View.backgroundColor(@ColorRes color: Int) {
    setBackgroundColor(ContextCompat.getColor(context, color))
}

fun View.changeHeight(@DimenRes newHeight: Int) {
    layoutParams = (this.layoutParams as ViewGroup.MarginLayoutParams).apply {
        height = resources.getDimension(newHeight).toInt()
    }
}

fun TextView.addTextChanged(
    beforeTextChanged: ((String, Int, Int, Int) -> Unit)? = null,
    onTextChanged: ((String, Int, Int, Int) -> Unit)? = null,
    afterTextChanged: ((String) -> Unit)? = null
) {
    addTextChangedListener(object : TextWatcher {

        override fun beforeTextChanged(value: CharSequence, start: Int, count: Int, after: Int) {
            beforeTextChanged?.invoke(value.toString(), start, count, after)
        }

        override fun onTextChanged(value: CharSequence, start: Int, before: Int, count: Int) {
            onTextChanged?.invoke(value.toString(), start, before, count)
        }

        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged?.invoke(editable.toString())
        }
    })
}