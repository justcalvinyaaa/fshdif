package com.dicoding.game

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.ImageView
import android.widget.Switch
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager


class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var switcher: Switch
    private var nightMode = false
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    private lateinit var gameList: RecyclerView
    private lateinit var icon_about: ImageView
    private val list = ArrayList<Game>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()
        switcher = findViewById(R.id.switcher)

        sharedPreferences = getSharedPreferences("Mode", Context.MODE_PRIVATE)
        nightMode = sharedPreferences.getBoolean("night", false)

        if (nightMode) {
            switcher.isChecked = true
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }

        switcher.setOnClickListener {
            if (nightMode) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                editor = sharedPreferences.edit()
                editor.putBoolean("night", false)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                editor = sharedPreferences.edit()
                editor.putBoolean("night", true)
            }
            editor.apply()
        }


        icon_about = findViewById(R.id.icon_about)
        gameList = findViewById(R.id.game_list)
        gameList.setHasFixedSize(true)
        list.addAll(getListGame())
        showRecyclerList()

        icon_about.setOnClickListener(this)
    }

    private fun getListGame(): ArrayList<Game> {
        val gameName = resources.getStringArray(R.array.game_name)
        val gameDescription = resources.getStringArray(R.array.game_description)
        val gamePhoto = resources.obtainTypedArray(R.array.game_photo)
        val listGame = ArrayList<Game>()
        for (i in gameName.indices) {
            val game = Game(gameName[i], gameDescription[i], gamePhoto.getResourceId(i, -1))
            listGame.add(game)
        }
        return listGame
    }

    private fun showRecyclerList() {
        gameList.layoutManager = LinearLayoutManager(this)
        val listGameAdapter = ListGameAdapter(list)
        gameList.adapter = listGameAdapter
        listGameAdapter.setOnItemClickCallback(object : ListGameAdapter.OnItemClickCallBack {
            override fun onItemClicked(game: Game) {
                val moveGameDetail = Intent(this@MainActivity, GameDetails::class.java)
                moveGameDetail.putExtra(GameDetails.EXTRA_GAME, game)
                startActivity(moveGameDetail)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.about, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.icon_about -> {
                val intent = Intent(this@MainActivity, About::class.java)
                startActivity(intent)
            }
        }
    }
}