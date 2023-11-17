package com.example.b1906411_nguyenthitrang

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.b1906411_nguyenthitrang.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var helper = MyDB(applicationContext)
        var db = helper.readableDatabase
        var rs = db.rawQuery("select * from sinhvien", null)

    }
}