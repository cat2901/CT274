package com.example.ttappcontacts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ttappcontacts.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvList.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        // RecyclerView with API create Contacts
        initContacts()

        // Contact Activity

    }

    private fun initContacts() {
        val api = Retrofit.Builder()
            .baseUrl("https://randomuser.me/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiRequests::class.java)
        GlobalScope.launch(Dispatchers.IO) {
            val response = api.getContacts().awaitResponse()
            val data = response.body()!!
            withContext(Dispatchers.Main) {
                var adapter = RVAdapter(applicationContext, data.results)
                binding.rvList.adapter = adapter
            }
        }
    }

}