package com.example.loginsignup.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.loginsignup.BuildConfig
import com.example.loginsignup.R
import com.example.loginsignup.models.UserResponseModel
import com.example.loginsignup.retrofit.APIInterface
import com.example.loginsignup.retrofit.ApiClient
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.edit_text_email
import kotlinx.android.synthetic.main.activity_login.edit_text_password
import kotlinx.android.synthetic.main.activity_sign_up.*
import net.simplifiedcoding.mvvmsampleapp.util.ApiException
import net.simplifiedcoding.mvvmsampleapp.util.NoInternetException
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class LoginActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        signUpTextClick()
        LoginClicked()
    }

    fun LoginClicked(){

        button_sign_in.setOnClickListener{

            val apiService: APIInterface? = ApiClient.client?.create(APIInterface::class.java)

            val email = edit_text_email.text.toString()
            val password =edit_text_password.text.toString()

            val callLogin = apiService?.login(email , password)

            try {
                callLogin?.enqueue(object : Callback<UserResponseModel> {

                    override fun onResponse(call: retrofit2.Call<UserResponseModel>, response: Response<UserResponseModel>) {

                        if (response.body()?.isSuccessful == true)
                        {

                            Toast.makeText(this@LoginActivity, "Login Successfully "+response.body()!!.user!!.name , Toast.LENGTH_SHORT).show()

                            val intent = Intent(this@LoginActivity , DashBoard ::class.java)
                            startActivity(intent)
                        }
                        else
                        {

                            Toast.makeText(this@LoginActivity, "error : ${response.body()!!.message ?: response.body()!!.errors}" , Toast.LENGTH_SHORT).show()

                        }
                    }

                    override fun onFailure(call: retrofit2.Call<UserResponseModel>, t: Throwable) {

                        //  TODO("Not yet implemented")

                        Toast.makeText(this@LoginActivity, "error : $t" , Toast.LENGTH_SHORT).show()

                    }
                })
            }
            catch (e : ApiException)
            {
                Toast.makeText(this, "error: $e", Toast.LENGTH_SHORT).show()
            }
            catch (e : NoInternetException)
            {
                Toast.makeText(this , "error : $e" , Toast.LENGTH_SHORT).show()
            }

        }


    }

    private fun signUpTextClick() {

        text_view_sign_up.setOnClickListener {

            val intent = Intent(this , SignUpActivity ::class.java)
            startActivity(intent)
        }
    }
}