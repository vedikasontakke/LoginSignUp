package com.example.loginsignup.models

import com.google.gson.annotations.SerializedName

 class UserResponseModel{

        @SerializedName("isSuccessful")
        var isSuccessful: Boolean? = null

        @SerializedName("message")
        var message : String? = null

        @SerializedName("errors")
        var errors: List<String>? = null

        @SerializedName("user")
        var user: UserModel? = null

}

//ekda postman
/*
{
       "isSuccessful": true,
       "message": "User registered successfully",
       "errors": [],
       "user": {
       "name": "vedika",
       "email": "vvedikaviksontakke2003@gmail.com",
       "updated_at": "2021-03-26 15:27:55",
       "created_at": "2021-03-26 15:27:55",
       "id": 1276
}*/
//user he list nahi ahe he ek dataclass ahe fakt
// ani errors?????????//[] array