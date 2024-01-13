package com.example.hm_7_1

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider


class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnStart: Button = findViewById(R.id.btn_start)
        val btnStop: Button = findViewById(R.id.btn_stop)
        val btnResed: Button = findViewById(R.id.btn_reset)
        val tvTimer: TextView = findViewById(R.id.tv_timer)


        btnStart.setOnClickListener {
            viewModel.startTimer()
        }

        btnStop.setOnClickListener {
            viewModel.stopTimer()
        }
        btnResed.setOnClickListener {
            viewModel.resetTimer()
        }

        viewModel.elapsedTime.observe(this) { elapsedTime ->
            tvTimer.text = formatTime(elapsedTime)
        }

    }

    private fun formatTime(seconds: Long): String {
        val minutes = seconds / 60
        val remainingSeconds = seconds % 60
        return String.format("%02d:%02d", minutes, remainingSeconds)
    }
}