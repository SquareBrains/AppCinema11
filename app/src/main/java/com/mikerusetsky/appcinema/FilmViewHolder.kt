package com.mikerusetsky.appcinema


import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.mikerusetsky.appcinema.databinding.FilmItemBinding

//В конструктор класс передается layout, который мы создали(film_item.xml)
class FilmViewHolder (itemView: View) : ViewHolder(itemView) {

    //Привязываем View из layout к переменным
        val filmBinding = FilmItemBinding.bind(itemView)


    //В этом методе кладем данные из Film в наши View
        fun bind(film: Film) {

        //Устанавливаем заголовок
            filmBinding.title.text = film.title

        //Устанавливаем постер
            filmBinding.poster.setImageResource(film.poster)

        //Устанавливаем описание
            filmBinding.description.text = film.description
        }
    }
