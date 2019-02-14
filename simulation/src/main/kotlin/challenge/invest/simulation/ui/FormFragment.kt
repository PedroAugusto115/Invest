package challenge.invest.simulation.ui


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import challenge.invest.core.extensions.bindView
import challenge.invest.core.extensions.observeNonNull
import challenge.invest.core.viewmodel.provideActivityViewModel
import challenge.invest.simulation.R
import challenge.invest.simulation.viewmodel.SimulationViewModel

class FormFragment : Fragment() {

    private val simulateButton by bindView<Button>(R.id.frag_form_button)

    private val viewModel by provideActivityViewModel(SimulationViewModel::class)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_form, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservables()
        initViews()
    }

    private fun initViews() {
        simulateButton.setOnClickListener {
            viewModel.getSimulation()
        }
    }

    private fun initObservables() {
        viewModel.simulationResponse.data.observeNonNull(this) {
            findNavController().navigate(R.id.action_formFragment_to_resultFragment)
        }

        viewModel.simulationResponse.errorMessage.observeNonNull(this) {
            Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
        }
    }

}
