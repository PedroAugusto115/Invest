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
import android.widget.EditText
import android.widget.TextView
import java.text.NumberFormat
import java.util.Locale

private const val START_RANGE = 3
private const val END_RANGE = 2
private const val HUNDRED = 100

private const val PERCENT_MAX_LENGTH = 4
private const val DATE_MAX_LENGTH = 10

private const val DATE_FIRST_SLASH_POSITION = 2
private const val DATE_LAST_SLASH_POSITION = 5

fun View.hideKeyboard() {
    val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

fun ViewGroup.inflate(@LayoutRes layout: Int, attachToRoot: Boolean = true) =
    LayoutInflater
        .from(context)
        .inflate(layout, this, attachToRoot)!!

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

fun EditText.brazilianCurrencyFormat() {

    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            //
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            //
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            this@brazilianCurrencyFormat.removeTextChangedListener(this)

            var formatted = ""
            val cleanString = s.toString()
                .replace("R$", "")
                .replace(",", "")
                .replace(".", "")

            if (!cleanString.isEmpty()) {
                val parsed = cleanString.toDouble()
                formatted = NumberFormat
                    .getCurrencyInstance(Locale("pt_BR", "BR"))
                    .format(parsed / HUNDRED)

                formatted = formatted.run {
                    replaceRange(length - START_RANGE, length - END_RANGE, ",")
                }
            }

            this@brazilianCurrencyFormat.setText(formatted)
            this@brazilianCurrencyFormat.setSelection(formatted.length)
            this@brazilianCurrencyFormat.addTextChangedListener(this)
        }
    })
}

fun EditText.dateFormat() {

    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            //
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            //
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            this@dateFormat.removeTextChangedListener(this)
            var date = s.toString()
            if ((before == 0) &&
                (date.length == DATE_FIRST_SLASH_POSITION ||
                        date.length == DATE_LAST_SLASH_POSITION)) {
                date += "/"
            }

            if (date.length > DATE_MAX_LENGTH) {
                date = date.removeRange(DATE_MAX_LENGTH - 1, DATE_MAX_LENGTH)
            }
            this@dateFormat.setText(date)
            this@dateFormat.setSelection(date.length)
            this@dateFormat.addTextChangedListener(this)
        }
    })
}

fun EditText.percentFormat() {

    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            //
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            //
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            this@percentFormat.removeTextChangedListener(this)
            var percent = s.toString().replace("%", "")
            if (percent.length == PERCENT_MAX_LENGTH) {
                percent = percent.removeRange(PERCENT_MAX_LENGTH - 1, PERCENT_MAX_LENGTH)
            }

            this@percentFormat.setText(percent)
            this@percentFormat.setSelection(percent.length)
            this@percentFormat.addTextChangedListener(this)
        }
    })
}