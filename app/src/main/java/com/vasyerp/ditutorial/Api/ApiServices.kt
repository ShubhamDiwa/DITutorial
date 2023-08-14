package com.vasyerp.ditutorial.Api

import retrofit2.Response
import retrofit2.http.GET

interface ApiServices {

    @GET("products")
    suspend fun getProducts(): Response<ProductsResponse>
}