package com.example.arturo.mycomics.ui.comics.views

import android.os.Bundle
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v4.content.ContextCompat
import android.text.Html
import android.view.MenuItem
import com.example.arturo.mycomics.R
import com.example.arturo.mycomics.infrastructure.activities.ActivityModule
import com.example.arturo.mycomics.infrastructure.extensions.app
import com.example.arturo.mycomics.ui.BaseActivity
import com.example.arturo.mycomics.ui.comics.models.ComicModel
import com.example.arturo.mycomics.ui.comics.presenters.ComicDetailPresenter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_comic_detail.*
import kotlinx.android.synthetic.main.toolbar.view.*
import javax.inject.Inject

class ComicDetailActivity : BaseActivity(), ComicDetailView {

    val component by lazy { app.component.plus(ActivityModule(this)) }

    @Inject
    lateinit var presenter: ComicDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comic_detail)
        component.inject(this)
        setUpToolbar(appBar.toolbar, true)
        val comicId = intent.getIntExtra(EXTRA_COMIC.toString(), 0)
        presenter.initialize(this, comicId)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showComic(comic: ComicModel) {
        setupComicInfo(comic)
    }

    private fun setupComicInfo(comicModel: ComicModel?) {
        if (comicModel != null) {
            setupTitle(comicModel.title)
            setupDescription(comicModel.description)
            showImage(comicModel.thumbnailUrl)
            setupAppBar(comicModel.title)
        } else {
            comicTitle.text = getString(R.string.error_no_title)
            comicDescription.text = getString(R.string.error_comic)
        }
    }

    private fun setupAppBar(title: String) {
        val appBarLayout = toolbarLayout as CollapsingToolbarLayout
        appBarLayout.title = title
        appBarLayout.setExpandedTitleColor(ContextCompat.getColor(this, android.R.color.transparent))
    }

    @SuppressWarnings("deprecation")
    private fun setupDescription(description: String) {
        if (description.isEmpty()) {
            comicDescription.text = getString(R.string.error_no_description)
        }
        comicDescription.text = Html.fromHtml(description)
    }

    private fun setupTitle(title: String) {
        if (title.isEmpty()) {
            comicTitle.text = getString(R.string.error_no_title)
        }
        comicTitle.text = title
    }

    private fun showImage(imageUrl: String) {
        Picasso.with(this).load(imageUrl)
                .fit().centerCrop().into(this.comicImage)
    }

    object EXTRA_COMIC {
        @JvmStatic val EXTRA_COMIC = "EXTRA_COMIC"
    }
}
