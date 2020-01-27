package com.example.ghibliquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val baseURL = "https://ghibliapi.herokuapp.com/"

        val jsonConverter = GsonConverterFactory.create(GsonBuilder().create())
        val retrofit = Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(jsonConverter)
            .build()

        val service: WebServiceInterface = retrofit.create(WebServiceInterface::class.java)

        val wsCallback: Callback<MutableList<Characters>> = object : Callback<MutableList<Characters>> {
            override fun onFailure(call: Call<MutableList<Characters>>, t: Throwable) {
                Log.w("TAG", "WebService call failed")
                Log.w("TAG", "-> " + t.message)
            }

            override fun onResponse(
                call: Call<MutableList<Characters>>,
                response: Response<MutableList<Characters>>
            ) {
                if (response.code() == 200) {
                    val responseData = response.body()
                    if (responseData != null) {
                        Log.d("TAG", "WebService success : " + responseData.size)

                        var adapter = CharacterRecyclerViewAdapter(applicationContext, responseData)
                        recyclerView.adapter = adapter
                        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
                        recyclerView.addItemDecoration(DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL)
                        )
                    }
                }
            }
        }
        service.listAllPeople().enqueue(wsCallback)
    }
}
