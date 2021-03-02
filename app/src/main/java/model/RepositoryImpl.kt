package model

class RepositoryImpl: Repository {
    override fun getWeatherServer():Weather{
        return Weather()
    }
    override fun getWeatherFromLocalStorage(): Weather{
        return Weather()
    }
}