package com.veryable.android.rest

import retrofit2.Response
import retrofit2.http.GET

interface AccountApiInterface {
    //provides get request to json file
    @GET("/veryable.json")
    suspend fun getAccounts() : Response<List<AccountJsonEntity>>

}