package com.example.memorygame

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    private val SPLASH_TIME_OUT: Long = 3000
    private var progressBarStatus = 0
    private var handler: Handler? = null
    lateinit var progress_bar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        progress_bar = findViewById(R.id.progress_bar)

        handler = Handler(Looper.getMainLooper())
        Thread(Runnable {
            while (progressBarStatus < 100) {
                progressBarStatus++
                handler?.post(Runnable {
                    progress_bar.progress = progressBarStatus
                })
                try {
                    Thread.sleep(SPLASH_TIME_OUT / 100)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
            handler?.postDelayed(Runnable {
                startActivity(Intent(this@SplashActivity, MenuActivity::class.java))
                finish()
            }, 1000)
        }).start()
    }
}
