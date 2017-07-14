package com.example.arturo.mycomics.ui.comics.views.viewholders

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.arturo.mycomics.ui.comics.models.ComicModel
import com.example.arturo.mycomics.ui.comics.views.listeners.OnComicClickedListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.comic_list_content.view.*

class ComicViewHolder(val comicView: View) : RecyclerView.ViewHolder(comicView) {

    fun bind(model: ComicModel, context: Context,
             onComicClickedListener: OnComicClickedListener?) {
        itemView.title!!.text = model.title

        Picasso.with(context).load(model.thumbnailUrl).fit().centerCrop().into(
                itemView.thumbnail)

        comicView.setOnClickListener {
            if (onComicClickedListener != null) {
                onComicClickedListener!!.onComicClicked(model)
            }
        }
    }
}
