package com.example.arturo.mycomics.ui.comics.views

import android.os.Bundle
import android.widget.Toast
import com.example.arturo.mycomics.R
import com.example.arturo.mycomics.infrastructure.activities.ActivityModule
import com.example.arturo.mycomics.infrastructure.extensions.app
import com.example.arturo.mycomics.ui.BaseActivity
import com.example.arturo.mycomics.ui.comics.models.ComicModel

class ComicsActivity : BaseActivity(), ComicsView {

    val component by lazy { app.component.plus(ActivityModule(this)) }

    override fun showComics(models: MutableList<ComicModel>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comics)
        component.inject(this)
        presenter.initialize(this, 0)
    }
}
