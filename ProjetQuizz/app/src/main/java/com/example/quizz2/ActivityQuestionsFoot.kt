package com.example.quizz2

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Vibrator
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.quizz2.R.id.imageQuestion
import com.example.quizz2.R.id.scoreQuestionsFoot


class ActivityQuestionsFoot : AppCompatActivity() {
    var quizzsFoot = ArrayList<Quizz>()
    var currendQuizIndex = 0
    var number = 0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions_foot)

        quizzsFoot.add(Quizz("Quel est ce club de foot", "OM", "PSG", "ASM", "TFC",
                R.drawable.psg,
                2))
        quizzsFoot.add(Quizz("Quel est ce club de foot", "OM", "PSG", "ASM", "TFC",
                R.drawable.om,
                1))
        quizzsFoot.add(Quizz("Quel est ce club de foot", "OM", "PSG", "ASM", "TFC",
                R.drawable.monaco,
                3))
        quizzsFoot.add(Quizz("Quel est ce club de foot", "OM", "PSG", "ASM", "TFC",
                R.drawable.toulouse,
                4))
        quizzsFoot.add(Quizz("Qui a gagné le ballon d'or en 2012", "Iniesta", "Ronaldo", "Messi", "Neymar",
                R.drawable.ballonor,
                3))
        quizzsFoot.add(Quizz("Quel pays a gagné le plus de coupe du monde", "Brésil", "Allemagne", "Argentine", "France",
                R.drawable.coupedumonde,
                1))
        quizzsFoot.add(Quizz("Au total, combien on couté les transfert de Neymar et Mbappe au psg", "302M", "402M", "505M", "350M",
                R.drawable.neymarmbappe,
                2))
        quizzsFoot.add(Quizz("Quel club a gagné le plus de ligue 1 dans son histoire", "Saint-Etienne", "OL", "PSG", "Nantes",
                R.drawable.ligue1,
                1))
        quizzsFoot.add(Quizz("Qui est cette légende Brésilienne", "Carlos", "Ronaldinho", "Ronaldo ", "Pelé",
                R.drawable.pele,
                4))


        quizzsFoot.shuffle() //mélange les questions

        val btnQuestSvt = findViewById<Button>(R.id.buttonQuestionSuivante)
        btnQuestSvt.setOnClickListener {
            onClickQuestions()
        }

        showQuestion(quizzsFoot.get(currendQuizIndex))

        var scoreQuestion = findViewById<TextView>(R.id.scoreQuestionsFoot)
        scoreQuestion.text = number.toString()
    }

    fun showQuestion(quizz: Quizz) {

        var QuestionFoot1 = findViewById<TextView>(R.id.QuestionFoot1)
        var answer1 = findViewById<TextView>(R.id.ReponseFoot1)
        var answer2 = findViewById<TextView>(R.id.ReponseFoot2)
        var answer3 = findViewById<TextView>(R.id.ReponseFoot3)
        var answer4 = findViewById<TextView>(R.id.ReponseFoot4)
        var img = findViewById<ImageView>(imageQuestion)



        QuestionFoot1.text = quizz.Question
        answer1.text = quizz.Answer1
        answer2.text = quizz.Answer2
        answer3.text = quizz.Answer3
        answer4.text = quizz.Answer4
        img.setImageResource(quizz.image)

    }


    fun handleAnswer(answerID: Int) {
        val quiz = quizzsFoot.get(currendQuizIndex)
        var scoreQuestion = findViewById<TextView>(R.id.scoreQuestionsFoot)

        var answer1 = findViewById<TextView>(R.id.ReponseFoot1)
        var answer2 = findViewById<TextView>(R.id.ReponseFoot2)
        var answer3 = findViewById<TextView>(R.id.ReponseFoot3)
        var answer4 = findViewById<TextView>(R.id.ReponseFoot4)
        var img = findViewById<ImageView>(imageQuestion)

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
        var answer1 = findViewById<TextView>(R.id.ReponseFoot1)
        var answer2 = findViewById<TextView>(R.id.ReponseFoot2)
        var answer3 = findViewById<TextView>(R.id.ReponseFoot3)
        var answer4 = findViewById<TextView>(R.id.ReponseFoot4)

        currendQuizIndex++

        if (currendQuizIndex >= quizzsFoot.size) {
            val intent = Intent(this, ActivityPhotoPartageFoot::class.java)
            intent.putExtra("nombreFinalPointFoot", number)

            startActivity(intent)
        } else {
            answer1.setBackgroundColor(Color.parseColor("#FFFFFF"))
            answer2.setBackgroundColor(Color.parseColor("#FFFFFF"))
            answer3.setBackgroundColor(Color.parseColor("#FFFFFF"))
            answer4.setBackgroundColor(Color.parseColor("#FFFFFF"))

            showQuestion(quizzsFoot.get(currendQuizIndex))

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