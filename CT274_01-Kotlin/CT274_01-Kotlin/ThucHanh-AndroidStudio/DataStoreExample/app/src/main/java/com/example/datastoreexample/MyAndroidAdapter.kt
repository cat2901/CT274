package com.example.datastoreexample

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class MyAndroidAdapter(val context: Context, val items: ArrayList<MyAndroid>) : BaseAdapter() {
    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(p0: Int): Any {
        return 0
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val view = LayoutInflater.from(context).inflate(R.layout.custom_spinner_item, null)
        val icon = view.findViewById<ImageView>(R.id.ivSpinner)
        val name = view.findViewById<TextView>(R.id.tvSpinner)
        icon.setImageResource(items[p0].img)
        name.setText(items[p0].name)

        return view
    }

}
