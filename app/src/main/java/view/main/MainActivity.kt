package view.main

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import app.googlemaps.GoogleMapsFragment
import view.experiments.ContentProviderFragment
import com.example.weather.R
import com.example.weather.databinding.MainActivityBinding
import history.HistoryFragment

//Weather
class MainActivity : AppCompatActivity() {

    private val receiver = MainBroadcastReceiver()
    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitAllowingStateLoss()
        }
        registerReceiver(receiver, IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED))
    }
        override fun onDestroy() {
            unregisterReceiver(receiver)
            super.onDestroy()
        }

        override fun onCreateOptionsMenu(menu: Menu?): Boolean {
            menuInflater.inflate(R.menu.main_screen_menu, menu)
            return super.onCreateOptionsMenu(menu)
        }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            return when (item.itemId) {
                R.id.menu_history -> {
                    supportFragmentManager.apply {
                        beginTransaction()
                            .add(R.id.container, HistoryFragment.newInstance())
                            .addToBackStack("")
                            .commitAllowingStateLoss()
                    }
                    true
                }
                R.id.menu_google_maps -> {
                    supportFragmentManager.apply {
                        beginTransaction()
                            .add(R.id.container, GoogleMapsFragment.newInstance())
                            .addToBackStack("")
                            .commitAllowingStateLoss()
                    }
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }
        }
    }
