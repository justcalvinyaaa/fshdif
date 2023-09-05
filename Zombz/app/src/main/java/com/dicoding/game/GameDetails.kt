package com.dicoding.game

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.View
import android.widget.ImageView
import com.dicoding.game.databinding.ActivityGameDetailsBinding


class GameDetails : AppCompatActivity(), View.OnClickListener {
    private  lateinit var icon_back_left: ImageView
    private lateinit var binding:ActivityGameDetailsBinding

    companion object {
        const val EXTRA_GAME = "extra_game"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        icon_back_left = findViewById(R.id.icon_back_left)
        icon_back_left.setOnClickListener(this)

        val game: Game? = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_GAME, Game::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_GAME)
        }

        binding.titleDetails.text = game?.name
        if (game != null) {
            game.photo?.let { binding.imgDetails.setImageResource(it) }
        }
        binding.descriptionDetails.text = game?.description ?: ""
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.icon_back_left -> {
                val intent = Intent(this@GameDetails,MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}