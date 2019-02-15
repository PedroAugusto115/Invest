package challenge.invest.simulation.repository

import challenge.invest.core.service.ApiResponse
import challenge.invest.core.service.CallbackRequest
import challenge.invest.simulation.service.RemoteData
import challenge.invest.simulation.service.SimulationResponse
import retrofit2.Response

object SimulationRepository {

    fun simulate(
        simulationResponse: ApiResponse<SimulationResponse>,
        investedAmount: Float,
        rate: Int,
        maturityDate: String) {
        RemoteData.getInstance().simulate(
            investedAmount = investedAmount.toLong(),
            rate = rate,
            maturityDate = maturityDate )
            .enqueue(
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