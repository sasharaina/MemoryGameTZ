package com.example.memorygame

import android.annotation.SuppressLint
import android.os.CountDownTimer
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import com.example.memorygame.MemoryCard

@SuppressLint("StaticFieldLeak")
object Variable {
    lateinit var cards: List<MemoryCard>
    lateinit var buttons: List<ImageButton>
    var indexOfSingleSelectedCard: Int? = null
    var images = mutableListOf(R.drawable.card1,R.drawable.card2,R.drawable.card3, R.drawable.card4,R.drawable.card5,R.drawable.card6,R.drawable.card7, R.drawable.card8)
    var tvResult = 0
    var tvMovesResult = 0
    var timer: CountDownTimer? = null
    var timeLeftInMillis: Long = 0
    lateinit var tvTimer : TextView
    lateinit var card1 : ImageButton
    lateinit var card2 : ImageButton
    lateinit var card3 : ImageButton
    lateinit var card4 : ImageButton
    lateinit var card5 : ImageButton
    lateinit var card6 : ImageButton
    lateinit var card7 : ImageButton
    lateinit var card8 : ImageButton
    lateinit var card9 : ImageButton
    lateinit var card10 : ImageButton
    lateinit var card11 : ImageButton
    lateinit var card12 : ImageButton
    lateinit var card13 : ImageButton
    lateinit var card14 : ImageButton
    lateinit var card15 : ImageButton
    lateinit var card16 : ImageButton
    lateinit var playBtn: Button
    lateinit var getJsonBtn: Button
    lateinit var exitBtn: Button
    lateinit var btnBack: Button
    lateinit var btnRestart: Button
    lateinit var tvMoves: TextView
    lateinit var tvRemainingTime: TextView
}