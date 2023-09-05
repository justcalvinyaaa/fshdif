package com.dicoding.game

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView

class About : AppCompatActivity(), View.OnClickListener {
    private  lateinit var icon_back_left: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        icon_back_left = findViewById(R.id.icon_back_left)

        icon_back_left.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.icon_back_left -> {
                val intent = Intent(this@About,MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}