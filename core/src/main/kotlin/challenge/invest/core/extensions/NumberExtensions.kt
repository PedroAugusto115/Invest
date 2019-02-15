package challenge.invest.core.extensions

import java.text.NumberFormat
import java.util.Locale

private const val START_RANGE = 3
private const val END_RANGE = 2

fun Float.toBrazilianCurrency() = NumberFormat
        .getCurrencyInstance(Locale("pt_BR", "BR"))
        .format(this).run {
            replaceRange(
                length - START_RANGE,
                length - END_RANGE,
                ",")
        }

fun Float.toPercent() = String.format("%.2f", this).plus("%")