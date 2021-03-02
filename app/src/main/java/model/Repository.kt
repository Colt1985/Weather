package model

interface Repository {
    fun getWeatherServer():Weather
    fun getWeatherFromLocalStorage():Weather
}