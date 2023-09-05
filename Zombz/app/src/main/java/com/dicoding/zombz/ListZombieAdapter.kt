package com.dicoding.zombz

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class ListZombieAdapter(private val listZombie: ArrayList<Zombie>) : RecyclerView.Adapter<ListZombieAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback : OnItemClickCallBack

    interface OnItemClickCallBack {
        fun onItemClicked(zombie: Zombie)
    }
    fun setOnItemClickCallback(onItemClickCallBack: OnItemClickCallBack){
        this.onItemClickCallback = onItemClickCallBack

    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val itemName: TextView = itemView.findViewById(R.id.tv_item_name)
        val itemDescription: TextView = itemView.findViewById(R.id.tv_item_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_zombie, parent, false)
        return ListViewHolder(view)
    }


    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, description, photo) = listZombie[position]
        photo?.let { holder.itemPhoto.setImageResource(it) }
        holder.itemName.text = name
        holder.itemDescription.text = description

        holder.itemView.setOnClickListener{ this.onItemClickCallback.onItemClicked(listZombie[holder.adapterPosition]) }
    }
    override fun getItemCount(): Int = listZombie.size
}