package com.example.b2012045_huynhtrungtin_mymap

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.b2012045_huynhtrungtin_mymap.models.UserMap

private const val TAG = "MapsAdapter"
class MapsAdapter(val context: Context, val userMap: List<UserMap>,
                  val onClickListener: OnClickListener) :
    RecyclerView.Adapter<MapsAdapter.MyViewHolder>() {
    interface OnClickListener{
        fun onItemClick(position: Int)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.row_place, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MapsAdapter.MyViewHolder, position: Int) {
        val userMap = userMap[position]
        val tvTitle = holder.itemView.findViewById<TextView>(R.id.tv_place)
        tvTitle.text = userMap.title
        holder.itemView.setOnClickListener {
            Log.i(TAG, "Click on position $position")
            onClickListener.onItemClick(position)
        }
    }

    override fun getItemCount(): Int = userMap.size
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    }


}