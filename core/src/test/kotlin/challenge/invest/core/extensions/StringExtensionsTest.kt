package challenge.invest.core.extensions

import org.junit.Assert
import org.junit.Test

class StringExtensionsTest {

    @Test
    fun shouldFormatStringToDataDisplay() {
        val expected = "15/02/2019"
        val result = "2019-02-15T00:00:00".toDateDisplay()

        Assert.assertEquals(expected, result)
    }

    @Test
    fun shouldFormatDataDisplayToDataServer() {
        val expected = "2019-02-15"
        val result = "15/02/2019".toDateServer()

        Assert.assertEquals(expected, result)
    }

    @Test
    fun isValidDate() {
        Assert.assertTrue("15/02/2100".isValidDate())
    }

    @Test
    fun pastDateIsInvalidDate() {
        Assert.assertFalse("15/02/2010".isValidDate())
    }

    @Test
    fun incorrectFormatDateIsInvalidDate() {
        Assert.assertFalse("2100-02-15".isValidDate())
    }

    @Test
    fun shouldFormatCurrencyAsServerFormat() {
        val expected = 2900.0.toFloat()
        val result = "R$2900,00".currencyToServer()

        Assert.assertEquals(expected, result)
    }

    @Test
    fun shouldFormatCurrencyAsServerFormatWithCents() {
        val expected = 2900.34.toFloat()
        val result = "R$2900,34".currencyToServer()

        Assert.assertEquals(expected, result)
    }
}