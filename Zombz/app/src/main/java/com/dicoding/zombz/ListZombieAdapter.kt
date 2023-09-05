package com.dicoding.zombz

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class ListZombieAdapter(private val listZombie: ArrayList<Zombie>) : RecyclerView.Adapter<ListZombieAdapter.ListViewHolder>(){
    private lateinit var onItemClickCallback : OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback : OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_zombie, parent,false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listZombie.size
    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto : ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvName : TextView = itemView.findViewById(R.id.tv_item_name)
        val tvDescription : TextView = itemView.findViewById(R.id.tv_item_description)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, description, photo) = listZombie[position]
        holder.imgPhoto.setImageResource(photo)
        holder.tvName.text = name
        holder.tvDescription.text = description

        holder.itemView.setOnClickListener{
            val intenDetail_Info =  Intent(holder.itemView.context, Detail_Info::class.java)
            intenDetail_Info.putExtra("key_zombie",listZombie[holder.adapterPosition])
            holder.itemView.context.startActivity(intenDetail_Info)}
    }

    interface OnItemClickCallback{
        fun onItemClick(data : Zombie)
    }
}