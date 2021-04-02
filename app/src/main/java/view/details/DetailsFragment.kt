package view.details


import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import utils.showSnackBar
import viewmodel.DetailsViewModel
import com.example.weather.R
import com.example.weather.databinding.FragmentDetailsBinding
import model.Weather
import app.AppState
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import model.City


class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var weatherBundle: Weather
    private val viewModel: DetailsViewModel by lazy { ViewModelProvider(this).get(DetailsViewModel::class.java) }


//    private val onLoadListener: WeatherLoader.WeatherLoaderListener =
//        object : WeatherLoader.WeatherLoaderListener {
//
//            override fun onLoaded(weatherDTO: WeatherDTO) {
//                displayWeather(weatherDTO)
//            }
//
//            override fun onFailed(throwable: Throwable) {
//
//            }
//        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        weatherBundle = arguments?.getParcelable(BUNDLE_EXTRA) ?: Weather()
        viewModel.detailsLiveData.observe(viewLifecycleOwner, { renderData(it) })
        viewModel.getWeatherFromRemoteSource(weatherBundle.city.lat, weatherBundle.city.lon)
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                binding.mainView.visibility = View.VISIBLE
                binding.includedLoadingLayout.loadingLayout.visibility = View.GONE
                setWeather(appState.weatherData[0])
            }
            is AppState.Loading -> {
                binding.mainView.visibility = View.GONE
                binding.includedLoadingLayout.loadingLayout.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                binding.mainView.visibility = View.VISIBLE
                binding.includedLoadingLayout.loadingLayout.visibility = View.GONE
                binding.mainView.showSnackBar(
                    getString(R.string.error),
                    getString(R.string.reload),
                    {
                        viewModel.getWeatherFromRemoteSource(
                            weatherBundle.city.lat,
                            weatherBundle.city.lon
                        )
                    })
            }
        }
    }

    private fun setWeather(weather: Weather) {
        val speed = getString(R.string.ms)
        val pres = getString(R.string.pres)
        with(binding) {
            val city = weatherBundle.city
            saveCity(city, weather)

            cityName.text = city.city
            cityCoordinates.text = String.format(
                getString(R.string.city_coordinates),
                city.lat.toString(),
                city.lon.toString()
            )

            weather.icon?.let {
                GlideToVectorYou.justLoadImage(
                    activity,
                    Uri.parse("https://yastatic.net/weather/i/icons/blueye/color/svg/${it}.svg"),
                    ivIcon
                )
                temperatureValue.text = weather.temperature.toString()
                feelsLikeValue.text = weather.feelsLike.toString()
                //tvConditionTextView.text = weather.condition
                // tvWindDir.text = weather.wind_dir
                val text = weather.wind_speed.toString()
                tvWindSpeed.text = "$text $speed"
                val pressure = weather.pressure_mm.toString()
                tvPressureMm.text = "$pressure $pres"
                tvWindDir.text = when (weather.wind_dir) {
                    "nw" -> getString(R.string.nw)
                    "n" -> getString(R.string.n)
                    "ne" -> getString(R.string.ne)
                    "e" -> getString(R.string.e)
                    "se" -> getString(R.string.se)
                    "s" -> getString(R.string.s)
                    "sw" -> getString(R.string.sw)
                    "w" -> getString(R.string.w)
                    else -> getString(R.string.c)
                }
                tvConditionTextView.text = when (weather.condition) {
                    "clear" -> getString(R.string.clear)
                    "cloudy" -> getString(R.string.cloudy)
                    "continuous-heavy-rain" -> getString(R.string.continuous_heavy_rain)
                    "drizzle" -> getString(R.string.drizzle)
                    "hail" -> getString(R.string.hail)
                    "heavy-rain" -> getString(R.string.heavy_rain)
                    "light-rain" -> getString(R.string.light_rain)
                    "light-snow" -> getString(R.string.light_snow)
                    "moderate-rain" -> getString(R.string.moderate_rain)
                    "overcast" -> getString(R.string.overcast)
                    "partly-cloudy" -> getString(R.string.partly_cloudy)
                    "rain" -> getString(R.string.rain)
                    "snow" -> getString(R.string.snow)
                    "showers" -> getString(R.string.snowers)
                    "snow-showers" -> getString(R.string.snow_showers)
                    "thunderstorm" -> getString(R.string.thunderstorm)
                    "thunderstorm-with-hail" -> getString(R.string.thunderstorm_with_hail)
                    else -> getString(R.string.thunderstorm_with_rain)
                }
                tvSeason.text = when (weather.season) {
                    "summer" -> getString(R.string.summer)
                    "autumn" -> getString(R.string.autumn)
                    "winter" -> getString(R.string.winter)
                    else -> getString(R.string.spring)
                }
            }


//            Picasso
//                .get()
//                .load("https://freepngimg.com/thumb/city/36275-3-city-hd.png")
//                .into(ivIcon)
        }
    }

//        binding.mainView.visibility = View.GONE
//        binding.loadingLayout.visibility = View.VISIBLE
//        val loader = WeatherLoader(onLoadListener, weatherBundle.city.lat, weatherBundle.city.lon)
//        loader.loadWeather()
//    }

//    @SuppressLint("SetTextI18n")
//    private fun displayWeather(weatherDTO: WeatherDTO) {
//        with(binding) {
//            mainView.visibility = View.VISIBLE
//            loadingLayout.visibility = View.GONE
//            val city = weatherBundle.city
//            val speed = getString(R.string.ms)
//            val pres = getString(R.string.pres)
//            cityName.text = city.city
//            cityCoordinates.text = String.format(
//                getString(R.string.city_coordinates),
//                city.lat.toString(),
//                city.lon.toString()
//            )
//            tvConditionTextView.text = weatherDTO.fact?.condition
//            temperatureValue.text = weatherDTO.fact?.temp.toString()
//            feelsLikeValue.text = weatherDTO.fact?.feels_like.toString()
//            val text = weatherDTO.fact?.wind_speed.toString()
//            tvWindSpeed.text = "$text $speed"
//            val pressure = weatherDTO.fact?.pressure_mm.toString()
//            tvPressureMm.text = "$pressure $pres"
//
//            when (weatherDTO.fact?.condition) {
//                "clear" -> ivIcon.setImageResource(R.drawable.clear)
//                "cloudy" -> ivIcon.setImageResource(R.drawable.cloudy)
//                "continuous-heavy-rain" -> ivIcon.setImageResource(R.drawable.snow)
//                "drizzle" -> ivIcon.setImageResource(R.drawable.drizzle)
//                "hail" -> ivIcon.setImageResource(R.drawable.snow)
//                "heavy-rain" -> ivIcon.setImageResource(R.drawable.snow)
//                "light-rain" -> ivIcon.setImageResource(R.drawable.drizzle)
//                "light-snow" -> ivIcon.setImageResource(R.drawable.lightsnow)
//                "moderate-rain" -> ivIcon.setImageResource(R.drawable.rain)
//                "overcast" -> ivIcon.setImageResource(R.drawable.overcast)
//                "partly-cloudy" -> ivIcon.setImageResource(R.drawable.partlycloudy)
//                "rain" -> ivIcon.setImageResource(R.drawable.rain)
//                "snow" -> ivIcon.setImageResource(R.drawable.snow)
//                "showers" -> ivIcon.setImageResource(R.drawable.snowers)
//                "snow-showers" -> ivIcon.setImageResource(R.drawable.snow)
//                "thunderstorm" -> ivIcon.setImageResource(R.drawable.thunderstorm)
//                "thunderstorm-with-hail" -> ivIcon.setImageResource(R.drawable.thunderstorm)
//                "thunderstorm-with-rain" -> ivIcon.setImageResource(R.drawable.thunderstorm)
//            }
//            tvWindDir.text = when (weatherDTO.fact?.wind_dir) {
//                "nw" -> getString(R.string.nw)
//                "n" -> getString(R.string.n)
//                "ne" -> getString(R.string.ne)
//                "e" -> getString(R.string.e)
//                "se" -> getString(R.string.se)
//                "s" -> getString(R.string.s)
//                "sw" -> getString(R.string.sw)
//                "w" -> getString(R.string.w)
//                else -> getString(R.string.c)
//            }
//        }
//    }


    companion object {

        const val BUNDLE_EXTRA = "weather"

        fun newInstance(bundle: Bundle): DetailsFragment {
            val fragment = DetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun saveCity(
        city: City,
        weather: Weather
    ) {
        viewModel.saveCityToDB(
            Weather(
                city,
                weather.temperature,
                weather.feelsLike,
                weather.condition
            )
        )
    }
}


