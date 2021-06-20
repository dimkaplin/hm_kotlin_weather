package com.example.hm_view_model.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.hm_view_model.R
import com.example.hm_view_model.databinding.MainFragmentBinding
import com.example.hm_view_model.model.ApplState
import com.example.hm_view_model.model.Weather
import com.google.android.material.snackbar.Snackbar

class MainFragment : Fragment() {

    private lateinit var binding: MainFragmentBinding

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //return inflater.inflate(R.layout.main_fragment, container, false)
        binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View,  savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        //val observe = Observer<Any> {renderData(it)}
        val observe = Observer<ApplState> {renderData(it)}
        //viewModel.getData().observe(viewLifecycleOwner, observe)
        viewModel.getData().observe(viewLifecycleOwner, observe)

        viewModel.getWeather()
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
                setData(weather)
            }
            is ApplState.Bad -> {
                loadLayout.visibility = View.GONE
            }
            is ApplState.Middle -> {
                loadLayout.visibility = View.VISIBLE
                Snackbar
                    //mainView ?????
                    .make(main, "Bad", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Reload") {viewModel.getWeather()}
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