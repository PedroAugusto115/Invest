package challenge.invest.simulation.viewmodel

import android.arch.lifecycle.ViewModel
import challenge.invest.core.service.ApiResponse
import challenge.invest.simulation.repository.SimulationRepository
import challenge.invest.simulation.service.SimulationResponse

class SimulationViewModel: ViewModel() {

    val simulationResponse = ApiResponse<SimulationResponse>()

    fun getSimulation() {
        SimulationRepository.simulate(simulationResponse)
    }
}