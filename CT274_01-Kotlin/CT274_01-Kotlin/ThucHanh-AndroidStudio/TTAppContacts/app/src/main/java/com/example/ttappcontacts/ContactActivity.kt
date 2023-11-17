package com.example.ttappcontacts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.ttappcontacts.databinding.ActivityContactBinding
import com.example.ttappcontacts.databinding.ActivityMainBinding

class ContactActivity : AppCompatActivity() {

    lateinit var binding: ActivityContactBinding

    private var name:String? = null
    private var phone:String? = null
    private var email:String? = null
    private var addres:String? = null
    private var picture:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactBinding.inflate(layoutInflater)
        setContentView(binding.root)
        name = intent.getStringExtra(Const.NAME)
        phone = intent.getStringExtra(Const.PHONE)
        email = intent.getStringExtra(Const.EMAIL)
        addres = intent.getStringExtra(Const.ADDRESS)
        picture = intent.getStringExtra(Const.AVT)

        binding.tvNameP.setText(name)
        binding.tvEmailP.setText(email)
        binding.tvAddressP.setText(addres)
        binding.tvPhoneNumberP.setText(phone)

        Glide.with(this).load(picture).into(binding.ivAvatarP);

    }
}