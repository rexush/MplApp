package com.example.mplapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ListTeamAdapter(private val listTeam: ArrayList<Team>): RecyclerView.Adapter<ListTeamAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgLogo: ImageView = itemView.findViewById(R.id.img_logo_team)
        val tvNameTeam: TextView = itemView.findViewById(R.id.tv_name_team)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_description_team)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_team, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listTeam.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, description, logo) = listTeam[position]
        Glide.with(holder.itemView.context)
            .load(logo) // URL Gambar
            .into(holder.imgLogo) // imageView mana yang akan diterapkan
        holder.tvNameTeam.text = name
        holder.tvDescription.text = description
        holder.itemView.setOnClickListener{onItemClickCallback.onItemClicked(listTeam[holder.adapterPosition])
            val intentDetail = Intent(holder.itemView.context, DetailTeamActivity::class.java)
            intentDetail.putExtra("key_team", listTeam[holder.adapterPosition])
            holder.itemView.context.startActivity(intentDetail)
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Team)
    }
}