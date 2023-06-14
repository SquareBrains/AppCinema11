package com.mikerusetsky.appcinema


import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.mikerusetsky.appcinema.databinding.FilmItemBinding

//В конструктор класс передается layout, который мы создали(film_item.xml)
class FilmViewHolder (binding: FilmItemBinding) : RecyclerView.ViewHolder(binding.root) {

    //Привязываем View к переменным
    private val title = binding.title
    private val poster = binding.poster
    private val description = binding.description
    //Вот здесь мы находим в верстке наш прогресс бар для рейтинга
    private val ratingDonut = binding.ratingDonut




    //В этом методе кладем данные из Film в наши View
        fun bind(film: Film) {

        //Устанавливаем заголовок
        title.text = film.title

        //Устанавливаем постер


        //Указываем контейнер, в котором будет "жить" наша картинка
        Glide.with(itemView).
            //Указываем ImageView, куда будем загружать изображение
        load(film.poster).
            //Центруем изображение
            centerCrop().
            //Указываем ImageView, куда будем загружать изображение
        into(poster)

        //Устанавливаем описание
        description.text = film.description

        //Устанавливаем рэйтинг
        ratingDonut.setProgress((film.rating * 10).toInt())
        }
    }
