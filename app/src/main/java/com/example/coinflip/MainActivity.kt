package com.example.coinflip

import android.content.Context
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.seismic.ShakeDetector

class MainActivity : AppCompatActivity(), ShakeDetector.Listener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sm = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val sd = ShakeDetector(this)
        sd.start(sm)
        val flipBtn: Button = findViewById(R.id.btnFlip)
        flipBtn.setOnClickListener { flipCoin() }
    }
    override fun hearShake() {
        flipCoin()
    }
    private fun flipCoin(){
        val coin = Coin(2)
        val coinResult = coin.flip()
        val resultView: TextView = findViewById(R.id.textView)
        val resultImage: ImageView = findViewById(R.id.coinView)

        when (coinResult){
            1 -> {
                resultView.setText("It's heads!")
                resultImage.setImageResource(R.drawable.heads)
            }
            2 -> {
                resultView.setText("It's tails!")
                resultImage.setImageResource(R.drawable.tails)
            }
        }
    }
}
class Coin(val numSides: Int){
    fun flip(): Int{
        return(1..numSides).random()
    }
}