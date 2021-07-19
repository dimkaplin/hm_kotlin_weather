package com.example.hm_view_model.ui.main.head

import android.content.Context
import android.os.Bundle
import android.provider.Settings.Global.getString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.hm_view_model.R
import com.example.hm_view_model.databinding.FragmentOneBinding
import com.example.hm_view_model.databinding.MainActivityBinding
import com.example.hm_view_model.databinding.MainFragmentBinding
import com.example.hm_view_model.model.ApplState
import com.example.hm_view_model.model.Weather
import com.example.hm_view_model.ui.main.MainFragment
import com.example.hm_view_model.ui.main.MainViewModel
import com.example.hm_view_model.ui.main.adapters.MainFragAdapter
import com.google.android.material.snackbar.Snackbar

class HeadFragment  : Fragment() {

    //private var _binding: FragmentMainBinding? = null
    //private val binding get() = _binding!!
    private lateinit var binding: FragmentOneBinding



    private lateinit var viewModel: MainViewModel
    private val onItemViewClickListenn = object: OnItemViewClickListen {
        override  fun onItemClickView(weather: Weather) {
            val manager = activity?.supportFragmentManager
            if (manager != null) {
                val bundle = Bundle()
                bundle.putParcelable(MainFragment.BUNDLE_EXTRA, weather)
                manager.beginTransaction()
                    .add(R.id.container, MainFragment.newInstance(bundle))
                    .addToBackStack("")
                    .commitAllowingStateLoss()
            }

        }
    }
    private val adapter = MainFragAdapter(onItemViewClickListenn)
    private var isDataSetRus: Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //_binding = FragmentMainBinding.inflate(inflater, container, false)
        binding = FragmentOneBinding.inflate(inflater, container, false)
        return binding.root//getRoot()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //binding.mainFragmentRecyclerView.adapter = adapter
        binding.mainFragmentRecyclerView.adapter = adapter
        binding.mainFragmentFAB.setOnClickListener { changeWeatherDataSet() }
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getData().observe(viewLifecycleOwner, Observer { renderData(it) })
        viewModel.getWeatherFromLocalRusSource()//getWeatherFromLocalSourceRus()


    }

    private fun changeWeatherDataSet() {
        var isDataSetRusNew = activity
            ?.getPreferences(Context.MODE_PRIVATE)
            ?.getBoolean(key_activity, true) ?: true
        if (isDataSetRusNew) {
            viewModel.getWeatherFromAnotherSource()//getWeatherFromLocalSourceWorld()
            binding.mainFragmentFAB.setImageResource(R.drawable.ic_launcher_background)
        } else {
            viewModel.getWeatherFromLocalRusSource()//getWeatherFromLocalSourceRus()
            binding.mainFragmentFAB.setImageResource(R.drawable.ic_launcher_foreground)
        }
        val editor = activity?.getPreferences(Context.MODE_PRIVATE)?.edit()
        editor?.putBoolean(key_activity, !isDataSetRusNew)
        editor?.apply()
        isDataSetRus = !isDataSetRus
    }

    private fun renderData(appState: ApplState) {
        when (appState) {
            is ApplState.Good -> {
                binding.mainFragmentLoadingLayout.visibility = View.GONE
                adapter.setWeather(appState.weatherData)
            }
            is ApplState.Middle -> {
                binding.mainFragmentLoadingLayout.visibility = View.VISIBLE
            }
            is ApplState.Bad -> {
                binding.mainFragmentLoadingLayout.visibility = View.GONE
                Snackbar
                    .make(binding.mainFragmentFAB, getString(R.string.error), Snackbar.LENGTH_INDEFINITE)
                    //.setAction(getString(R.string.reload)) { viewModel.getWeatherFromLocalSourceRus() }
                    .setAction(getString(R.string.reload)) { viewModel.getWeatherFromLocalRusSource() }
                    .show()
            }
        }
    }

    interface OnItemViewClickListen{
        fun onItemClickView(weather: Weather)
    }

    companion object {
        private const val key_activity = "key_activity"
        fun newInstance() = HeadFragment()//MainFragment()
    }
}
