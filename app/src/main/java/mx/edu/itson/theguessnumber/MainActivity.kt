package mx.edu.itson.theguessnumber

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    var minValue = 0
    var maxValue = 100
    var num: Int = 0
    var won = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val guessings = findViewById<TextView>(R.id.guessings)
        val down = findViewById<Button>(R.id.down)
        val up = findViewById<Button>(R.id.up)
        val generate = findViewById<Button>(R.id.generate)
        val guessed = findViewById<Button>(R.id.guessed)

        generate.setOnClickListener {
            num = Random.nextInt(minValue, maxValue)
            guessings.setText(num.toString())
            generate.visibility = View.INVISIBLE
            guessed.visibility = View.VISIBLE
        }

        up.setOnClickListener {
            minValue = num
            if (checkingLimits()){
                num = Random.nextInt(minValue, maxValue)
                guessings.setText(num.toString())
            } else {
                guessings.setText("No es posible, ganaste >:(")
            }

        }

        down.setOnClickListener {
            maxValue = num
            if (checkingLimits()){
                num = Random.nextInt(minValue, maxValue)
                guessings.setText(num.toString())
            } else {
                guessings.setText("No es posible, ganaste >:(")
            }

        }

        guessed.setOnClickListener {
            if(!won){
                guessings.setText("Adiviné, tu numero es el " +num)
                guessed.setText("Volver a jugar")
                won = true
            } else {
                generate.visibility = View.VISIBLE
                guessings.setText("Tap on generate to start")
                guessed.visibility = View.GONE
                resetValues()
            }

        }

    }

    fun resetValues(){
        minValue = 0
        maxValue = 100
        num = 0
        won = false
    }

    fun checkingLimits(): Boolean{
        return minValue != maxValue
    }

}