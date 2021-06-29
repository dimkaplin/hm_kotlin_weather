package com.example.hm_view_model.ui.main.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.hm_view_model.R
import com.example.hm_view_model.databinding.FragmentRecyclerItemBinding
import com.example.hm_view_model.model.Weather
import com.example.hm_view_model.ui.main.head.HeadFragment

class MainFragAdapter(private var onItemViewClickListener: HeadFragment.OnItemViewClickListen?): RecyclerView.Adapter<MainFragAdapter.MainViewHolder>() {
   // RecyclerView.Adapter<MainFragAdapter.MainViewHolder>()
   // {

        private var weatherData: List<Weather> = listOf()
        private lateinit var binding: FragmentRecyclerItemBinding

        fun setWeather(data: List<Weather>) {
            weatherData = data
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): MainViewHolder {
            /*return MainViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.fragment_recycler_item, parent, false) as View
            )*/
            binding = FragmentRecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return MainViewHolder(binding.root)
        }

        override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
            holder.bind(weatherData[position])
        }

        override fun getItemCount(): Int {
            return weatherData.size
        }

        inner class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {

            fun bind(weather: Weather) = with(binding) {
                /*itemView.findViewById<TextView>(R.id.mainFragmentRecyclerItemTextView).text =
                    weather.city.city*/
                mainFragmentRecyclerItemTextView.text = weather.city.city
                itemView.setOnClickListener {
                    /*Toast.makeText(
                        itemView.context,
                        weather.city.city,
                        Toast.LENGTH_LONG
                    ).show()*/
                    onItemViewClickListener?.onItemClickView(weather)
                }
            }
        }

        //}

    //}
}