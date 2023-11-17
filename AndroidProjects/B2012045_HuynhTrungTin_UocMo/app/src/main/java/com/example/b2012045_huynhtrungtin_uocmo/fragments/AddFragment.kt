package com.example.b2012045_huynhtrungtin_uocmo.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.b2012045_huynhtrungtin_uocmo.R
import com.example.b2012045_huynhtrungtin_uocmo.apis.Constants
import com.example.b2012045_huynhtrungtin_uocmo.databinding.FragmentAddBinding
import com.example.b2012045_huynhtrungtin_uocmo.models.RequestAddWish
import com.example.b2012045_huynhtrungtin_uocmo.sharedpreferences.AppSharedPreferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddFragment : Fragment() {
    private lateinit var binding: FragmentAddBinding
    private lateinit var mAppSharedPreferences: AppSharedPreferences
    private var idUser = ""
    private var fullName = ""
    private var content = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //binding = FragmentAddBinding.inflate(LayoutInflater, container, false)
        binding = FragmentAddBinding.inflate(inflater, container, false) // Use LayoutInflater.from()
        //khoi tao mAppSharedPreferences va lay idUser từ mAppSharedPreferences
        mAppSharedPreferences = AppSharedPreferences(requireContext())
        idUser = mAppSharedPreferences.getIdUser("idUser").toString()

        binding.apply {
            btnSave.setOnClickListener {
                if (edtFullName.text.isNotEmpty() && edtContent.text.isNotEmpty()) {
                    fullName =edtFullName.text.toString().trim()
                    content = edtContent.text.toString().trim()
                    //thuc hien api them dieu uoc
                    addWish(fullName, content)
                }
            }
        }
        return binding.root
    }

    private fun addWish(fullName: String, content: String) {
        binding.progressBar.visibility = View.VISIBLE
        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main) {
                val response = Constants.getInstance()
                    .addWish(RequestAddWish(idUser,fullName,content))
                    .body()

                if(response != null){
                    if (response.success) {
                        binding.progressBar.visibility = View.GONE
                        //Them dieu uoc thanh cong
                        Toast.makeText(requireContext(), response.message, Toast.LENGTH_SHORT).show()
                        requireActivity().supportFragmentManager.beginTransaction()
                            .replace(R.id.frame_layout, WishListFragment())
                            .commit()
                    } else {
                        binding.progressBar.visibility = View.GONE
                        //Them dieu uoc k thanh cong
                        Toast.makeText(requireContext(), response.message, Toast.LENGTH_SHORT).show()
                        requireActivity().supportFragmentManager.beginTransaction()
                            .replace(R.id.frame_layout, LoginFragment())
                            .commit()
                    }
                }
            }
        }
    }
}