package com.example.datastoreexample

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.datastoreexample.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    val items = ArrayList<MyAndroid>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        addEvents()

        binding.rvListAndroid.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.rvListAndroid.adapter = MyAndroidRVAdapter(items)


    }

    fun addEvents(){
        binding.btnSave.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                saveData(binding.etKey.text.toString(), binding.etValue.text.toString())
            }
        }

        binding.btnRead.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val value = readData(binding.etRead.text.toString())
                binding.tvResult.setText(value)
            }
        }

        // Basic Spinner
        val options = arrayOf("Opt 1", "Opt 2", "Opt 3")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, options)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.basicSpinner.adapter = adapter

        binding.basicSpinner.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                Toast.makeText(this@MainActivity, "Selected ${options[p2]}", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        // Custom Spinner
        items.add(MyAndroid(1, R.drawable.cupcake, "Cupcake", "API level 4"))
        items.add(MyAndroid(2, R.drawable.donut, "Donut", "API level 4"))
        items.add(MyAndroid(3, R.drawable.eclairs, "Eclairs", "API level 5"))
        items.add(MyAndroid(4, R.drawable.froyo, "Froyo", "API level 8"))
        items.add(MyAndroid(5, R.drawable.gingerbread, "Ginger Bread", "API level 9"))
        items.add(MyAndroid(6, R.drawable.honeycomb, "Honey comb", "API level 11"))
        items.add(MyAndroid(7, R.drawable.jellybean, "Jelly bean", "API level 16"))
        items.add(MyAndroid(8, R.drawable.kitkat, "KitKat", "API level 19"))
        items.add(MyAndroid(9, R.drawable.lolipop, "Lolipop", "API level 21"))
        items.add(MyAndroid(10, R.drawable.oreo, "Oreo", "API level 26"))

        binding.customSpinner.adapter = MyAndroidAdapter(this, items)
    }

    suspend fun saveData(key:String, value:String) {
        val _key = stringPreferencesKey(key)
        dataStore.edit { settings ->
            val currentCounterValue = settings[_key] ?: 0
            settings[_key] = value
        }
    }

    suspend fun readData(key: String):String {
        val _key = stringPreferencesKey(key)
        val _value: Flow<String> = dataStore.data
            .map { settings ->
                // No type safety.
                settings[_key] ?: "No Value"
            }
        return _value.first().toString()
    }
}