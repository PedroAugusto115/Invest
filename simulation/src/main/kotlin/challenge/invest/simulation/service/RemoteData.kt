package challenge.invest.simulation.service

import challenge.invest.core.BuildConfig
import challenge.invest.core.service.OkHttpProvider

internal object RemoteData {

    private var instance: SimulationApi? = null

    fun getInstance(url: String = BuildConfig.SERVICE_URL) = instance ?: synchronized(this) {
            instance
                ?: SimulationApi
                    .build(url, OkHttpProvider.getInstance())
                    .also { instance = it }
        }

}