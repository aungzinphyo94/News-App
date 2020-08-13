package com.azp.newsapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.azp.newsapp.R
import com.azp.newsapp.model.Article
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_home.view.*

class HomeAdapter(var articleList: List<Article> = ArrayList<Article>()) :
    RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    var mClickListener: ClickListener? = null

    fun setOnClickListener(clickListener: ClickListener){
        this.mClickListener = clickListener
    }

    inner class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        lateinit var article: Article

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(article: Article) {
            this.article = article
            itemView.articleTitle.text = article.title
            itemView.articleDate.text = article.publishedAt
            itemView.articleDesc.text = article.description
            Picasso.get()
                .load(article.urlToImage)
                .placeholder(R.drawable.ic_launcher_background)
                .into(itemView.articleImage)
        }

        override fun onClick(view: View?) {
            mClickListener?.onClcik(article)
        }
    }

    fun updateArticle(articleList: List<Article>) {
        this.articleList = articleList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_home, parent, false)
        return HomeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return articleList.size
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(articleList.get(position))
    }

    interface ClickListener {
        fun onClcik(article: Article)
    }

}