package com.example.mymap.activities

import android.app.Activity
import android.content.AbstractThreadedSyncAdapter
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymap.Constants
import com.example.mymap.MapsAdapter
import com.example.mymap.Models.Place
import com.example.mymap.Models.UserMap
import com.example.mymap.R
import com.example.mymap.Utils
import com.example.mymap.databinding.ActivityMainBinding
private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {
     lateinit var binding : ActivityMainBinding
     private lateinit var userMaps : MutableList<UserMap>
     lateinit var mapAdapter: MapsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        //getSupportActionBar()?.show()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        //tao layout manager cho rvMaps

        binding.rvMaps.layoutManager = LinearLayoutManager(this)

        //tao adapter cho rvMaps
        userMaps = Constants.generationSimpleData().toMutableList()
        mapAdapter = MapsAdapter(this, userMaps,
            object : MapsAdapter.OnClickListener {
                override fun onItemClick(position: Int) {
                    Log.i(TAG, "onItemClick $position")
                    val intent = Intent(this@MainActivity, DisplayMapActivity::class.java)
                    intent.putExtra(Utils.EXTRA_USER_MAP, userMaps[position])
                    startActivity(intent)
                }
            })

        binding.rvMaps.adapter = mapAdapter


        binding.btnadd.setOnClickListener {

            val mapFormView = LayoutInflater.from(this).inflate(R.layout.dialog_create_map, null)
            AlertDialog.Builder(this)
                .setTitle("Map title")
                .setView(mapFormView)
                .setNegativeButton("Cancle", null)
                .setPositiveButton("OK") { _,_ ->
                    val _title = mapFormView.findViewById<EditText>(R.id.et_title).text.toString()

                    Log.i(TAG, (_title.trim().isEmpty()).toString())
                    if(_title.trim().isEmpty()) {
                        Toast.makeText(this, "Fill out title", Toast.LENGTH_SHORT).show()
                        return@setPositiveButton
                    }
                    val intent = Intent(this@MainActivity, CreateMapActivity::class.java)
                    intent.putExtra(Utils.EXTRA_USER_TITLE, _title)
                    getResult.launch(intent)
                }
                .show()
        }


    }
    private val getResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        Log.i(TAG, (it.resultCode == Activity.RESULT_OK).toString())
        if(it.resultCode == Activity.RESULT_OK) {
            val userMap = it.data?.getSerializableExtra(Utils.EXTRA_USER_MAP) as UserMap
            Log.i(TAG, userMap.title)
            Log.i(TAG, "${userMap.places[0].title} ${userMap.places[0].description} ${userMap.places[0].latitude} ${userMap.places[0].longitude}")

            userMaps.add(userMap)
            Log.i(TAG, userMaps.size.toString())
            mapAdapter.notifyItemInserted(userMaps.size - 1)

            Log.i(TAG, userMap.title)
        }
    }
}