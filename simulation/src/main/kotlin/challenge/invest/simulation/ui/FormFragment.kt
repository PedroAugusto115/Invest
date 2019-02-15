package challenge.invest.simulation.ui

import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import challenge.invest.core.extensions.bindView
import challenge.invest.core.extensions.brazilianCurrencyFormat
import challenge.invest.core.extensions.currencyToServer
import challenge.invest.core.extensions.dateFormat
import challenge.invest.core.extensions.hideKeyboard
import challenge.invest.core.extensions.observeNonNull
import challenge.invest.core.extensions.percentFormat
import challenge.invest.core.extensions.toDateServer
import challenge.invest.core.viewmodel.provideActivityViewModel
import challenge.invest.core.widget.DefaultInputText
import challenge.invest.core.widget.ErrorView
import challenge.invest.simulation.R
import challenge.invest.simulation.viewmodel.SimulationViewModel

class FormFragment : Fragment() {

    private val amountEditText by bindView<DefaultInputText>(R.id.frag_form_value)
    private val dueDateEditText by bindView<DefaultInputText>(R.id.frag_form_due_date)
    private val percentEditText by bindView<DefaultInputText>(R.id.frag_form_percent)
    private val simulateButton by bindView<Button>(R.id.frag_form_button)

    private val loadingLayout by bindView<ConstraintLayout>(R.id.view_loading)
    private val contentLayout by bindView<ConstraintLayout>(R.id.frag_form)
    private val errorView by bindView<ErrorView>(R.id.error_view)

    private val viewModel by provideActivityViewModel(SimulationViewModel::class)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_form, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showContent()
        initObservables()
        initViews()
    }

    private fun initViews() {
        amountEditText.inputView.brazilianCurrencyFormat()
        dueDateEditText.inputView.dateFormat()
        percentEditText.inputView.percentFormat()

        simulateButton.setOnClickListener {
            callSimulation()
        }
    }

    private fun initObservables() {
        viewModel.simulationResponse.data.observeNonNull(this) {
            findNavController().navigate(R.id.action_formFragment_to_resultFragment)
        }

        viewModel.simulationResponse.errorMessage.observeNonNull(this) {
            showError()
        }
    }

    private fun callSimulation() {
        val amount = amountEditText.inputView.text.toString().currencyToServer()
        val rate = percentEditText.inputView.text.toString().toInt()
        val date = dueDateEditText.inputView.text.toString().toDateServer()

        simulateButton.hideKeyboard()
        viewModel.getSimulation(amount, rate, date)
        showLoading()
    }

    private fun showContent() {
        loadingLayout.visibility = View.GONE
        contentLayout.visibility = View.VISIBLE
        errorView.visibility = View.GONE
    }

    private fun showLoading() {
        loadingLayout.visibility = View.VISIBLE
        contentLayout.visibility = View.GONE
        errorView.visibility = View.GONE
    }

    private fun showError() {
        loadingLayout.visibility = View.GONE
        contentLayout.visibility = View.GONE
        errorView.run {
            visibility = View.VISIBLE
            onTryAgainClick(::callSimulation)
            onBackButtonClick(::showContent)
        }
    }
}
