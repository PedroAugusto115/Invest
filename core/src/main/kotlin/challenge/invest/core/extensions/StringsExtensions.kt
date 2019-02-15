package challenge.invest.core.extensions

import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

const val HUNDRED = 100

fun String.toDateDisplay() = this.toDate("yyyy-MM-dd'T'HH:mm:ss", "dd/MM/yyyy")

fun String.toDateServer() = this.toDate("dd/MM/yyyy", "yyyy-MM-dd")

fun String.toDate(input: String, output: String): String {
    val locale = Locale("pt-BR")
    val inputFormat = SimpleDateFormat(input, locale)
    val outputFormat = SimpleDateFormat(output, locale)
    val d = inputFormat.parse(this)
    return outputFormat.format(d)
}

fun String.isValidDate() = try {
    val typedDate = SimpleDateFormat("dd/MM/yyyy", Locale("pt-BR")).parse(this)

    typedDate > Date()
} catch (ex: Exception) {
    false
}

fun String.currencyToServer() =
    this.replace("R$", "")
        .replace(".", "")
        .replace(",", "")
        .toFloat() / HUNDRED