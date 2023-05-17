package com.example.mplapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class DetailTeamActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val actionBar = supportActionBar
        actionBar!!.title = "Detail"
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        val dataTeam : Team? = intent?.getParcelableExtra("key_team")

        val tvDetailName = findViewById<TextView>(R.id.tv_name_team)
        val tvDetailTeamDescription = findViewById<TextView>(R.id.tv_detail_received)
        val ivDetailLogo = findViewById<ImageView>(R.id.img_logo_team)
        val tvDetailAchievement = findViewById<TextView>(R.id.tv_detail_achievement)
        val tvDetailFounder = findViewById<TextView>(R.id.tv_founder_received)
        val tvDetailNegara = findViewById<TextView>(R.id.tv_negara_received)
        val tvDetailPemain = findViewById<TextView>(R.id.tv_pemain_received)
        val btnShare = findViewById<TextView>(R.id.btn_set_share)

        tvDetailName.text = dataTeam?.name
        tvDetailTeamDescription.text = dataTeam?.description
        Glide.with(this)
            .load(dataTeam?.logo) // URL Gambar
            .into(ivDetailLogo)
        tvDetailAchievement.text =  dataTeam?.achievement
        tvDetailFounder.text =  dataTeam?.founder
        tvDetailNegara.text =  dataTeam?.negara
        tvDetailPemain.text =  dataTeam?.pemain
        btnShare.setOnClickListener {
            // Toast.makeText(holder.itemView.context, "Share " + listTeam[holder.adapterPosition].name, Toast.LENGTH_SHORT).show()
            val intent  = Intent(Intent.ACTION_SEND)
            intent.putExtra(Intent.EXTRA_TEXT, dataTeam?.name + "\n\n" + dataTeam?.logo)
            intent.putExtra(Intent.EXTRA_SUBJECT, dataTeam?.name)
            intent.type = "text/plain"
            btnShare.context.startActivity(Intent.createChooser(intent, "Share via"))
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}