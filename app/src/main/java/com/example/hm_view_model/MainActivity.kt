package com.example.hm_view_model

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hm_view_model.databinding.MainActivityBinding
import com.example.hm_view_model.ui.main.MainFragment
import com.example.hm_view_model.ui.main.head.HeadFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                //.replace(R.id.container, MainFragment.newInstance())
                .replace(R.id.container, HeadFragment.newInstance())
                .commitNow()
        }
    }
}