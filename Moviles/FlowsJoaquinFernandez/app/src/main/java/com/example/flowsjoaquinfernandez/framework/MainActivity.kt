package com.example.flowsjoaquinfernandez.framework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.example.flows.R
import com.example.flows.databinding.ActivityMainBinding
import com.example.flowsjoaquinfernandez.framework.movies.TrendingMovies
import com.example.flowsjoaquinfernandez.framework.series.SearchSeries
import com.example.flowsjoaquinfernandez.framework.series.SeriesContract
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(TrendingMovies())

        with(binding) {

            //lifecycleScope.launch {
            //    repeatOnLifecycle(Lifecycle.State.STARTED) {
            /*
            traer datos de room antes de ir a internet
            utils = para devolver el estado de la conexion
            class result<T> = either, tipo data, error, loading
            network result = result
            state flow = livedata
            mirar paginacion en android
            */


            bottomNavigation.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.trendingMovies -> {
                        replaceFragment(TrendingMovies())
                        true
                    }
                    R.id.trendingTvSeries -> {
                        replaceFragment(SearchSeries())
                        true
                    }
                    else -> false
                }
            }

        }
    }






    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frame_layout, fragment)
            setCustomAnimations(
                android.R.anim.fade_in,
                android.R.anim.fade_out,
                android.R.anim.fade_in,
                android.R.anim.fade_out
            )
            commit()
        }
    }


}