package com.dicoding.zombz

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.widget.ImageView
import com.dicoding.zombz.Zombie
import com.dicoding.zombz.databinding.ActivityDetailInfoBinding

class Detail_Info : AppCompatActivity(){

    private lateinit var binding: ActivityDetailInfoBinding
    companion object {
        const val EXTRA_ZOMBIE = "extra_zombie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val zombie: Zombie? = intent.getParcelableExtra(EXTRA_ZOMBIE)

        binding.zombTitle.text = zombie?.name
        zombie?.photo?.let { binding.zombImage.setImageResource(it) }
        binding.zombDetails.text = zombie?.description ?: ""


    }

   
}
