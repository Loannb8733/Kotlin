package com.example.quizz2

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Vibrator
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class ActivityQuestionsLogos : AppCompatActivity() {
    var quizzsLogos = ArrayList<Quizz>()
    var currendQuizIndex = 0
    var number = 0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions_logos)

        quizzsLogos.add(Quizz("Quel est ce logo ?", "Pringles", "KFC", "Burger King", "Pizza Hut",
            R.drawable.kfc,
            2))
        quizzsLogos.add(Quizz("Quel est ce logo ?", "Mitsubishi", "Toyota", "Opel", "Seat",
            R.drawable.mitsubishi,
            1))
        quizzsLogos.add(Quizz("Quel est ce logo ?", "Matmu", "Matmut", "Total", "Areva",
            R.drawable.total,
            3))
        quizzsLogos.add(Quizz("Quel est ce logo ?", "Adidas", "Polo Ralph" + "\n" + "Lauren", "Puma", "Nike",
            R.drawable.nike,
            4))
        quizzsLogos.add(Quizz("Quel est ce logo ?", "Skoda", "Gamm Vert", "Nuxe", "Crédit" +"\n"+ "Agricole",
            R.drawable.nuxe,
            3))
        quizzsLogos.add(Quizz("Quel est ce logo ?", "NBC", "Canal +", "CNN", "France TV",
            R.drawable.nbc,
            1))
        quizzsLogos.add(Quizz("Quel est ce logo ?", "Dolce Gabbana", "Kappa", "New Balance", "DC Shoes",
            R.drawable.kappa,
            2))
        quizzsLogos.add(Quizz("Quel est ce logo ?", "Chanel", "Cachanel", "Camaïeu", "Dior",
            R.drawable.chanel,
            1))
        quizzsLogos.add(Quizz("Quel est ce logo ?", "Chérie FM", "Nrj", "Fun Radio ", "Skyrock",
            R.drawable.skyrock,
            4))


        quizzsLogos.shuffle() //mélange les questions

        val btnQuestSvt = findViewById<Button>(R.id.buttonQuestionSuivante)
        btnQuestSvt.setOnClickListener {
            onClickQuestions()
        }

        showQuestion(quizzsLogos.get(currendQuizIndex))

        var scoreQuestion = findViewById<TextView>(R.id.scoreQuestionsLogos)
        scoreQuestion.text = number.toString()
    }

    fun showQuestion(quizz: Quizz) {

        var QuestionLogos1 = findViewById<TextView>(R.id.QuestionLogos1)
        var answer1 = findViewById<TextView>(R.id.ReponseLogos1)
        var answer2 = findViewById<TextView>(R.id.ReponseLogos2)
        var answer3 = findViewById<TextView>(R.id.ReponseLogos3)
        var answer4 = findViewById<TextView>(R.id.ReponseLogos4)
        var img = findViewById<ImageView>(R.id.imageQuestion)



        QuestionLogos1.text = quizz.Question
        answer1.text = quizz.Answer1
        answer2.text = quizz.Answer2
        answer3.text = quizz.Answer3
        answer4.text = quizz.Answer4
        img.setImageResource(quizz.image)

    }


    fun handleAnswer(answerID: Int) {
        val quiz = quizzsLogos.get(currendQuizIndex)
        var scoreQuestion = findViewById<TextView>(R.id.scoreQuestionsLogos)

        var answer1 = findViewById<TextView>(R.id.ReponseLogos1)
        var answer2 = findViewById<TextView>(R.id.ReponseLogos2)
        var answer3 = findViewById<TextView>(R.id.ReponseLogos3)
        var answer4 = findViewById<TextView>(R.id.ReponseLogos4)
        var img = findViewById<ImageView>(R.id.imageQuestion)

        answer1.isEnabled = false
        answer2.isEnabled = false
        answer3.isEnabled = false
        answer4.isEnabled = false

        if (quiz.CorrectAnswer(answerID)) {

            number += 100

            scoreQuestion.text = number.toString()

            img.setImageResource(R.drawable.juste)


            when {
                quiz.CorrectAnswer(1) -> {

                    answer1.setBackgroundColor(Color.parseColor("#008000"))
                    answer2.setBackgroundColor(Color.parseColor("#FFFFFF"))
                    answer3.setBackgroundColor(Color.parseColor("#FFFFFF"))
                    answer4.setBackgroundColor(Color.parseColor("#FFFFFF"))

                }
                quiz.CorrectAnswer(2) -> {

                    answer1.setBackgroundColor(Color.parseColor("#FFFFFF"))
                    answer2.setBackgroundColor(Color.parseColor("#008000"))
                    answer3.setBackgroundColor(Color.parseColor("#FFFFFF"))
                    answer4.setBackgroundColor(Color.parseColor("#FFFFFF"))

                }
                quiz.CorrectAnswer(3) -> {

                    answer1.setBackgroundColor(Color.parseColor("#FFFFFF"))
                    answer2.setBackgroundColor(Color.parseColor("#FFFFFF"))
                    answer3.setBackgroundColor(Color.parseColor("#008000"))
                    answer4.setBackgroundColor(Color.parseColor("#FFFFFF"))

                }
                quiz.CorrectAnswer(4) -> {

                    answer1.setBackgroundColor(Color.parseColor("#FFFFFF"))
                    answer2.setBackgroundColor(Color.parseColor("#FFFFFF"))
                    answer3.setBackgroundColor(Color.parseColor("#FFFFFF"))
                    answer4.setBackgroundColor(Color.parseColor("#008000"))

                }
            }

            val vibrator = getSystemService(VIBRATOR_SERVICE) as Vibrator
            vibrator.vibrate(500)

            Toast.makeText(this, "+100", Toast.LENGTH_SHORT).show()
        } else {
            number += 0
            scoreQuestion.text = number.toString()
            img.setImageResource(R.drawable.faux)

            if (quiz.CorrectAnswer(1)) {
                answer1.setBackgroundColor(Color.parseColor("#008000"))

                if (!quiz.CorrectAnswer(2)) {
                    answer2.setBackgroundColor(Color.parseColor("#FF0000"))
                    answer3.setBackgroundColor(Color.parseColor("#FF0000"))
                    answer4.setBackgroundColor(Color.parseColor("#FF0000"))

                } else if (!quiz.CorrectAnswer(3)) {
                    answer2.setBackgroundColor(Color.parseColor("#FF0000"))
                    answer3.setBackgroundColor(Color.parseColor("#FF0000"))
                    answer4.setBackgroundColor(Color.parseColor("#FF0000"))
                } else if (!quiz.CorrectAnswer(4)) {
                    answer2.setBackgroundColor(Color.parseColor("#FF0000"))
                    answer3.setBackgroundColor(Color.parseColor("#FF0000"))
                    answer4.setBackgroundColor(Color.parseColor("#FF0000"))
                }
            } else if (quiz.CorrectAnswer(2)) {
                answer2.setBackgroundColor(Color.parseColor("#008000"))

                if (!quiz.CorrectAnswer(1)) {
                    answer1.setBackgroundColor(Color.parseColor("#FF0000"))
                    answer3.setBackgroundColor(Color.parseColor("#FF0000"))
                    answer4.setBackgroundColor(Color.parseColor("#FF0000"))
                } else if (!quiz.CorrectAnswer(3)) {
                    answer1.setBackgroundColor(Color.parseColor("#FF0000"))
                    answer3.setBackgroundColor(Color.parseColor("#FF0000"))
                    answer4.setBackgroundColor(Color.parseColor("#FF0000"))
                } else if (!quiz.CorrectAnswer(4)) {
                    answer1.setBackgroundColor(Color.parseColor("#FF0000"))
                    answer3.setBackgroundColor(Color.parseColor("#FF0000"))
                    answer4.setBackgroundColor(Color.parseColor("#FF0000"))
                }

            } else if (quiz.CorrectAnswer(3)) {
                answer3.setBackgroundColor(Color.parseColor("#008000"))

                if (!quiz.CorrectAnswer(1)) {
                    answer1.setBackgroundColor(Color.parseColor("#FF0000"))
                    answer2.setBackgroundColor(Color.parseColor("#FF0000"))
                    answer4.setBackgroundColor(Color.parseColor("#FF0000"))
                } else if (!quiz.CorrectAnswer(2)) {
                    answer1.setBackgroundColor(Color.parseColor("#FF0000"))
                    answer2.setBackgroundColor(Color.parseColor("#FF0000"))
                    answer4.setBackgroundColor(Color.parseColor("#FF0000"))
                } else if (!quiz.CorrectAnswer(4)) {
                    answer1.setBackgroundColor(Color.parseColor("#FF0000"))
                    answer2.setBackgroundColor(Color.parseColor("#FF0000"))
                    answer4.setBackgroundColor(Color.parseColor("#FF0000"))
                }

            } else if(quiz.CorrectAnswer(4)) {
                answer4.setBackgroundColor(Color.parseColor("#008000"))

                if (!quiz.CorrectAnswer(1)) {
                    answer1.setBackgroundColor(Color.parseColor("#FF0000"))
                    answer2.setBackgroundColor(Color.parseColor("#FF0000"))
                    answer3.setBackgroundColor(Color.parseColor("#FF0000"))
                } else if (!quiz.CorrectAnswer(2)) {
                    answer1.setBackgroundColor(Color.parseColor("#FF0000"))
                    answer2.setBackgroundColor(Color.parseColor("#FF0000"))
                    answer3.setBackgroundColor(Color.parseColor("#FF0000"))
                } else if (!quiz.CorrectAnswer(3)) {
                    answer1.setBackgroundColor(Color.parseColor("#FF0000"))
                    answer2.setBackgroundColor(Color.parseColor("#FF0000"))
                    answer3.setBackgroundColor(Color.parseColor("#FF0000"))
                }
            }

            Toast.makeText(this, "+0", Toast.LENGTH_SHORT).show()
        }


    }

    fun onClickQuestions() {
        var answer1 = findViewById<TextView>(R.id.ReponseLogos1)
        var answer2 = findViewById<TextView>(R.id.ReponseLogos2)
        var answer3 = findViewById<TextView>(R.id.ReponseLogos3)
        var answer4 = findViewById<TextView>(R.id.ReponseLogos4)

        currendQuizIndex++

        if (currendQuizIndex >= quizzsLogos.size) {
            val intent = Intent(this, ActivityPhotoPartageLogos::class.java)
            intent.putExtra("nombreFinalPointLogos", number)
            startActivity(intent)
            number = 0
        } else {
            answer1.setBackgroundColor(Color.parseColor("#FFFFFF"))
            answer2.setBackgroundColor(Color.parseColor("#FFFFFF"))
            answer3.setBackgroundColor(Color.parseColor("#FFFFFF"))
            answer4.setBackgroundColor(Color.parseColor("#FFFFFF"))

            showQuestion(quizzsLogos.get(currendQuizIndex))

            answer1.isEnabled = true
            answer2.isEnabled = true
            answer3.isEnabled = true
            answer4.isEnabled = true
        }
    }

    fun onClickAnswerOne(view: View) {
        handleAnswer(1)
    }

    fun onClickAnswerTwo(view: View) {
        handleAnswer(2)
    }

    fun onClickAnswerThree(view: View) {
        handleAnswer(3)
    }

    fun onClickAnswerFour(view: View) {
        handleAnswer(4)
    }
}