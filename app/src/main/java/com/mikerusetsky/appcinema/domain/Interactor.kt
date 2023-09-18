package com.mikerusetsky.appcinema.domain

import android.database.Observable
import android.telecom.Call
import androidx.lifecycle.LiveData
import com.mikerusetsky.appcinema.MyApi
import com.mikerusetsky.appcinema.TmdbApi
import com.mikerusetsky.appcinema.data.Entity.TmdbResultsDto
import com.mikerusetsky.appcinema.data.MainRepository
import com.mikerusetsky.appcinema.data.PreferenceProvider
import com.mikerusetsky.appcinema.utils.Converter
import com.mikerusetsky.appcinema.viewmodel.HomeFragmentViewModel
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import okhttp3.Callback
import okhttp3.Response

class Interactor(private val repo: MainRepository, private val retrofitService: TmdbApi, private val preferences: PreferenceProvider) {
    var progressBarState: BehaviorSubject<Boolean> = BehaviorSubject.create()

    //В конструктор мы будем передавать коллбэк из вью модели, чтобы реагировать на то, когда фильмы будут получены
    //и страницу, которую нужно загрузить (это для пагинации)

    fun getFilmsFromApi(page: Int) {
        //Показываем ProgressBar
        progressBarState.onNext(true)

        //Метод getDefaultCategoryFromPreferences() будет получать при каждом запросе нужный нам список фильмов
        retrofitService.getFilms(getDefaultCategoryFromPreferences(), MyApi.API, "ru-RU", page).enqueue(object : retrofit2.Callback<TmdbResultsDto> {
            override fun onResponse(call: retrofit2.Call<TmdbResultsDto>, response: retrofit2.Response<TmdbResultsDto>) {
                val list = Converter.convertApiListToDtoList(response.body()?.tmdbFilms)

                //Кладем фильмы в бд
                //В случае успешного ответа кладем фильмы в БД и выключаем ProgressBar
                Completable.fromSingle<List<Film>> {
                    repo.putToDb(list)
                }
                    .subscribeOn(Schedulers.io())
                    .subscribe()
            }

            override fun onFailure(call: retrofit2.Call<TmdbResultsDto>, t: Throwable) {
                //В случае провала выключаем ProgressBar
                progressBarState.onNext(false)
            }
        })
    }
    //Метод для сохранения настроек
    fun saveDefaultCategoryToPreferences(category: String) {
        preferences.saveDefaultCategory(category)
    }
    //Метод для получения настроек
    fun getDefaultCategoryFromPreferences() = preferences.getDefaultCategory()
    fun getFilmsFromDB(): Observable<List<Film>> = repo.getAllFromDB()
}
