package com.dicoding.zombz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.dicoding.zombz.Detail_Info.Companion.EXTRA_ZOMBIE

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var rvZombie: RecyclerView
    private val list = ArrayList<Zombie>()
    private lateinit var pipel: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pipel = findViewById(R.id.pipel)
        rvZombie = findViewById(R.id.rv_zombies)
        rvZombie.setHasFixedSize(true)
        list.addAll(getListZombies())
        showRecyclerList()

        pipel.setOnClickListener(this)
    }

    private fun getListZombies():ArrayList<Zombie>{
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listZombie = ArrayList<Zombie>()
        for (i in dataName.indices) {
            val zombie = Zombie(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1))
            listZombie.add(zombie)
        }
        return listZombie
    }


    private fun showRecyclerList() {
        rvZombie.layoutManager = LinearLayoutManager(this)
        val listZombieAdapter = ListZombieAdapter(list)
        rvZombie.adapter = listZombieAdapter
        listZombieAdapter.setOnItemClickCallback(object : ListZombieAdapter.OnItemClickCallBack {
            override fun onItemClicked(zombie: Zombie) {
                val moveZombieDetail = Intent(this@MainActivity, Detail_Info::class.java)
                moveZombieDetail.putExtra(Detail_Info.EXTRA_ZOMBIE, zombie)
                startActivity(moveZombieDetail)
            }
        })
    }
    override fun onClick(view: View?) {
        when(view?.id){
            R.id.pipel -> {
                val intent = Intent(this@MainActivity, Detail_About::class.java)
                startActivity(intent)
            }
        }
    }
}