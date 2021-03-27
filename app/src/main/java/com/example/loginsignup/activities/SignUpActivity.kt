package com.example.loginsignup.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.loginsignup.R
import com.example.loginsignup.models.UserResponseModel
import com.example.loginsignup.retrofit.APIInterface
import com.example.loginsignup.retrofit.ApiClient
import com.google.gson.Gson
import com.google.gson.TypeAdapter
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.activity_sign_up.edit_text_email
import kotlinx.android.synthetic.main.activity_sign_up.edit_text_password
import net.simplifiedcoding.mvvmsampleapp.util.ApiException
import net.simplifiedcoding.mvvmsampleapp.util.NoInternetException
import org.json.JSONObject
import retrofit2.Callback
import retrofit2.Response


private const val TAG = "SignUpActivity"
class SignUpActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        signInTextClick()
        SignUpClicked()
    }
//Postman
    fun SignUpClicked()
    {

        button_sign_up.setOnClickListener{

            val apiService: APIInterface? = ApiClient.client?.create(APIInterface::class.java)

            val name = edit_text_name.text.toString()
            val email = edit_text_email.text.toString()
            val password = edit_text_password.text.toString()

            val callSignUp = apiService?.signup(name, email, password)
//RUn
            try
            {
                callSignUp?.enqueue(object : Callback<UserResponseModel> {

                    override fun onResponse(
                        call: retrofit2.Call<UserResponseModel>,
                        response: Response<UserResponseModel>
                    ) {
                        if (response.code() === 400) {
                            Log.d(TAG, "onResponse - Status : " + response.code())
                            val error = response.errorBody()?.string()
                            val jsonObject = JSONObject(error!!)
                            val errorStr = jsonObject.get("error").toString()
                            val msg = jsonObject.get("messag").toString()
                            val result = jsonObject.get("isSuccessful").toString()


                            Log.e(TAG, "onResponse: $errorStr $msg $result" )
                        }

                        if (response.body() != null) {
                            if (response.body()?.isSuccessful == true) {
                                Toast.makeText(
                                    this@SignUpActivity,
                                    "SignUp done  Successfully" + response.body()!!.user!!.name,
                                    Toast.LENGTH_SHORT
                                ).show()

                                val intent = Intent(this@SignUpActivity, DashBoard::class.java)
                                startActivity(intent)
                            } else {
                                Log.e(
                                    TAG,
                                    "onResponse: ${response.body() ?: response.code()} ${response.errorBody() ?: response.headers()}"
                                )
                                Toast.makeText(
                                    this@SignUpActivity,
                                    "error : ${response.body()!!.message /*?: response.body()!!.errors*/}",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        } else {
                            Log.e(
                                TAG,
                                "onResponse: ${response.body() ?: response.code()} ${response.errorBody() ?: response.headers()}"
                            )

                        }
                    }

                    override fun onFailure(call: retrofit2.Call<UserResponseModel>, t: Throwable) {
                        Toast.makeText(this@SignUpActivity, "error : $t", Toast.LENGTH_SHORT).show()
                    }
                })
            }
            catch (e: ApiException)
            {
                Toast.makeText(this, "error: $e", Toast.LENGTH_SHORT).show()
            }
            catch (e: NoInternetException)
            {
                Toast.makeText(this, "error : $e", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun signInTextClick()
    {

        text_view_login.setOnClickListener {

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}


