package challenge.invest.simulation.repository

import challenge.invest.core.service.ApiResponse
import challenge.invest.core.service.CallbackRequest
import challenge.invest.simulation.service.RemoteData
import challenge.invest.simulation.service.SimulationResponse
import retrofit2.Response

object SimulationRepository {

    fun simulate(simulationResponse: ApiResponse<SimulationResponse>) {
        RemoteData.getInstance().simulate(investedAmount = 32323, rate = 123, maturityDate = "2023-03-03" ).enqueue(
            object : CallbackRequest<SimulationResponse>() {
                override fun success(response: Response<SimulationResponse>) {
                    simulationResponse.data.postValue(response.body()!!)
                }

                override fun failureHttp(response: Response<SimulationResponse>?) {
                    simulationResponse.errorMessage.postValue(response?.message())
                }

                override fun failure(throwable: Throwable) {
                    simulationResponse.errorMessage.postValue(throwable.message)
                }

            }
        )
    }

}