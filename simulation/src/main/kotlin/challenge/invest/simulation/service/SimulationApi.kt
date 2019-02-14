package challenge.invest.simulation.service

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface SimulationApi {

    @GET("/calculator/simulate")
    fun simulate(@Query("investedAmount") investedAmount: Long,
                 @Query("index") index: String = "CDI",
                 @Query("rate") rate: Int,
                 @Query("isTaxFree") isTaxFree: Boolean = false,
                 @Query("maturityDate") maturityDate: String): Call<SimulationResponse>

    companion object {
        internal fun build(url: String, okHttpClient: OkHttpClient) : SimulationApi {
            return Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create()))
                .client(okHttpClient)
                .build()
                .create(SimulationApi::class.java)
        }
    }
}