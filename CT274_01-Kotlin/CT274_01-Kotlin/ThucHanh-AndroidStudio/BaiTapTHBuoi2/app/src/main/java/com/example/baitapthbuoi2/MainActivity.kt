package com.example.baitapthbuoi2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.baitapthbuoi2.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvList.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        binding.btnGen.setOnClickListener {
            binding.pbLoading.visibility = View.VISIBLE
            binding.rvList.visibility = View.GONE

            CoroutineScope(Dispatchers.IO).launch {
                getQuotes()
            }
        }


    }
    private suspend fun getQuotes() {
        val api = Retrofit.Builder()
            .baseUrl("https://api.quotable.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiRequests::class.java)
        val response = api.getQuotes().awaitResponse()
        val data = response.body()!!
//        Log.i("ctu: size", data.size.toString())
        withContext(Dispatchers.Main) {
            binding.pbLoading.visibility = View.GONE
            binding.rvList.visibility = View.VISIBLE
            var adapter = RVAdapter(data)
            binding.rvList.adapter = adapter
        }
    }
}