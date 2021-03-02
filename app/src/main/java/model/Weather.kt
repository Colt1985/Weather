package model

data class Weather(
    val city: City = getDefaultCity(),
    val temperature: String = "-10",
    val feelsLike: String = "-12",
    var cloudy: String = "Mainly cloudy"
)

fun getDefaultCity() = City("Kirov", 58.60353,49.6668)