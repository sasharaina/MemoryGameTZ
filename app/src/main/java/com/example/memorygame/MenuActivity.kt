package com.example.memorygame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.example.memorygame.Variable.exitBtn
import com.example.memorygame.Variable.getJsonBtn
import com.example.memorygame.Variable.playBtn

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        playBtn = findViewById(R.id.btnPlay)
        getJsonBtn = findViewById(R.id.btnGetJSON)
        exitBtn = findViewById(R.id.btnExit)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        playBtn.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            startActivity(intent)
        }

        getJsonBtn.setOnClickListener {
            val intent = Intent(this, GetJsonActivity::class.java)
            startActivity(intent)
        }

        exitBtn.setOnClickListener {
            finish()
        }



    }

}