package com.example.arturo.mycomics.ui.comics.views

import android.os.Bundle
import android.widget.Toast
import com.example.arturo.mycomics.R
import com.example.arturo.mycomics.infrastructure.activities.ActivityModule
import com.example.arturo.mycomics.infrastructure.extensions.app
import com.example.arturo.mycomics.ui.BaseActivity
import com.example.arturo.mycomics.ui.comics.models.ComicModel
import com.example.arturo.mycomics.ui.comics.presenters.ComicsPresenter
import com.example.arturo.mycomics.ui.comics.views.adapters.ComicsAdapter
import com.example.arturo.mycomics.ui.comics.views.listeners.OnComicClickedListener
import kotlinx.android.synthetic.main.activity_comics.*
import kotlinx.android.synthetic.main.comic_list.*
import kotlinx.android.synthetic.main.toolbar.view.*
import javax.inject.Inject
import javax.inject.Named

class ComicsActivity : BaseActivity(), ComicsView {

    val component by lazy { app.component.plus(ActivityModule(this)) }

    //TODO @Inject @Named("character_id") var characterId: Int = 0

    private var adapter: ComicsAdapter? = null

    override fun showComics(models: MutableList<ComicModel>) {
        adapter?.items = models
        adapter?.notifyDataSetChanged()
    }

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comics)
        component.inject(this)

        setUpToolbar(appBar.toolbar, false)

        adapter = ComicsAdapter(this)
        adapter!!.setListener(object : OnComicClickedListener {
            override fun onComicClicked(comicModel: ComicModel) {
                //TODO navigator -> go to comic detail
            }
        })
        comicsList.adapter = adapter

        presenter.initialize(this, 1009220)
    }
}
