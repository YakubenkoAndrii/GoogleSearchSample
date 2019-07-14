package by.search.app.googlesearch.presentation.search.searchfragment

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.search.app.googlesearch.R
import by.search.app.googlesearch.data.entity.search.SearchItem
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.item_search.view.*

class SearchResultAdapter : RecyclerView.Adapter<SearchResultAdapter.ViewHolder>() {

    private val items: MutableList<SearchItem> = mutableListOf()
    var toBrowser: ((String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_search, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.itemView.apply {
            tvDescription.text = item.description

            Glide.with(ivLogo.context)
                .load(item.pageMap?.images?.get(0)?.imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .placeholder(R.drawable.ic_error_placeholder)
                .into(ivLogo)

            setOnClickListener { toBrowser?.invoke(item.descriptionUrl) }

        }
    }

    fun setItems(newItems: MutableList<SearchItem>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}