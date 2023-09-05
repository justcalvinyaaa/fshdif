package com.dicoding.zombz

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.dicoding.game.ActivityDetailInfoBinding

class Detail_Info : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding:ActivityDetailInfoBinding

    companion object {
        const val EXTRA_Zombie = "extra_zombie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val zombie: Zombie? = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_ZOMBIE, Zombie::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_ZOMBIE)
        }

        binding.titleDetails.text = zombie?.name
        if (zombie != null) {
            zombie.photo?.let { binding.imgDetails.setImageResource(it) }
        }
        binding.descriptionDetails.text = zombie?.description ?: ""
    }


}