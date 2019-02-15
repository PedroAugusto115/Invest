package challenge.invest.core.extensions

import java.text.SimpleDateFormat
import java.util.Locale

fun String.toDateDisplay() = this.toDate("yyyy-MM-dd'T'HH:mm:ss", "dd/MM/yyyy")

fun String.toDateServer() = this.toDate("dd/MM/yyyy", "yyyy-MM-dd")

fun String.toDate(input: String, output: String): String {
    val locale = Locale("pt-BR")
    val inputFormat = SimpleDateFormat(input, locale)
    val outputFormat = SimpleDateFormat(output, locale)
    val d = inputFormat.parse(this)
    return outputFormat.format(d)
}

fun String.currencyToServer() {
    this.replace("R$", "").replace(".", "").replace(",", ".")
}