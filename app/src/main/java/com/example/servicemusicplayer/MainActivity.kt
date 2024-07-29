package com.example.servicemusicplayer

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var mediaPlayer: MediaPlayer
    private var isPrepared = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        //(local)
//        mediaPlayer = MediaPlayer.create(this, R.raw.override)

        //(internet)
        //available music: override.mp3, 104hz.mp3, kawaikutegomen.mp3
        mediaPlayer = MediaPlayer()
        mediaPlayer.setDataSource("https://buatlogin11.github.io/3sq/assets/104hz.mp3")

        // Set up click listeners for play and pause buttons
        findViewById<ImageView>(R.id.icon_play).setOnClickListener {
            playMusic()
        }

        findViewById<ImageView>(R.id.icon_pause).setOnClickListener {
            pauseMusic()
        }

        mediaPlayer.setOnPreparedListener {
            isPrepared = true
        }

        mediaPlayer.prepareAsync()
    }

    private fun playMusic() {
        if (!mediaPlayer.isPlaying) {
            if (isPrepared) {
                mediaPlayer.start()
                findViewById<ImageView>(R.id.icon_play).visibility = View.GONE
                findViewById<ImageView>(R.id.icon_pause).visibility = View.VISIBLE
                Toast.makeText(this, "Playing music...", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Preparing music, please wait...", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun pauseMusic() {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.pause()
            findViewById<ImageView>(R.id.icon_play).visibility = View.VISIBLE
            findViewById<ImageView>(R.id.icon_pause).visibility = View.GONE
            Toast.makeText(this, "Pausing music...", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release() // Release the MediaPlayer when the activity is destroyed
    }
}
