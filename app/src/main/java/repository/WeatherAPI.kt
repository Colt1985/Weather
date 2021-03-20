package repository


import model.WeatherDTO
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface WeatherAPI {
    @GET("v2/forecast")
    fun getWeather(
        @Header("X-Yandex-API-Key") token: String,
        @Query("lat") lat: Double,
        @Query("lon") lon: Double
    ): retrofit2.Call<WeatherDTO>
}