package com.example.flowsjoaquinfernandez.framework.movies

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flows.databinding.FragmentTrendingMoviesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TrendingMovies : Fragment() {

    //binding
    private var _binding: FragmentTrendingMoviesBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: MovieAdapter
    private val viewModel: MovieViewModel by viewModels()

    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTrendingMoviesBinding.inflate(layoutInflater)
        viewModel.handleEvent(MovieContract.Event.Cargar)

        init()

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.uiState.collect{ value ->
                    binding.loading.visibility = if (value.isLoading) View.VISIBLE else View.GONE
                    adapter.submitList(value.movies)
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiError.collect {
                    Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                }
            }
        }

        return binding.root
    }

    private fun init(){
        val layoutManager = LinearLayoutManager(context)
        binding.rvMovies.layoutManager = layoutManager

        val deco = DividerItemDecoration(
            binding.rvMovies.context,
            layoutManager.orientation
        )
        binding.rvMovies.addItemDecoration(deco)

        adapter = MovieAdapter()
        binding.rvMovies.adapter = adapter


    }





}