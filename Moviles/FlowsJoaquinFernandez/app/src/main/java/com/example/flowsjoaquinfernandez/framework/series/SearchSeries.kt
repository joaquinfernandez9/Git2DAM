package com.example.flowsjoaquinfernandez.framework.series

import android.annotation.SuppressLint
import android.app.Notification.Action
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.SearchView
import android.widget.Toast
import androidx.core.view.inputmethod.InputContentInfoCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flows.R
import com.example.flows.databinding.FragmentSearchTvSeriesBinding
import com.example.flowsjoaquinfernandez.domain.modelo.Series
import com.example.flowsjoaquinfernandez.framework.movies.MovieAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import okhttp3.internal.notify
import java.util.Locale.filter

@AndroidEntryPoint
class SearchSeries : Fragment() {

    private lateinit var _binding: FragmentSearchTvSeriesBinding
    private val binding get() = _binding
    private lateinit var adapter: SeriesAdapter


    private val viewModel: SearchSeriesViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        _binding = FragmentSearchTvSeriesBinding.inflate(layoutInflater)
        viewModel.handleEvent(SeriesContract.Event.Cargar)
        init()

        lifecycleScope.launch {
            viewModel.uiState.collect { value ->
                adapter.submitList(value.series)
            }
        }

        //set text listener

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.search_action -> {
                val searchView = item.actionView as SearchView
                searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(newText: String?): Boolean {
                        if (newText != null) {
                            viewModel.handleEvent(SeriesContract.Event.Buscar(newText))
                            lifecycleScope.launch {
                                viewModel.uiState.collect { value ->
                                    adapter.submitList(value.series)

                                }
                            }
                        }
                        return true
                    }


                    override fun onQueryTextChange(newText: String?): Boolean {
                        if (newText != null) {
                            viewModel.handleEvent(SeriesContract.Event.Buscar(newText))
                            lifecycleScope.launch {
                                viewModel.uiState.collect { value ->
                                    adapter.submitList(value.series)

                                }
                            }
                        }
                        return true
                    }
                })
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun init() {
        val layoutManager = LinearLayoutManager(context)
        binding.rvSeries.layoutManager = layoutManager

        val deco = DividerItemDecoration(
            binding.rvSeries.context,
            layoutManager.orientation
        )
        binding.rvSeries.addItemDecoration(deco)

        adapter = SeriesAdapter()
        binding.rvSeries.adapter = adapter


    }

}