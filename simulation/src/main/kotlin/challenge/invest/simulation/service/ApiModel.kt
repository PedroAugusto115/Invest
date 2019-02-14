package challenge.invest.simulation.service

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SimulationResponse(
    @SerializedName("investmentParameter") val investmentParameter: InvestmentResponse,
    @SerializedName("grossAmount") val grossAmount: Float,
    @SerializedName("netAmount") val netAmount: Float,
    @SerializedName("grossAmountProfit") val grossAmountProfit: Float,
    @SerializedName("netAmountProfit") val netAmountProfit: Float,
    @SerializedName("annualGrossRateProfit") val annualGrossRateProfit: Float,
    @SerializedName("monthlyGrossRateProfit") val monthlyGrossRateProfit: Float,
    @SerializedName("dailyGrossRateProfit") val dailyGrossRateProfit: Float,
    @SerializedName("taxesRate") val taxesRate: Float,
    @SerializedName("rateProfit") val rateProfit: Float,
    @SerializedName("annualNetRateProfit") val annualNetRateProfit: Float
) : Parcelable

@Parcelize
data class InvestmentResponse(
    @SerializedName("investedAmount") val investedAmount: Float,
    @SerializedName("yearlyInterestRate") val yearlyInterestRate: Float,
    @SerializedName("maturityTotalDays") val maturityTotalDays: Int,
    @SerializedName("maturityBusinessDays") val maturityBusinessDays: Int,
    @SerializedName("maturityDate") val maturityDate: String,
    @SerializedName("rate") val rate: Float,
    @SerializedName("isTaxFree") val isTaxFree: Boolean
) : Parcelable