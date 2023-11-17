package com.example.b1906411_nguyenthitrang

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView

class MyAndroidAdapter(val context: Context, val items: ArrayList<MyDB>) : BaseAdapter() {
    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(p0: Int): Any {
        return 0
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    @SuppressLint("MissingInflatedId")
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val view= LayoutInflater.from(context).inflate((R.layout.user_row),null)
        val name=view.findViewById<ImageView>(R.id.tvten)
        val gioitinh=view.findViewById<ImageView>(R.id.tvGT)
        val lop=view.findViewById<ImageView>(R.id.tvclass)
        val diem=view.findViewById<ImageView>(R.id.tvMatch)
        return view
    }

}