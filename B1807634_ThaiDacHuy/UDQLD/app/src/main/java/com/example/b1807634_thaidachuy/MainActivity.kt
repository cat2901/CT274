package com.example.b1807634_thaidachuy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.b1807634_thaidachuy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val option = arrayOf("Tat Ca", "CT27403","CT27402")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, option)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerLop.adapter= adapter

        binding.spinnerLop.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                val selected = option[position]
                Toast.makeText(this@MainActivity, "Lua chon: $selected", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
        binding.tvNVA.setOnClickListener{
            val intent = Intent(this@MainActivity, CNTT::class.java)
            startActivity(intent)
        }
    }
}