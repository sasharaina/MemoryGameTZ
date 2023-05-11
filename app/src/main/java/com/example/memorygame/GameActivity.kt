package com.example.memorygame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.WindowManager
import android.widget.*
import com.example.memorygame.Variable.buttons
import com.example.memorygame.Variable.card1
import com.example.memorygame.Variable.card10
import com.example.memorygame.Variable.card11
import com.example.memorygame.Variable.card12
import com.example.memorygame.Variable.card13
import com.example.memorygame.Variable.card14
import com.example.memorygame.Variable.card15
import com.example.memorygame.Variable.card16
import com.example.memorygame.Variable.card2
import com.example.memorygame.Variable.card3
import com.example.memorygame.Variable.card4
import com.example.memorygame.Variable.card5
import com.example.memorygame.Variable.card6
import com.example.memorygame.Variable.card7
import com.example.memorygame.Variable.card8
import com.example.memorygame.Variable.card9
import com.example.memorygame.Variable.cards
import com.example.memorygame.Variable.images
import com.example.memorygame.Variable.indexOfSingleSelectedCard
import com.example.memorygame.Variable.timeLeftInMillis
import com.example.memorygame.Variable.timer
import com.example.memorygame.Variable.tvMovesResult
import com.example.memorygame.Variable.tvResult
import com.example.memorygame.Variable.tvTimer

class GameActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        tvTimer = findViewById(R.id.tvTimer)
        startTimer()

         card1 = findViewById(R.id.card1)
         card2 = findViewById(R.id.card2)
         card3 = findViewById(R.id.card3)
         card4 = findViewById(R.id.card4)
         card5 = findViewById(R.id.card5)
         card6 = findViewById(R.id.card6)
         card7 = findViewById(R.id.card7)
         card8 = findViewById(R.id.card8)
         card9 = findViewById(R.id.card9)
         card10 = findViewById(R.id.card10)
         card11 = findViewById(R.id.card11)
         card12 = findViewById(R.id.card12)
         card13 = findViewById(R.id.card13)
         card14 = findViewById(R.id.card14)
         card15 = findViewById(R.id.card15)
         card16 = findViewById(R.id.card16)

       val btnRestart = findViewById<Button>(R.id.btnRestart)
        btnRestart.setOnClickListener {
            Toast.makeText(this,"Tap board to reset game!", Toast.LENGTH_SHORT).show()
            restartGame()
        }

        images.addAll(images)
        images.shuffle()
        buttons = listOf(card1, card2, card3, card4, card5,card6, card7, card8,
            card9,card10,card11,card12,card13,card14,card15,card16)
        cards = buttons.indices.map { index ->
            MemoryCard(images[index])
        }

        buttons.forEachIndexed { index, button ->
            button.setOnClickListener {
                updateModels(index)
                updateViews()
            }
        }
    }

    private fun startTimer() {
        val timerDurationInMillis: Long = 5 * 60 * 1000
        timer = object : CountDownTimer(timerDurationInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMillis = millisUntilFinished
                updateTimer()
            }
            override fun onFinish() {
                Toast.makeText(this@GameActivity, "GAME OVER", Toast.LENGTH_LONG).show()
                val sendIntent = Intent(this@GameActivity, GameOverActivity::class.java)
                sendIntent.putExtra("moves", tvMovesResult)
                sendIntent.putExtra("remainingTime", timeLeftInMillis)
                startActivity(sendIntent)
                finish()
            }
        }.start()
    }

    private fun updateTimer() {
        val minutes = (timeLeftInMillis / 1000) / 60
        val seconds = (timeLeftInMillis / 1000) % 60
        tvTimer.text = "%02d:%02d".format(minutes, seconds)
    }



    private fun updateViews() {
        cards.forEachIndexed { index, card ->
            val button = buttons[index]

            button.setImageResource(if (card.isFaceUp) card.identifier else R.drawable.card_back)
        }
    }

    private fun updateModels(position: Int) {
        val card = cards[position]
        if (card.isFaceUp) {
            Toast.makeText(this, "Invalid move!", Toast.LENGTH_SHORT).show()
            return
        }

        if (indexOfSingleSelectedCard == null) {
            restoreCards()
            indexOfSingleSelectedCard = position
        } else {
            checkForMatch(indexOfSingleSelectedCard!!, position)
            indexOfSingleSelectedCard = null
        }
        card.isFaceUp = !card.isFaceUp
        buttons[position].animate().apply {
            duration = 500
            rotationYBy(360f)
        }.start()
    }

    private fun restoreCards() {

        for (card in cards) {
            if (!card.isMatched) {
                card.isFaceUp = false
            }
        }
    }

    private fun restartGame() {
        cards.forEach {
            it.isFaceUp = false
            it.isMatched = false
        }
        images.shuffle()
        tvResult = 0
        tvMovesResult = 0
        timer?.cancel()
        startTimer()
        images.addAll(images)
        images.shuffle()

        buttons = listOf(card1, card2, card3, card4, card5,card6, card7, card8,
            card9,card10,card11,card12,card13,card14,card15,card16)


        cards = buttons.indices.map { index ->
            MemoryCard(images[index])
        }

        buttons.forEachIndexed { index, button ->
            button.setOnClickListener {
                updateModels(index)
                updateViews()
            }
        }
    }

    private fun checkForMatch(position1: Int, position2: Int) {
        tvMovesResult += 1
        var tvTrys = findViewById<TextView>(R.id.tvTrys)
        tvTrys.text = tvMovesResult.toString()
        if (cards[position1].identifier == cards[position2].identifier) {

            var tvPairFound = findViewById<TextView>(R.id.tvPairFound)
            tvResult += 1
            tvPairFound.text = tvResult.toString()
            if(tvResult == 8){
                Toast.makeText(this, "CONGRATULATIONS", Toast.LENGTH_LONG).show()
            }
            cards[position1].isMatched = true
            cards[position2].isMatched = true
            if (tvResult == 8) {
                val sendIntent = Intent(this, GameOverActivity::class.java)
                sendIntent.putExtra("moves", tvMovesResult)
                sendIntent.putExtra("remainingTime", timeLeftInMillis)
                startActivity(sendIntent)
                finish()
            }

        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        timer?.cancel()
    }
}