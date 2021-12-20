package com.veryable.android.rest

import android.widget.Toast
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import kotlin.coroutines.coroutineContext

/*
* Api Class to get HTML JSON response for pulling accounts data
* */
class AccountsApiService() {

    companion object {
        private const val VERYABLE_ASSET_BASE_URL =
            "https://veryable-public-assets.s3.us-east-2.amazonaws.com"
    }

    //HTTP Logging Interceptor
    private val loggingIntercept = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }

    //OkHttp HTTP Client
    private val okHttpClient = OkHttpClient.Builder().apply {
        this.callTimeout(2, TimeUnit.MINUTES)
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(loggingIntercept)
    }.build()

    //Retrofit HTTP Client
    private fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(VERYABLE_ASSET_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
    }
    private val accountsApiInterface: AccountApiInterface = getRetrofitInstance().create(
        AccountApiInterface::class.java
    )

    //Get Veryable Accounts API Response or return empty list
    suspend fun getVeryableAccounts(): List<AccountJsonEntity>? {
        val response = accountsApiInterface.getAccounts()
        return if (response.isSuccessful){
            //Successful API call
            if(!response.body().isNullOrEmpty()){
                //Response Body Populated
                response.body()
            }else{
                //Response Body Empty or Null
                emptyList()
            }
        } else{
            //Unsuccessful API call
            emptyList()
        }
    }

}