package com.example.demosqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.demosqlite.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val helper = MyDB(applicationContext)
        val db = helper.readableDatabase
        var rs = db.rawQuery("select * from users", null)

        rs.moveToFirst()

        binding.tvUserName.setText(rs.getString(1))
        binding.tvPassword.setText(rs.getString(2))

        binding.btnNext.setOnClickListener {
            if(!rs.moveToNext()){
                rs.moveToFirst()
            }
            binding.tvUserName.setText(rs.getString(1))
            binding.tvPassword.setText(rs.getString(2))

        }

    }
}