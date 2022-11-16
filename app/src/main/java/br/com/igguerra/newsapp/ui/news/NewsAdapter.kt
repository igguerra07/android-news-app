package br.com.igguerra.newsapp.ui.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.igguerra.newsapp.R
import br.com.igguerra.newsapp.databinding.ItemNewsBinding
import br.com.igguerra.newsapp.entities.News
import coil.load
import coil.size.Scale
import coil.transform.RoundedCornersTransformation

class NewsAdapter : ListAdapter<News, NewsAdapter.NewsViewHolder>(NewsDiffCallBack()) {
    lateinit var binding: ItemNewsBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ItemNewsBinding.inflate(inflater, parent, false)
        return NewsViewHolder()
    }

    override fun getItemViewType(position: Int): Int = position

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) =
        holder.bind(getItem(position))


    inner class NewsViewHolder : RecyclerView.ViewHolder(binding.root) {
        fun bind(news: News) {
            with(binding) {
                title.text = news.title
                publishedAt.text = news.author
                description.text = news.description
                poster.load(news.image) {
                    scale(Scale.FILL)
                    crossfade(true)
                    error(R.drawable.ic_launcher_background)
                    placeholder(R.drawable.ic_launcher_background)
                    transformations(RoundedCornersTransformation(topRight = 8f, topLeft = 8f))
                }
            }
        }
    }

    class NewsDiffCallBack : DiffUtil.ItemCallback<News>() {

        override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem == newItem
        }
    }
}




