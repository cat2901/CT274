package com.example.b1906411_nguyenthitrang

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class MyAndroidRVAdapter(private val dataSet: ArrayList<MyDB>) :
    RecyclerView.Adapter<MyAndroidRVAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView
        val gioitinh: TextView
        val lop: TextView
        val diem:TextView

        init {
            // Define click listener for the ViewHolder's View
            name = view.findViewById(R.id.tvten)
            gioitinh = view.findViewById(R.id.tvGT)
            lop = view.findViewById(R.id.tvclass)
            diem=view.findViewById(R.id.tvMatch)

        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.user_row, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.name.setText(dataSet[position].databaseName)
        viewHolder.diem.setText(dataSet[position].databaseName)
        viewHolder.lop.setText(dataSet[position].databaseName)
        viewHolder.gioitinh.setText(dataSet[position].databaseName)

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}
