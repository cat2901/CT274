package com.example.demobroadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.demobroadcastreceiver.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var receiver: BroadcastReceiver
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val filter = IntentFilter()
        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        receiver = object: BroadcastReceiver(){
            override fun onReceive(p0: Context?, p1: Intent?) {
                val state = p1?.getBooleanExtra("state", false)

                if(state == true){
                    binding.tvDisplay.setText("Airplane Mode: On")
                }
                else{
                    binding.tvDisplay.setText("Airplane Mode: Off")
                }

                Toast.makeText(p0, state.toString(), Toast.LENGTH_SHORT).show()
            }
        }
        registerReceiver(receiver, filter)

        binding.btnSend.setOnClickListener {
            sendBroadcast(Intent(applicationContext, MyReceiver::class.java))
        }


    }

    override fun onDestroy() {
        unregisterReceiver(receiver)
        super.onDestroy()
    }
}