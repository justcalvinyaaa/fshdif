package com.dicoding.game

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListGameAdapter(private val listGame: ArrayList<Game>) : RecyclerView.Adapter<ListGameAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallBack

    interface OnItemClickCallBack {
        fun onItemClicked(game: Game)
    }

    fun setOnItemClickCallback(onItemClickCallBack: OnItemClickCallBack){
        this.onItemClickCallback = onItemClickCallBack

    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val gameCover: ImageView = itemView.findViewById(R.id.img_game_cover)
        val gameTitle: TextView = itemView.findViewById(R.id.tv_game_title)
        val gameDescription: TextView = itemView.findViewById(R.id.tv_game_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.game_list, parent, false)
        return ListViewHolder(view)
    }


    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, description, photo) = listGame[position]
        photo?.let { holder.gameCover.setImageResource(it) }
        holder.gameTitle.text = name
        holder.gameDescription.text = description

        holder.itemView.setOnClickListener{ this.onItemClickCallback.onItemClicked(listGame[holder.adapterPosition]) }
    }
    override fun getItemCount(): Int = listGame.size
}
