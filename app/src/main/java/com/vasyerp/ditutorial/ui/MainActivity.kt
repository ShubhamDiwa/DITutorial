package com.vasyerp.ditutorial.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.vasyerp.ditutorial.Adapter.ProducAdapter
import com.vasyerp.ditutorial.Api.Products
import com.vasyerp.ditutorial.ViewModels.MainViewModels
import com.vasyerp.ditutorial.databinding.ActivityMainBinding
import com.vasyerp.ditutorial.utils.Status
import com.vasyerp.ditutorial.utils.Utils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private lateinit var Adapter: ProducAdapter

    private val mainViewModel: MainViewModels by viewModels()

    val productList = ArrayList<Products>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initAdapter()
        subscribeViewModel()


    }

    private fun subscribeViewModel() {
        mainViewModel.live_res.observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.progress.visibility = View.GONE
                    binding.rvEmployees.visibility = View.VISIBLE
                    it.data.let { res ->
                        if (res?.products?.isNotEmpty() == true) {
                            res.products.let { data ->
                                productList.addAll(data)
                            }
                        }

                    }
                }

                Status.LOADING -> {
                    binding.progress.visibility = View.VISIBLE
                    binding.rvEmployees.visibility = View.GONE
                }

                Status.ERROR -> {
                    binding.progress.visibility = View.GONE
                    binding.rvEmployees.visibility = View.VISIBLE
                    Utils.showSnackBar(binding.rootview, "Something went wrong")
                }
            }

        }
    }

    private fun initAdapter() {
        Adapter= ProducAdapter(this, productList)
        binding.rvEmployees.adapter = Adapter



    }
}