package challenge.invest.simulation.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import challenge.invest.core.extensions.bindView
import challenge.invest.core.extensions.toBrazilianCurrency
import challenge.invest.core.extensions.toDateDisplay
import challenge.invest.core.extensions.toPercent
import challenge.invest.core.viewmodel.provideActivityViewModel
import challenge.invest.core.widget.LabeledValueView
import challenge.invest.simulation.R
import challenge.invest.simulation.viewmodel.SimulationViewModel

class ResultFragment : Fragment() {

    private val viewModel by provideActivityViewModel(SimulationViewModel::class)

    private val totalValue by bindView<TextView>(R.id.txt_result_total_value)
    private val totalRevenue by bindView<TextView>(R.id.txt_result_total_revenue)
    private val lblInitialValue by bindView<LabeledValueView>(R.id.lbl_initial_value)
    private val lblTotalValue by bindView<LabeledValueView>(R.id.lbl_total_value)
    private val lblRevenueValue by bindView<LabeledValueView>(R.id.lbl_revenue_value)
    private val lblTaxValue by bindView<LabeledValueView>(R.id.lbl_tax_value)
    private val lblNetValue by bindView<LabeledValueView>(R.id.lbl_net_value)
    private val lblDate by bindView<LabeledValueView>(R.id.lbl_date)
    private val lblCountedDaye by bindView<LabeledValueView>(R.id.lbl_counted_days)
    private val lblMonthlyRevenue by bindView<LabeledValueView>(R.id.lbl_monthly_revenue)
    private val lblCDIPercent by bindView<LabeledValueView>(R.id.lbl_cdi_percent)
    private val lblAnnualRevenue by bindView<LabeledValueView>(R.id.lbl_annual_revenue)
    private val lblPeriodProfitability by bindView<LabeledValueView>(R.id.lbl_period_profitability)

    private val backButton by bindView<Button>(R.id.frag_result_button)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_result, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.simulationResponse.data.value?.let {
            totalValue.text = it.grossAmount.toBrazilianCurrency()
            totalRevenue.text = getString(R.string.frag_result_description, it.grossAmountProfit.toBrazilianCurrency())
            lblInitialValue.setText(it.investmentParameter.investedAmount.toBrazilianCurrency())
            lblTotalValue.setText(it.grossAmount.toBrazilianCurrency())
            lblRevenueValue.setText(it.grossAmountProfit.toBrazilianCurrency())
            lblTaxValue.setText("${it.taxesAmount.toBrazilianCurrency()}(${it.taxesRate.toPercent()})")
            lblNetValue.setText(it.netAmount.toBrazilianCurrency())
            lblDate.setText(it.investmentParameter.maturityDate.toDateDisplay())
            lblCountedDaye.setText(it.investmentParameter.maturityTotalDays.toString())
            lblMonthlyRevenue.setText(it.monthlyGrossRateProfit.toPercent())
            lblCDIPercent.setText(it.investmentParameter.rate.toPercent())
            lblAnnualRevenue.setText(it.annualGrossRateProfit.toPercent())
            lblPeriodProfitability.setText(it.annualNetRateProfit.toPercent())
        }

        backButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}
