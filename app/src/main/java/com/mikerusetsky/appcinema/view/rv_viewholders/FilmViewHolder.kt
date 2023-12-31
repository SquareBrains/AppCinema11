package com.mikerusetsky.appcinema.view.rv_viewholders


import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mikerusetsky.appcinema.databinding.FilmItemBinding
import com.mikerusetsky.appcinema.domain.Film
import com.mikerusetsky.remote_module.entity.ApiConstants

//В конструктор класс передается layout, который мы создали(film_item.xml)
class FilmViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val filmItemBinding = FilmItemBinding.bind(itemView)


    //Привязываем View к переменным
    private val title = filmItemBinding.title
    private val poster = filmItemBinding.poster
    private val description = filmItemBinding.description
    //Вот здесь мы находим в верстке наш прогресс бар для рейтинга
    private val ratingDonut = filmItemBinding.ratingDonut




    //В этом методе кладем данные из Film в наши View
        fun bind(film: Film) {

        //Устанавливаем заголовок
        title.text = film.title

        //Устанавливаем постер


        //Указываем контейнер, в котором будет "жить" наша картинка
        Glide.with(itemView).
            //Указываем ImageView, куда будем загружать изображение
        load(ApiConstants.IMAGES_URL + "w342" + film.poster).
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
