package com.github.uragiristereo.mejiboard.model.network

import com.github.uragiristereo.mejiboard.util.BASE_URL
import com.github.uragiristereo.mejiboard.util.CustomDateAdapter
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


object DataRepository {
    fun create(
        okHttpClient: OkHttpClient
    ): DataServices {
        val moshiBuilder = Moshi.Builder()
            .add(CustomDateAdapter())
            .build()

        val retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshiBuilder))
            .baseUrl(BASE_URL)
            .build()

        return retrofit.create(DataServices::class.java)
    }
}