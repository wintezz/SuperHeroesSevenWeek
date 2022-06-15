package com.example.alexpetrov.ui.activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.alexpetrov.R
import com.example.alexpetrov.data.model.HeroModel
import com.example.alexpetrov.data.retrofit.AppState
import com.example.alexpetrov.databinding.ActivityMainBinding
import com.example.alexpetrov.ui.adapter.MainAdapter
import com.example.alexpetrov.ui.fragment.MainFragment
import com.example.alexpetrov.ui.model.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val adapter: MainAdapter by lazy { MainAdapter(onListItemClickListener) }

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    private val onListItemClickListener: MainAdapter.ItemClickListener =
        object : MainAdapter.ItemClickListener {
            override fun onItemClick(dataModel: HeroModel) {
                supportFragmentManager
                    .beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.container, MainFragment.newInstance(dataModel))
                    .commit()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModel()
        initAdapter()
    }

    private fun initAdapter() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    private fun initViewModel() {
        viewModel.toObserve.observe(this) { renderData(it) }
        viewModel.getData()
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Error -> {
                Toast.makeText(this, appState.error.message, Toast.LENGTH_SHORT).show()
                Log.d("TAG", appState.error.message.toString())
            }
            is AppState.Success -> {
                appState.dataModel?.let {
                    if (it.isEmpty())
                        Toast.makeText(this, "No data available", Toast.LENGTH_SHORT).show()
                    else
                        adapter.setData(it)
                }
            }
        }
    }
}