package com.example.hm_view_model

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.hm_view_model.databinding.MainActivityBinding
import com.example.hm_view_model.ui.main.ContentProviderFragment
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_screen_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.menu_content_provider -> {
                supportFragmentManager.apply {
                    beginTransaction()
                        .add(R.id.container, ContentProviderFragment.newInstance())
                        .addToBackStack("")
                        .commitAllowingStateLoss()
                }
                true
            } else -> super.onOptionsItemSelected(item)
        }
    }
}