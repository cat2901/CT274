package com.example.baitapthbuoi2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class RVAdapter(private val dataSet: ResponeData) :
    RecyclerView.Adapter<RVAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvID: TextView
        val tvContent: TextView
        val tvAuthor: TextView
        val tvTags: TextView
        val tvAuthorSlug: TextView
        val tvLength: TextView
        val tvDateAdded: TextView
        val tvDateModified: TextView

        init {
            // Define click listener for the ViewHolder's View
            tvID = view.findViewById(R.id.tvID)
            tvContent = view.findViewById(R.id.tvContent)
            tvAuthor = view.findViewById(R.id.tvAuthor)
            tvTags = view.findViewById(R.id.tvTags)
            tvAuthorSlug = view.findViewById(R.id.tvAuthorSlug)
            tvLength = view.findViewById(R.id.tvLength)
            tvDateAdded = view.findViewById(R.id.tvDateAdded)
            tvDateModified = view.findViewById(R.id.tvDateModified)
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
        viewHolder.tvID.setText(dataSet[position]._id)
        viewHolder.tvContent.setText(dataSet[position].content)
        viewHolder.tvAuthor.setText(dataSet[position].author)
        viewHolder.tvTags.setText(dataSet[position].tags.toString()) // LUU Y ================
        viewHolder.tvAuthorSlug.setText(dataSet[position].authorSlug)
        viewHolder.tvLength.setText(dataSet[position].length.toString())
        viewHolder.tvDateAdded.setText(dataSet[position].dateAdded)
        viewHolder.tvDateModified.setText(dataSet[position].dateModified)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}
