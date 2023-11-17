package com.example.alarmsmn_buoi2


import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import java.util.*

class MyReceiver :BroadcastReceiver() {

    override fun onReceive(context: Context?, inten: Intent?) {
        Toast.makeText(context, "Nguyen thi trang",
            Toast.LENGTH_SHORT).show()    }



}
