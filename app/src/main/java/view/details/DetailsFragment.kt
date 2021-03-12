package view.details

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.weather.R
import com.example.weather.databinding.FragmentDetailsBinding
import model.Weather
import model.WeatherDTO

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var weatherBundle: Weather
    private val onLoadListener: WeatherLoader.WeatherLoaderListener =
        object : WeatherLoader.WeatherLoaderListener {

            override fun onLoaded(weatherDTO: WeatherDTO) {
                displayWeather(weatherDTO)
            }

            override fun onFailed(throwable: Throwable) {

            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        weatherBundle = arguments?.getParcelable(BUNDLE_EXTRA) ?: Weather()

        binding.mainView.visibility = View.GONE
        binding.loadingLayout.visibility = View.VISIBLE
        val loader = WeatherLoader(onLoadListener, weatherBundle.city.lat, weatherBundle.city.lon)
        loader.loadWeather()
    }

    private fun displayWeather(weatherDTO: WeatherDTO) {
        with(binding) {
            mainView.visibility = View.VISIBLE
            loadingLayout.visibility = View.GONE
            val city = weatherBundle.city
            cityName.text = city.city
            cityCoordinates.text = String.format(
                getString(R.string.city_coordinates),
                city.lat.toString(),
                city.lon.toString()
            )
            tvConditionTextView.text = weatherDTO.fact?.condition
            temperatureValue.text = weatherDTO.fact?.temp.toString()
            feelsLikeValue.text = weatherDTO.fact?.feels_like.toString()

            when(weatherDTO.fact?.condition){
                "clear" -> ivIcon.setImageResource(R.drawable.clear)
                "cloudy" -> ivIcon.setImageResource(R.drawable.cloudy)
                "continuous-heavy-rain" -> ivIcon.setImageResource(R.drawable.snow)
                "drizzle" -> ivIcon.setImageResource(R.drawable.drizzle)
                "hail" -> ivIcon.setImageResource(R.drawable.snow)
                "heavy-rain" -> ivIcon.setImageResource(R.drawable.snow)
                "light-rain" -> ivIcon.setImageResource(R.drawable.drizzle)
                "light-snow" -> ivIcon.setImageResource(R.drawable.lightsnow)
                "moderate-rain" -> ivIcon.setImageResource(R.drawable.rain)
                "overcast" -> ivIcon.setImageResource(R.drawable.overcast)
                "partly-cloudy" -> ivIcon.setImageResource(R.drawable.partlycloudy)
                "rain" -> ivIcon.setImageResource(R.drawable.rain)
                "snow" -> ivIcon.setImageResource(R.drawable.snow)
                "showers" -> ivIcon.setImageResource(R.drawable.snowers)
                "snow-showers" -> ivIcon.setImageResource(R.drawable.snow)
                "thunderstorm" -> ivIcon.setImageResource(R.drawable.thunderstorm)
                "thunderstorm-with-hail" -> ivIcon.setImageResource(R.drawable.thunderstorm)
                "thunderstorm-with-rain" -> ivIcon.setImageResource(R.drawable.thunderstorm)
            }
            tvWindDir.text = when(weatherDTO.fact?.wind_dir) {
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
        }
    }
    override
    fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        const val BUNDLE_EXTRA = "weather"

        fun newInstance(bundle: Bundle): DetailsFragment {
            val fragment = DetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}



