package model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Weather(
    val city: City = getDefaultCity(),
    val temperature: Int = 20,
    val feelsLike: Int = 25,
    val condition: String = "",
    val wind_dir: String? = "",
    val wind_speed: Double = 1.0,
    val icon: String? = "bkn_n",
    val pressure_mm: Int? = 700,
) :Parcelable

fun getDefaultCity() = City("Kirov", 58.60353,49.6668)

fun getWorldCities()= listOf(
        Weather(City("Лондон", 51.5085300, -0.1257400), 1, 2,"Clear"),
        Weather(City("Токио", 35.6895000, 139.6917100), 3, 4, "Rain"),
        Weather(City("Париж", 48.8534100, 2.3488000), 5, 6,"Mainly Cloudy"),
        Weather(City("Берлин", 52.52000659999999, 13.404953999999975), 7, 8,"Rain"),
        Weather(City("Рим", 41.9027835, 12.496365500000024), 9, 10,"Sunny"),
        Weather(City("Минск", 53.90453979999999, 27.561524400000053), 11, 12,"Sunny"),
        Weather(City("Стамбул", 41.0082376, 28.97835889999999), 13, 14,"Sunny"),
        Weather(City("Вашингтон", 38.9071923, -77.03687070000001), 15, 16,"Rain"),
        Weather(City("Киев", 50.4501, 30.523400000000038), 17, 18,"Sunny"),
        Weather(City("Пекин", 39.90419989999999, 116.40739630000007), 19, 20,"Mainly Cloudy")
    )

fun getRussianCities() = listOf(
        Weather(City("Киров", 58.60353,49.6668), 1, 2,"Mainly Cloudy"),
        Weather(City("Москва", 55.755826, 37.617299900000035), 1, 2,"Mainly Cloudy"),
        Weather(City("Санкт-Петербург", 59.9342802, 30.335098600000038), 3, 3,"Sunny"),
        Weather(City("Новосибирск", 55.00835259999999, 82.93573270000002), 5, 6,"Rain"),
        Weather(City("Екатеринбург", 56.83892609999999, 60.60570250000001), 7, 8,"Mainly Cloudy"),
        Weather(City("Нижний Новгород", 56.2965039, 43.936059), 9, 10,"Sunny"),
        Weather(City("Казань", 55.8304307, 49.06608060000008), 11, 12,"Rain"),
        Weather(City("Челябинск", 55.1644419, 61.4368432), 13, 14,"Sunny"),
        Weather(City("Омск", 54.9884804, 73.32423610000001), 15, 16,"Rain"),
        Weather(City("Ростов-на-Дону", 47.2357137, 39.701505), 17, 18,"Sunny"),
        Weather(City("Уфа", 54.7387621, 55.972055400000045), 19, 20,"Rain")
    )
