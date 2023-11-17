package com.example.b2012045_huynhtrungtin_uocmo.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.PixelCopy.Request
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.b2012045_huynhtrungtin_uocmo.R
import com.example.b2012045_huynhtrungtin_uocmo.databinding.FragmentLoginBinding
import com.example.b2012045_huynhtrungtin_uocmo.models.RequestRegisterOrLogin
import com.example.b2012045_huynhtrungtin_uocmo.sharedpreferences.AppSharedPreferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.b2012045_huynhtrungtin_uocmo.apis.Constants
import kotlinx.coroutines.withContext

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private  lateinit var mAppSharedPreferences: AppSharedPreferences
    private var username = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =FragmentLoginBinding.inflate(layoutInflater,container,false)

        //khoi tao  mAppSharedPreferences
        mAppSharedPreferences = AppSharedPreferences(requireContext())

        binding.apply {
            tvRegister.setOnClickListener {
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_layout,RegisterFragment())
                    .commit()
            }
            btnLogin.setOnClickListener {
                if(edtUsername.text.isNotEmpty()){
                    username = edtUsername.text.toString().trim()
                    //thuc hien call api dang nhap tai khoan
                    loginUser(username)
                }
            }
        }
        //Inflate the layout for this fragment
        return binding.root
    }

    private fun loginUser(username: String){
        binding.apply {
            progressBar.visibility = View.VISIBLE
            CoroutineScope(Dispatchers.IO).launch {//
                withContext(Dispatchers.Main){
                    val response = Constants.getInstance().loginUser(RequestRegisterOrLogin(username))
                        .body()
                    if (response != null){
                        if(response.success){
                            //dang nhap tai khoan thanh cong
                            //Nhan idUser va thuc hien luu vao SharedPreferences
                            mAppSharedPreferences.putIdUser("idUser", response.idUser!!)
                            requireActivity().supportFragmentManager.beginTransaction()
                                .replace(R.id.frame_layout, WishListFragment())
                                .commit()
                            progressBar.visibility = View.GONE
                        }else{
                            //dang nhap tai khoan that bai
                            tvMessage.text = response.message
                            tvMessage.visibility = View.VISIBLE
                            progressBar.visibility = View.GONE
                        }

                    }
                }
            }
        }
    }
}