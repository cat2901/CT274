package com.example.ttappcontacts

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RVAdapter(private val context: Context, private val dataSet: ArrayList<Result>) :
    RecyclerView.Adapter<RVAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivAvatar: ImageView
        val tvName: TextView
        val tvEmail: TextView
        val tvAddress: TextView
        val tvPhoneNumber: TextView

        init {
            // Define click listener for the ViewHolder's View
            ivAvatar = view.findViewById(R.id.ivAvatar)
            tvName = view.findViewById(R.id.tvName)
            tvEmail = view.findViewById(R.id.tvEmail)
            tvAddress = view.findViewById(R.id.tvAddress)
            tvPhoneNumber = view.findViewById(R.id.tvPhoneNumber)
        }


    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.rv_android_contacts, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
//        viewHolder.ivAvatar.setText(dataSet[position]._id)
        viewHolder.tvName.setText(dataSet[position].name.title + " " + dataSet[position].name.first + " " + dataSet[position].name.last)
        viewHolder.tvEmail.setText(dataSet[position].email)
        viewHolder.tvAddress.setText(dataSet[position].location.city)
        viewHolder.tvPhoneNumber.setText(dataSet[position].phone)
        Glide.with(context).load(dataSet[position].picture.thumbnail).into(viewHolder.ivAvatar);

        viewHolder.itemView.setOnClickListener { v ->
            val intent = Intent(v.context, ContactActivity::class.java)
            intent.putExtra(Const.AVT, dataSet[position].picture.thumbnail)
            intent.putExtra(Const.NAME, dataSet[position].name.first)
            intent.putExtra(Const.EMAIL, dataSet[position].email)
            intent.putExtra(Const.ADDRESS, dataSet[position].location.city)
            intent.putExtra(Const.PHONE, dataSet[position].phone)
            v.context.startActivity(intent)
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}
