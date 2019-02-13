package challenge.invest.simulation.ui


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import challenge.invest.core.extensions.bindView
import challenge.invest.simulation.R

class FormFragment : Fragment() {

    private val simulateButton by bindView<Button>(R.id.frag_form_button)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_form, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        simulateButton.setOnClickListener {
            findNavController().navigate(R.id.action_formFragment_to_resultFragment)
        }
    }

}
