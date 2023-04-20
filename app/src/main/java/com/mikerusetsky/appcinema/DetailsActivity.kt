package com.mikerusetsky.appcinema

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mikerusetsky.appcinema.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {

    private var detailBinding: ActivityDetailsBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailBinding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(detailBinding!!.root)

        setFilmsDetails()
    }

    private fun setFilmsDetails() {
        val film = intent.extras?.get("film") as Film

        detailBinding?.detailsToolbar?.title = film.title
        detailBinding?.detailsPoster?.setImageResource(film.poster)
        detailBinding?.detailsDescription?.text = film.description
    }
}