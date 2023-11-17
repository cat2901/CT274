package com.example.b2012045_mymap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.b2012045_mymap.databinding.ActivityDisplayMainBinding
import com.google.android.gms.maps.model.LatLngBounds
import com.example.b2012045_mymap.models.UserMap

private const val TAG = "DisplayMapActivity"

class DisplayMainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityDisplayMainBinding
    private  lateinit var userMap:UserMap
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDisplayMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        userMap =intent.getSerializableExtra(Utils.EXTRA_USER_MAP) as UserMap
        supportActionBar?.title =userMap.title

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        Log.i(TAG,"map : ${userMap.title}")
        val boundsBuilder =LatLngBounds.builder()//giới hạn bản đồ
        for(place in userMap.places){
            val latLng = LatLng(place.latitude, place.longitude)
            boundsBuilder.include(latLng)
            mMap.addMarker(MarkerOptions().position(latLng).title(place.title).snippet(place.description))
        }
        mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(boundsBuilder.build(),1000,1000,0))

    }
}