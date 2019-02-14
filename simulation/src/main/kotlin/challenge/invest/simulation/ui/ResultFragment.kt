package challenge.invest.simulation.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import challenge.invest.core.viewmodel.provideActivityViewModel
import challenge.invest.simulation.R
import challenge.invest.simulation.viewmodel.SimulationViewModel

class ResultFragment : Fragment() {

    private val viewModel by provideActivityViewModel(SimulationViewModel::class)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_result, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Toast.makeText(
            activity,
            viewModel.simulationResponse.data.value?.annualGrossRateProfit.toString(),
            Toast.LENGTH_SHORT).show()
    }
}
