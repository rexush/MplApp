package com.example.mplapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvTeam: RecyclerView
    private val list = ArrayList<Team>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvTeam = findViewById(R.id.rv_team)
        rvTeam.setHasFixedSize(true)

        list.addAll(getListTeam())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_list -> {
                rvTeam.layoutManager = LinearLayoutManager(this)
            }
            R.id.action_grid -> {
                rvTeam.layoutManager = GridLayoutManager(this, 2)
            }
            R.id.action_about -> {
                val aboutIntent = Intent(this@MainActivity, About::class.java)
                startActivity(aboutIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getListTeam(): ArrayList<Team> {
        val dataName = resources.getStringArray(R.array.data_team_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataLogo = resources.getStringArray(R.array.data_logo)
        val dataAchievement = resources.getStringArray(R.array.data_achievement)
        val dataFounder = resources.getStringArray(R.array.data_founder_team)
        val dataNegara = resources.getStringArray(R.array.data_negara_team)
        val dataPemain = resources.getStringArray(R.array.data_pemain_team)
        val listTeam = ArrayList<Team>()
        for (i in dataName.indices) {
            val team = Team(dataName[i], dataDescription[i], dataLogo[i], dataAchievement[i], dataFounder[i], dataNegara[i], dataPemain[i])
            listTeam.add(team)
        }
        return listTeam
    }

    private fun showRecyclerList() {
        rvTeam.layoutManager = LinearLayoutManager(this)
        val listHeroAdapter = ListTeamAdapter(list)
        rvTeam.adapter = listHeroAdapter
        listHeroAdapter.setOnItemClickCallback(object : ListTeamAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Team) {
                showSelectedHero(data)
            }
        })
    }

    private fun showSelectedHero(team: Team) {
        Toast.makeText(this, "You Choose " + team.name, Toast.LENGTH_SHORT).show()
    }
}