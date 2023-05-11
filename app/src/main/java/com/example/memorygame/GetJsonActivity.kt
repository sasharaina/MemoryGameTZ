package com.example.memorygame

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class GetJsonActivity : AppCompatActivity() {

    private val REQUEST_URL = "http://ip-api.com/json/"
    lateinit var btnGetData: Button
    lateinit var tvCountry: TextView
    lateinit var tvCity: TextView
    lateinit var tvTimeZone: TextView


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_json)
        btnGetData = findViewById(R.id.btnGetData)
        tvCountry = findViewById(R.id.tvCountry)
        tvCity = findViewById(R.id.tvCity)
        tvTimeZone = findViewById(R.id.tvTimeZone)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        btnGetData.setOnClickListener {
            val request = JsonObjectRequest(
                Request.Method.GET, REQUEST_URL, null,
                { response ->
                    val country = response.getString("country")
                    val city = response.getString("city")
                    val timeZone = response.getString("timezone")

                    tvCountry.text = "Country: $country"
                    tvCity.text = "City: $city"
                    tvTimeZone.text = "Time Zone: $timeZone"
                },
                { error ->
                    tvCountry.text = "Error: ${error.message}"
                    tvCity.text = ""
                    tvTimeZone.text = ""
                })

            Volley.newRequestQueue(this).add(request)
        }
    }
}