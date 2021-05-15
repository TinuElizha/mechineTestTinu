package com.example.mechinetest

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import com.bumptech.glide.Glide
import com.example.mechinetest.viewModel.DetailsViewModel
import kotlinx.android.synthetic.main.activity_details.*


class DetailsActivity(): AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        var id: Long? = intent.getLongExtra("id",0)

        val viewModel  = ViewModelProvider(this).get(DetailsViewModel::class.java)

        lifecycleScope.launchWhenCreated {
            if (id != null) {
                viewModel.getMovieDetails(id)
            }
        }
        viewModel.movieDetails.observe(this, Observer {

            Glide.with(iv_top)
                .load("https://image.tmdb.org/t/p/w500" + it.backdrop_path)
                .into(iv_top)

            original_title.text =it.original_title
            overview.text = it.overview
            status.text ="Status: "+it.status
            tagline.text ="Tagline"+ it.tagline
            vote_count.text ="Vote Count: "+it.vote_count.toString()
            vote_average.text ="Vote Avg: "+it.vote_average.toString()


        })

    }
}