package com.example.datastoreexample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class MyAndroidRVAdapter(private val dataSet: ArrayList<MyAndroid>) :
    RecyclerView.Adapter<MyAndroidRVAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val iv_img: ImageView
        val tv_name: TextView
        val tv_description: TextView

        init {
            // Define click listener for the ViewHolder's View
            iv_img = view.findViewById(R.id.iv_rv_img)
            tv_name = view.findViewById(R.id.tv_rv_name)
            tv_description = view.findViewById(R.id.tv_rv_description)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.rv_android_row_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.iv_img.setImageResource(dataSet[position].img)
        viewHolder.tv_name.setText(dataSet[position].name)
        viewHolder.tv_description.setText(dataSet[position].description)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}
