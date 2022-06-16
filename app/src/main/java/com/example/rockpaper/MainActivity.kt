package com.example.rockpaper

import android.app.AlertDialog
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var textAction: TextView
    private lateinit var textNewAction: TextView
    private lateinit var textResult: TextView
    private lateinit var randImage: ImageView
    private lateinit var buttonChoose: Button
    private lateinit var buttonFight: Button
    private lateinit var buttonNewFight: Button
    private lateinit var userChoose: ImageView
    private lateinit var imageView: ImageView
    private val images = listOf(R.drawable.rock, R.drawable.paper, R.drawable.scissors)
    private val messages = listOf("Вы выбрали камень", "Вы выбрали бумагу", "Вы выбрали ножницы")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)
        setup()
    }

    private fun setup() {
        textAction = findViewById(R.id.textAction)
        textNewAction = findViewById(R.id.textNewAction)
        textResult = findViewById(R.id.textResult)
        randImage = findViewById(R.id.randomizer)
        buttonChoose = findViewById(R.id.buttonChoose)
        buttonFight = findViewById(R.id.buttonFight)
        buttonNewFight = findViewById(R.id.buttonNewFight)
        userChoose = findViewById(R.id.userChoose)
        imageView = findViewById(R.id.image)
        buttonChoose.setOnClickListener {
            choose()
        }
    }

    private fun choose() {
        val customView: View = layoutInflater.inflate(R.layout.dialog_custom, null)
        val dialog: AlertDialog = AlertDialog.Builder(this).apply {
            setView(customView)
        }.create()

        with(customView) {
            findViewById<ImageView>(R.id.rock).setOnClickListener {
                dialog.dismiss()
                action(0)
            }
            findViewById<ImageView>(R.id.paper).setOnClickListener {
                dialog.dismiss()
                action(1)
            }
            findViewById<ImageView>(R.id.scissors).setOnClickListener {
                dialog.dismiss()
                action(2)
            }
        }
        dialog.show()
    }

    private fun action(chosenItem: Int) {
        textAction.text = ""
        buttonChoose.visibility = Button.INVISIBLE
        textNewAction.text = messages[chosenItem]
        userChoose.setImageResource(images[chosenItem])
        userChoose.visibility = ImageView.VISIBLE
        buttonFight.visibility = Button.VISIBLE
        buttonFight.setOnClickListener {
            val rand = random()
            imageView.setImageResource(images[rand])
            imageView.visibility = ImageView.VISIBLE
            setWinner(rand, chosenItem)
            return@setOnClickListener
        }
    }

    private fun random(): Int {
        randImage.visibility = ImageView.INVISIBLE
        val randomizer = Random()
        return randomizer.nextInt(images.size)
    }

    private fun setWinner(rand: Int, user: Int) {
        textNewAction.text = ""
        if(rand==user) {
            textResult.text = "Ничья!"
            textResult.setTextColor(Color.parseColor("#120B3F"))
        }
        else if((rand==0 && user==1) || (rand==1 && user==2) || (rand==2 && user==0)) {
            textResult.text = "Вы виыграли!"
            textResult.setTextColor(Color.parseColor("#039000"))
        }
        else if((rand==1 && user==0) || (rand==2 && user==1) || (rand==0 && user==2)) {
            textResult.text = "Вы проиграли!"
            textResult.setTextColor(Color.parseColor("#971313"))
        }
        restartGame()
    }

    private fun restartGame() {
        buttonFight.visibility = Button.INVISIBLE
        buttonNewFight.visibility = Button.VISIBLE
        buttonNewFight.setOnClickListener {
            finish()
            startActivity(intent)
        }
    }
}