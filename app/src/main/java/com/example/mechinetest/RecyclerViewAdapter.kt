package com.example.mechinetest

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mechinetest.network.Result

class RecyclerViewAdapter(var context: Context): PagingDataAdapter<Result, RecyclerViewAdapter.MyViewHolder>(
    DiffUtilCallBack()) {
    lateinit var contextt:Context

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.bind(getItem(position)!!)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapter.MyViewHolder {
        contextt =parent.context

        val inflater = LayoutInflater.from(parent.context).inflate(
            R.layout.recycler_row,
            parent,
            false
        )

        return MyViewHolder(inflater)
    }

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {

       val imageView: ImageView = view.findViewById(R.id.imageView)
        val tvName: TextView = view.findViewById(R.id.tvName)
        val tvDesc: TextView = view.findViewById(R.id.tvDesc)
        val  ll_item: LinearLayout =view.findViewById(R.id.ll_item)
        fun bind(data: Result) {
            tvName.text = data.title
            tvDesc.text = "Release Date: "+data.release_date
            ll_item.setOnClickListener{
                val intent = Intent(it.context, DetailsActivity::class.java)
                intent.putExtra("id", data.id)
                it.context.startActivity(intent)
            }


            Glide.with(imageView)
                .load("https://image.tmdb.org/t/p/w500" + data.poster_path)
                .into(imageView)


        }
    }

    class DiffUtilCallBack: DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
           return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id == newItem.id
                    && oldItem.title == newItem.title
        }

    }


}