package com.example.memorygame

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.memorygame.Variable.tvMoves
import com.example.memorygame.Variable.tvRemainingTime
import java.util.concurrent.TimeUnit


class GameOverActivity : AppCompatActivity() {

    lateinit var menuBtn: Button

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_over)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        tvMoves = findViewById(R.id.tv_moves);
        tvRemainingTime = findViewById(R.id.tv_remaining_time);
        menuBtn = findViewById(R.id.menuBtn)

        val moves = intent.getIntExtra("moves", 0)
        val timeLeftInMillis = intent.getLongExtra("remainingTime", 0)

        tvMoves.text = "Moves: $moves"
        tvRemainingTime.text = "Remaining Time: " + "%02d:%02d".format(TimeUnit.MILLISECONDS.toMinutes(timeLeftInMillis), TimeUnit.MILLISECONDS.toSeconds(timeLeftInMillis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(timeLeftInMillis)))

        menuBtn.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }


    }
}