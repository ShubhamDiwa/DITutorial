package com.vasyerp.ditutorial.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vasyerp.ditutorial.Api.ProductsResponse
import com.vasyerp.ditutorial.repository.MainRepositries
import com.vasyerp.ditutorial.utils.NetworkHelper
import com.vasyerp.ditutorial.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

class MainViewModels @Inject constructor(
    private val repo: MainRepositries,
    private val networkHelper: NetworkHelper
) : ViewModel() {
    private val _res = MutableLiveData<Resource<ProductsResponse>>()

    val live_res: LiveData<Resource<ProductsResponse>> = _res

    init {
        getProducts()
    }

    private fun getProducts() = viewModelScope.launch {
        _res.postValue(Resource.loading(null))
        if (networkHelper.isNetworkConnected()) {
            repo.getProducts().let {
                if (it.isSuccessful) {
                    _res.postValue(Resource.success(it.body()))

                } else {
                    _res.postValue(Resource.error(it.errorBody().toString(), null))
                }
            }
        } else {
            _res.postValue(Resource.error("no internet connection", null))
        }

    }


}