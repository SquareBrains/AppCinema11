package com.mikerusetsky.appcinema

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.widget.Toolbar
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationBarMenu
import com.mikerusetsky.appcinema.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var filmsAdapter: FilmListRecyclerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        //находим наш RV
        binding?.mainRecycler.apply {

            filmsAdapter =
                FilmListRecyclerAdapter(object : FilmListRecyclerAdapter.OnItemClickListener {
                    override fun click(film: Film) {
                        val bundle = Bundle()
                        bundle.putParcelable("film", film)
                        val intent = Intent(this@MainActivity, DetailsActivity::class.java)
                        intent.putExtras(bundle)
                        startActivity(intent)
                    }
                })

            this!!.adapter = filmsAdapter
            this!!.layoutManager = LinearLayoutManager(this@MainActivity)
            val decorator = TopSpacingItemDecoration(8)
            this!!.addItemDecoration(decorator)

            filmsAdapter.addItems(filmsDataBase)

        }
    }
}


