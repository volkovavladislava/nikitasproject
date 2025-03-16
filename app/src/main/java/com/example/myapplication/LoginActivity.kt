package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.adapters.ListAdapterListMark
import com.example.myapplication.data.LoginRequest
import com.example.myapplication.data.Mark
import com.example.myapplication.data.UserAuth
import com.example.myapplication.databinding.ActivityLoginBinding
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {


    private lateinit var binding: ActivityLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding!!.loginButton.setOnClickListener {

            val login = binding!!.emailPhoneInput.text.toString()
            val pas = binding!!.passwordInput.text.toString()

            val l = LoginRequest(login,pas)



            RetrofitClient.apiService.login(l).enqueue(object : Callback<UserAuth> {
                override fun onResponse(call: Call<UserAuth>, response: Response<UserAuth>) {
                    if (response.isSuccessful) {
                        var user = response.body()

                        Log.d("RetrofitClient", "user " + user)

                        val intent = Intent(this@LoginActivity, MainActivity::class.java).apply {
                            putExtra("id",user?.userid?.toInt() ?: -1)
                        }
                        startActivity(intent)
                        finish()

                    }
                    else{
                        Log.d("RetrofitClient", "Receive user  problem " )
                        Toast.makeText(this@LoginActivity, "Неверный логин или пароль", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<UserAuth>, t: Throwable) {
                    Log.e("MainActivity", "Error: ${t.message}")
                }
            })

//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//            finish()
        }

        binding!!.registerButton.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }



    }
}