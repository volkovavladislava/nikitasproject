package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.data.RegistrationRequest
import com.example.myapplication.data.UserAuth
import com.example.myapplication.databinding.ActivityLoginBinding
import com.example.myapplication.databinding.ActivityRegisterBinding
import com.example.myapplication.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding!!.registrationRegisterButton.setOnClickListener {
            if( !binding!!.registrationUsernameInput.text.isNullOrEmpty()  && !binding!!.registrationPasswordInput.text.isNullOrEmpty()
                && !binding!!.registrationEmailPhoneInput.text.isNullOrEmpty()){

                val username = binding!!.registrationUsernameInput.text.toString()
                val pas = binding!!.registrationPasswordInput.text.toString()
                val email = binding!!.registrationEmailPhoneInput.text.toString()

                val l = RegistrationRequest(username, pas, email )


                RetrofitClient.apiService.register(l).enqueue(object : Callback<UserAuth> {
                    override fun onResponse(call: Call<UserAuth>, response: Response<UserAuth>) {
                        if (response.isSuccessful) {
                            var user = response.body()


                            val intent = Intent(this@RegisterActivity, MainActivity::class.java).apply {
                                putExtra("id",user?.userid?.toInt() ?: -1)
                            }
                            startActivity(intent)
                            finish()

                        }
                        else{
                            Toast.makeText(this@RegisterActivity, "Неверный логин или пароль", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<UserAuth>, t: Throwable) {
                        Log.e("MainActivity", "Error: ${t.message}")
                    }
                })
            }
            else{
                Toast.makeText(this, "Сначала заполните все поля", Toast.LENGTH_SHORT).show()
            }



            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding!!.registrationLoginButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}