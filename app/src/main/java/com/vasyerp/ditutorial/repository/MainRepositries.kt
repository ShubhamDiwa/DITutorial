package com.vasyerp.ditutorial.repository

import com.vasyerp.ditutorial.Api.ApiServices
import javax.inject.Inject

class MainRepositries  @Inject constructor(private val apiServices: ApiServices) {


    suspend fun getProducts() =apiServices.getProducts()
}