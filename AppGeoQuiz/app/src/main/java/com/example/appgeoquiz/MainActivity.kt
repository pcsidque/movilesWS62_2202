package com.example.appgeoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    lateinit var questions: ArrayList<Question>
    var pos = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadQuestions()
        setupViews()
    }

    private fun loadQuestions() {
        questions = ArrayList()
        var question = Question("¿Es Lima capital de Perú?", true)
        questions.add(question)

        questions.add(Question("¿Es Lima capital de Chile?", false))
        questions.add(Question("¿Es La Paz capital de Chile?", false))
        questions.add(Question("¿Es La Paz capital de Bolivia?", true))
        questions.add(Question("¿Es Brasil capital de Venezuela?", false))
        questions.add(Question("¿Es Quito capital de Ecuador?", true))
        questions.add(Question("¿Es Quito capital de Lima?", false))
    }

    private fun setupViews() {
        val tvQuestion = findViewById<TextView>(R.id.tvQuestion)
        val btYes = findViewById<Button>(R.id.btYes)
        val btNo = findViewById<Button>(R.id.btNo)
        val btNext = findViewById<Button>(R.id.btNext)

        tvQuestion.text = questions[pos].sentence

        btYes.setOnClickListener {
            if (questions[pos].answer){
                Toast.makeText(this, "Correcto", Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(this, "Incorrecto", Toast.LENGTH_LONG).show()
            }
        }

        btNo.setOnClickListener {
            if (questions[pos].answer){
                Toast.makeText(this, "Incorrecto", Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(this, "Correcto", Toast.LENGTH_LONG).show()
            }
        }

        btNext.setOnClickListener {
            pos++
            tvQuestion.text = questions[pos].sentence
        }
    }
}