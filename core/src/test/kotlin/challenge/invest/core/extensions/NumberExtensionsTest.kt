package challenge.invest.core.extensions

import org.junit.Assert
import org.junit.Test

class NumberExtensionsTest {

    @Test
    fun shouldFormatFloatToBrazilianCurrencyString() {
        val expected = "BRL 2,900,00"
        val result = 2900.0.toFloat().toBrazilianCurrency()

        Assert.assertEquals(expected, result)
    }

    @Test
    fun shouldFormatFloatPercent() {
        val expected = "100,00%"
        val result = 100.0.toFloat().toPercent()

        Assert.assertEquals(expected, result)
    }

    @Test
    fun shouldFormatFloatPercentWithDecimals() {
        val expected = "95,45%"
        val result = 95.45.toFloat().toPercent()

        Assert.assertEquals(expected, result)
    }

    @Test
    fun shouldFormatFloatPercentWithMoreThanTwoDecimals() {
        val expected = "95,45%"
        val result = 95.45345.toFloat().toPercent()

        Assert.assertEquals(expected, result)
    }
}