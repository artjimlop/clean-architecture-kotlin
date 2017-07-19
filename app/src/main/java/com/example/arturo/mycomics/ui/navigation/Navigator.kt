package com.example.arturo.mycomics.ui.navigation

import android.content.Context
import android.content.Intent
import com.example.arturo.mycomics.ui.comics.models.ComicModel
import com.example.arturo.mycomics.ui.comics.views.ComicDetailActivity
import javax.inject.Inject

class Navigator @Inject constructor() {

    fun goToComicDetail(context: Context, comicModel: ComicModel) {
        val comicDetailIntent = Intent(context, ComicDetailActivity::class.java)
        comicDetailIntent.putExtra(ComicDetailActivity.EXTRA_COMIC.toString(), comicModel.id)
        context.startActivity(comicDetailIntent)
    }
}
