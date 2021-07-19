package com.example.hm_view_model.ui.main

import android.content.Intent
import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import com.example.hm_view_model.R
import com.example.hm_view_model.databinding.MainFragmentBinding
import com.example.hm_view_model.model.ApplState
import com.example.hm_view_model.model.City
import com.example.hm_view_model.model.Weather
import com.example.hm_view_model.model.WeatherDTO
import com.example.hm_view_model.services.MAIN_SERVICE_STRING_EXTRA
import com.example.hm_view_model.services.MainService
//import com.example.hm_view_model.services.TAG
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.MalformedURLException
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

private const val WEATHER_API_KEY = "a57ed224-c226-4ccb-bd2b-500720e134ba"
class MainFragment : Fragment() {

    private lateinit var binding: MainFragmentBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var weatherBundle: Weather

    companion object {
        const val BUNDLE_EXTRA = "weather"
        //fun newInstance() = MainFragment()
        fun newInstance(bundle: Bundle): MainFragment {
            val frag = MainFragment()
            frag.arguments = bundle
            return frag
        }


    }

    /*private fun saveCity(
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
    }*/


    private fun displayWeather(weatherDTO: WeatherDTO) {
        with(binding) {
            main.visibility = View.VISIBLE
            loadLayout.visibility = View.GONE
            val city = weatherBundle.city
            cityName.text = city.city
            cityCoordinates.text = String.format(
                getString(R.string.city_coordinates),
                city.lat.toString(),
                city.lon.toString()
            )
            weatherCondition.text = weatherDTO.fact?.condition
            temperatureValue.text = weatherDTO.fact?.temp.toString()
            feelsLikeValue.text = weatherDTO.fact?.feels_like.toString()
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    public fun loadWeather() {
        try {
            val uri =
                URL("https://api.weather.yandex.ru/v2/informers?lat=${weatherBundle.city.lat}&lon=${weatherBundle.city.lon}")
            val handler = Handler()
            Thread(Runnable {
                lateinit var urlConnection: HttpsURLConnection
                try {
                    urlConnection = uri.openConnection() as HttpsURLConnection
                    urlConnection.requestMethod = "GET"
                    urlConnection.addRequestProperty(
                        "X-Yandex-API-Key",
                        WEATHER_API_KEY
                    )
                    urlConnection.readTimeout = 10000
                    val bufferedReader =
                        BufferedReader(InputStreamReader(urlConnection.inputStream))

                    // преобразование ответа от сервера (JSON) в модель данных (WeatherDTO)
                    val weatherDTO: WeatherDTO =
                        Gson().fromJson(getLines(bufferedReader), WeatherDTO::class.java)
                    handler.post { displayWeather(weatherDTO) }
                } catch (e: Exception) {
                    Log.e("", "Fail connection", e)
                    e.printStackTrace()
                    //Обработка ошибки
                } finally {
                    urlConnection.disconnect()
                }
            }).start()
        } catch (e: MalformedURLException) {
            Log.e("", "Fail URI", e)
            e.printStackTrace()
            //Обработка ошибки
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun getLines(reader: BufferedReader): String {
        return reader.lines().collect(Collectors.joining("\n"))
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //return inflater.inflate(R.layout.main_fragment, container, false)
        binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //val observe = Observer<Any> {renderData(it)}
        //viewModel.getData().observe(viewLifecycleOwner, observe)
        //viewModel.getWeather()
        /*viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        val observe = Observer<ApplState> {renderData(it)}
        viewModel.getData().observe(viewLifecycleOwner, observe)
        viewModel.getWeatherFromLocalRusSource()*/
        val weather = arguments?.getParcelable<Weather>(BUNDLE_EXTRA)
        if (weather != null) {
            val city = weather.city
            binding.cityName.text = city.city
            binding.cityCoordinates.text = String.format(
                getString(R.string.city_coordinates),
                city.lat.toString(),
                city.lon.toString()
            )
            binding.temperatureValue.text = weather.temperature.toString()
            binding.feelsLikeValue.text = weather.feelsLike.toString()
        }//оставил потому что онлайн пока не срабатывает

        weatherBundle = arguments?.getParcelable(BUNDLE_EXTRA) ?: Weather()
      //  binding.main.visibility = View.GONE
      //  binding.loadLayout.visibility = View.VISIBLE
        //loadWeather()
        Picasso
            .get()
            .load("https://res.cloudinary.com/demo/video/upload/dog.png")
            ;//.into(куда грузить)

        Log.d("111", "111")
        initServiceButton()
        //initServiceWithBroadcastButton()
    }

    //эксперимент с broadcastReciever

    /*private fun initServiceWithBroadcastButton() {
        binding.serviceWithBroadcastButton.setOnClickListener {
            context?.let {
                it.startService(Intent(it, MainService::class.java).apply {
                    putExtra(
                        MAIN_SERVICE_INT_EXTRA,
                        binding.editText.text.toString().toInt()
                    )
                })
            }
        }
    }*/


    private fun initServiceButton() {
        binding.btnStartService.setOnClickListener {
            context?.let {
                it.startService(Intent(it, MainService::class.java).apply {
                    putExtra(
                        MAIN_SERVICE_STRING_EXTRA,
                        getString(R.string.app_name)
                    )
                })
            }
        }
    }


    //private fun renderData(data: Any) {
    private fun renderData(data: ApplState) = with(binding) {
        Toast.makeText(context, "111", Toast.LENGTH_SHORT).show()
        when(data){
            is ApplState.Good -> {
                loadLayout.visibility = View.GONE
                //mainView ?????
                Snackbar.make(main, "Good", Snackbar.LENGTH_LONG).show()
                val weather = data.weatherData
               // setData(weather) //????
            }
            is ApplState.Bad -> {
                loadLayout.visibility = View.GONE }
            is ApplState.Middle -> {
                loadLayout.visibility = View.VISIBLE
                Snackbar
                    //mainView ?????
                    .make(main, "Bad", Snackbar.LENGTH_INDEFINITE)
                    //.setAction("Reload") {viewModel.getWeather(f)}
                    .setAction("Reload") {viewModel.getWeatherFromLocalRusSource()}
                    .show()
            }
        }
    }

    private fun setData(weather: Weather) = with(binding) {
        cityName.text = weather.city.city
        cityCoordinates.text = String.format(
            getString(R.string.city_coordinates),
            weather.city.lat.toString(),
            weather.city.lon.toString()
        )
        temperatureValue.text = weather.temperature.toString()
        feelsLikeValue.text = weather.feelsLike.toString()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }

}