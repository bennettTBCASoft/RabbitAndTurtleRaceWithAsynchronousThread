package com.example.thetortoiseandthehare

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.Button
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    // 建立變數以利後續綁定元件
    private lateinit var btn_start: Button
    private lateinit var sb_rabbit: SeekBar
    private lateinit var sb_turtle: SeekBar

    // 建立兩個數值，用於計算兔子與烏龜的進度
    private var progressRabbit = 0
    private var progressTurtle = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 將變數與XML元件綁定
        btn_start = findViewById(R.id.button)
        sb_rabbit = findViewById(R.id.seekBar_hare)
        sb_turtle = findViewById(R.id.seekBar_tortoise)

        // 對開始按鈕設定監聽器
        btn_start.setOnClickListener {
            btn_start.isEnabled = false
            progressRabbit = 0
            progressTurtle = 0
            sb_rabbit.progress = 0
            sb_turtle.progress = 0
            runRabbit()
            runTurtles()
        }
    }

    private fun showToast(msg: String){
        AlertDialog.Builder(this)
            .setTitle("Who is winner")
            .setMessage(msg)
            .setPositiveButton("確定"){ dialog, which ->
                Toast.makeText(this, "按下確定", Toast.LENGTH_SHORT).show()

            }.show()
    }

    private val handler = Handler(Looper.getMainLooper()) { msg ->
        if (msg.what == 1) {
            sb_rabbit.progress = progressRabbit
        }

        if (progressRabbit >= 100 && progressTurtle < 100) {
            showToast("Rabbit Win.")
            btn_start.isEnabled = true
        }

        return@Handler true
    }

    fun runRabbit(){
        Thread{
            //兔子有三分之二的機率會偷懶
            val sleepProbability = arrayOf(true, true, false)
            while (progressRabbit < 100 && progressTurtle < 100) {
                try{
                    Thread.sleep(100)
                    if(sleepProbability.random()){
                        Thread.sleep(300)
                    }
                } catch (e: InterruptedException){
                    e.printStackTrace()
                }
                progressRabbit += 3

                val msg = Message()
                msg.what = 1
                handler.sendMessage(msg)
            }
        }.start()
    }

    private fun runTurtles(){
        GlobalScope.launch(Dispatchers.Main) {
            while(progressTurtle < 100 && progressRabbit < 100) {
                delay(100)
                progressTurtle += 1
                sb_turtle.progress = progressTurtle

                if (progressTurtle >= 100 && progressRabbit < 100) {
                    showToast("Turtle Win")
                    btn_start.isEnabled = true
                }
            }
        }
    }
}