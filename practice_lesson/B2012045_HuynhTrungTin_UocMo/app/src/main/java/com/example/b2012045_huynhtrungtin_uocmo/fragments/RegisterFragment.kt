package com.example.b2012045_huynhtrungtin_uocmo.fragments

import android.os.Bundle
import android.provider.SyncStateContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.b2012045_huynhtrungtin_uocmo.R
import com.example.b2012045_huynhtrungtin_uocmo.databinding.FragmentLoginBinding
import com.example.b2012045_huynhtrungtin_uocmo.databinding.FragmentRegisterBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.example.b2012045_huynhtrungtin_uocmo.apis.Constants
import com.example.b2012045_huynhtrungtin_uocmo.models.RequestRegisterOrLogin
import com.example.b2012045_huynhtrungtin_uocmo.sharedpreferences.AppSharedPreferences


class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var mAppSharedPreferences: AppSharedPreferences
    private var username = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)

        // Khoi tao AppSharedPreferences
        mAppSharedPreferences = AppSharedPreferences(requireContext())

        binding.apply {
            btnRegister.setOnClickListener {
                if (edtUsername.text.isNotEmpty()) {
                    username = edtUsername.text.toString().trim()
                    // thuc hien call api dang ky tai khoan
                    registerUser(username)
                } else {
                    Snackbar.make(it, "Vui lòng nhập mã số sinh viên", Snackbar.LENGTH_LONG).show()
                }
            }

            tvLogin.setOnClickListener {
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_layout, LoginFragment())
                    .commit()
            }
        }
//inflate the layout for this fragment
        return binding.root
    }

    private fun registerUser(username: String) {
        binding.apply {
            progressBar.visibility = View.VISIBLE
            CoroutineScope(Dispatchers.IO).launch {
                withContext(Dispatchers.Main) {
                    val response = Constants.getInstance().registerUser(RequestRegisterOrLogin(username)
                    ).body()
                    if (response != null) {
                        if (response.success) {
                            //dang ky thanh cong
                            //nhan idUser va thuc hien luu vao SharedPreferences
                            mAppSharedPreferences.putIdUser("idUser", response.idUser!!)
                            requireActivity().supportFragmentManager.beginTransaction()
                                .replace(R.id.frame_layout, WishListFragment())
                                .commit()
                        } else {
                            //dang ky tai khoan that vai
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