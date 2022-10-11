package com.openapi.mvvm.ui.main.adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.openapi.mvvm.data.api.ImageLoader
import com.openapi.mvvm.data.model.MovieCritics
import com.openapi.mvvm.databinding.ItemCriticsBinding
import kotlinx.android.synthetic.main.item_critics.view.*

class MovieCriticsAdapter(
    private val movieCriticsList: List<MovieCritics>
) : RecyclerView.Adapter<MovieCriticsViewHolder>() {

    private lateinit var binding: ItemCriticsBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieCriticsViewHolder {
        binding = ItemCriticsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieCriticsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieCriticsViewHolder, position: Int) {
        val movieCritic = movieCriticsList[position]
        if (movieCritic.multimedia?.src != null) {
            ImageLoader.loadImage(holder.itemView.ivMovie,binding.root.context,movieCritic.multimedia.src)

        }else {
            ImageLoader.clearImage(holder.itemView.ivMovie,binding.root.context)
        }
        holder.bind(movieCritic)
    }

    override fun getItemCount(): Int = movieCriticsList.size

}
