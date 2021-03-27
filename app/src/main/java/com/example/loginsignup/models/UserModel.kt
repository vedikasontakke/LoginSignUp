package com.example.loginsignup.models

import com.google.gson.annotations.SerializedName

class UserModel {

    @SerializedName("name")
    var name: String? = null

    @SerializedName("email")
    var email: String? = null

    @SerializedName("updated_at")
    var updated_at: String? = null

    @SerializedName("created_at")
    var created_at: String? = null

    @SerializedName("id")
    var id: String? = null

}
