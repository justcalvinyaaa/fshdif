package com.dicoding.zombz

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class Detail_Info : AppCompatActivity(), View.OnClickListener {
    private lateinit var icon_back_left: ImageView
    private lateinit var binding:ActivityDetail

    companion object{
        const val EXTRA_ZOMBIE = "extra_zombie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetail_InfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        icon_back_left = findViewById(androidx.core.R.id.icon_back_left)
        icon_back_left.setOnClickListener(this)

        val zombie:Zombie? = if (Build.VERSION.SDK_INT >= 33){
            intent.getParcelableExtra(EXTRA_ZOMBIE, Zombie::class.java)
        }else{
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_ZOMBIE)
        }

        binding.titleDetails.text = zombie?.name
        if (zombie != null){
            zombie.photo?.let{ binding.imgDetails.setImageResource(it)}
        }
        binding.descriptionDetails.text = zombie?.description?:""

    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.icon_back_left -> {
                val intent = Intent(this@Detail_Info,MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}