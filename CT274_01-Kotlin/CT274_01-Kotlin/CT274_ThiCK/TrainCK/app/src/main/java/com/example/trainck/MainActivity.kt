package com.example.trainck

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.trainck.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNext.setOnClickListener {
            if (binding.etName.text.toString().isEmpty())
                Toast.makeText(this,"Please Enter Your Name",Toast.LENGTH_SHORT).show()
            else{
                val intent: Intent = Intent(this, BMIActivity::class.java)
                var name:String = binding.etName.text.toString()
                intent.putExtra(Constants.NAME,name)
                startActivity(intent)
            }
        }


    }
}