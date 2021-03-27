package com.example.loginsignup.retrofit

import com.example.loginsignup.models.UserResponseModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface APIInterface {

    @FormUrlEncoded
    @POST("signup")
     fun signup(
            @Field("password") password: String?,
            @Field("name") name: String?,
            @Field("email") email: String?
    ) : Call<UserResponseModel>?

    @FormUrlEncoded
    @POST("login")
     fun login(
            @Field("email") email: String?,
            @Field("password") password: String?
    ) : Call<UserResponseModel>?
}