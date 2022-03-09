package com.example.movies_api.presentation.movie_search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movies_api.databinding.ViewHolderFavListBinding
import com.example.movies_api.domain.model.MovieTable

class MovieSearchAdapter : RecyclerView.Adapter<MovieSearchAdapter.MyViewHolder>() {


    private var listener:((MovieTable)->Unit)?=null

    var list = mutableListOf<MovieTable>()

    fun setContentList(list:MutableList<MovieTable>){
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieSearchAdapter.MyViewHolder {
        val binding = ViewHolderFavListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    fun itemClickListener(l:(MovieTable)->Unit){
        listener= l
    }

    override fun onBindViewHolder(holder: MovieSearchAdapter.MyViewHolder, position: Int) {
        holder.viewHolder.movieTable = this.list[position]
        holder.viewHolder.root.setOnClickListener {
            listener?.let {
                it(this.list[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return this.list.size
    }

    class MyViewHolder(val viewHolder: ViewHolderFavListBinding) : RecyclerView.ViewHolder(viewHolder.root) {

    }
}