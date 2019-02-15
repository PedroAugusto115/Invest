package challenge.invest.simulation.viewmodel

import challenge.invest.core.service.ApiResponse
import challenge.invest.core.viewmodel.BaseViewModel
import challenge.invest.simulation.repository.SimulationRepository
import challenge.invest.core.service.SimulationResponse

class SimulationViewModel: BaseViewModel() {

    val simulationResponse = ApiResponse<SimulationResponse>()

    fun getSimulation(investedAmount: Float, rate: Int, maturityDate: String) {
        SimulationRepository.simulate(simulationResponse, investedAmount, rate, maturityDate)
    }
}