package repository

import model.Weather
import model.getRussianCities
import model.getWorldCities

class MainRepositoryImpl :
    MainRepository {

    override fun getWeatherFromServer() =
        Weather()

    override fun getWeatherFromLocalStorageRus() =
        getRussianCities()

    override fun getWeatherFromLocalStorageWorld() =
        getWorldCities()
}
