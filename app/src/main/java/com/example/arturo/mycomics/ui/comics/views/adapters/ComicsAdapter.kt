package com.example.arturo.mycomics.ui.comics.views.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.arturo.mycomics.R
import com.example.arturo.mycomics.ui.comics.models.ComicModel
import com.example.arturo.mycomics.ui.comics.views.viewholders.ComicViewHolder
import com.example.arturo.mycomics.ui.comics.views.listeners.OnComicClickedListener

class ComicsAdapter(private val context: Context) : RecyclerView.Adapter<ComicViewHolder>() {

    private var onComicClickedListener: OnComicClickedListener? = null
    var items: List<ComicModel>? = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.comic_list_content, parent, false)
        return ComicViewHolder(view)
    }

    override fun onBindViewHolder(holder: ComicViewHolder, position: Int) {
        val model = items!![position]
        holder.bind(model, context, onComicClickedListener)
    }

    override fun getItemCount(): Int {
        return items!!.size
    }

    fun setListener(listener: OnComicClickedListener) {
        this.onComicClickedListener = listener
    }
}