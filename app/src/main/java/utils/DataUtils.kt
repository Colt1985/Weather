package com.example.androidwithkotlin.utils


import model.FactDTO
import model.Weather
import model.WeatherDTO
import model.getDefaultCity

fun convertDtoToModel(weatherDTO: WeatherDTO): List<Weather> {
    val fact: FactDTO = weatherDTO.fact!!
    return listOf(Weather(getDefaultCity(), fact.temp!!, fact.feels_like!!, fact.condition!!, fact.wind_dir!!,fact.wind_speed!!,fact.icon,fact.pressure_mm!!))
}
