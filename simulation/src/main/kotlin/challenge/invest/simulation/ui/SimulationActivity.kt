package challenge.invest.simulation.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import challenge.invest.core.viewmodel.provideSharedViewModel
import challenge.invest.simulation.R
import challenge.invest.simulation.viewmodel.SimulationViewModel

class SimulationActivity : AppCompatActivity() {

    private val navController by lazy { findNavController(R.id.fragment_container) }
    private val viewModel by provideSharedViewModel(SimulationViewModel::class)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simulation)
    }

    override fun onSupportNavigateUp() = navController.navigateUp()
}
