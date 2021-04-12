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

class ActivityQuestionsFlag : AppCompatActivity() {

    var quizzsFlags = ArrayList<Quizz>()
    var currendQuizIndex = 0
    var number = 0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions_flag)

        quizzsFlags.add(Quizz("A qui appartient ce drapeau", "Barbade", "Rwanda", "Nicaragua", "Moldavie",
                R.drawable.barbade,
                1))
        quizzsFlags.add(Quizz("A qui appartient ce drapeau", "Grenade", "Burundi", "Dominique", "France",
                R.drawable.burundi,
                2))
        quizzsFlags.add(Quizz("A qui appartient ce drapeau", "Guinée", "Mozambique", "Comores", "Islande",
                R.drawable.comores,
                3))
        quizzsFlags.add(Quizz("A qui appartient ce drapeau", "Dominicaine", "Suriname", "Grèce", "Dominique",
                R.drawable.dominique,
                4))
        quizzsFlags.add(Quizz("A qui appartient ce drapeau", "Samoa", "Uruguay", "Tonga", "Taiwan",
                R.drawable.tonga,
                3))
        quizzsFlags.add(Quizz("A qui appartient ce drapeau", "Brésil", "Allemagne", "Argentine", "France",
                R.drawable.allemagne,
                2))
        quizzsFlags.add(Quizz("A qui appartient ce drapeau", "Kiribati", "Tibet", "Bermude", "Laos",
                R.drawable.kiribati,
                1))
        quizzsFlags.add(Quizz("A qui appartient ce drapeau", "Sierra Leone", "Lesotho", "Brunei", "EAU",
                R.drawable.brunei,
                3))
        quizzsFlags.add(Quizz("A qui appartient ce drapeau", "Gabon", "Malawi", "Afghanistan ", "Tchétchénie",
                R.drawable.malawi,
                2))


        quizzsFlags.shuffle() //mélange les questions

        val btnQuestSvt = findViewById<Button>(R.id.buttonQuestionSuivante)
        btnQuestSvt.setOnClickListener {
            onClickQuestions()
        }

        showQuestion(quizzsFlags.get(currendQuizIndex))

        var scoreQuestion = findViewById<TextView>(R.id.scoreQuestionsFlag)
        scoreQuestion.text = number.toString()
    }

    fun showQuestion(quizz: Quizz) {

        var QuestionFlag1 = findViewById<TextView>(R.id.QuestionFlag1)
        var answer1 = findViewById<TextView>(R.id.ReponseFlag1)
        var answer2 = findViewById<TextView>(R.id.ReponseFlag2)
        var answer3 = findViewById<TextView>(R.id.ReponseFlag3)
        var answer4 = findViewById<TextView>(R.id.ReponseFlag4)
        var img = findViewById<ImageView>(R.id.imageQuestion)

        QuestionFlag1.text = quizz.Question
        answer1.text = quizz.Answer1
        answer2.text = quizz.Answer2
        answer3.text = quizz.Answer3
        answer4.text = quizz.Answer4
        img.setImageResource(quizz.image)

    }


    fun handleAnswer(answerID: Int) {
        val quiz = quizzsFlags.get(currendQuizIndex)
        var scoreQuestion = findViewById<TextView>(R.id.scoreQuestionsFlag)

        var answer1 = findViewById<TextView>(R.id.ReponseFlag1)
        var answer2 = findViewById<TextView>(R.id.ReponseFlag2)
        var answer3 = findViewById<TextView>(R.id.ReponseFlag3)
        var answer4 = findViewById<TextView>(R.id.ReponseFlag4)
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
        var answer1 = findViewById<TextView>(R.id.ReponseFlag1)
        var answer2 = findViewById<TextView>(R.id.ReponseFlag2)
        var answer3 = findViewById<TextView>(R.id.ReponseFlag3)
        var answer4 = findViewById<TextView>(R.id.ReponseFlag4)

        currendQuizIndex++

        if (currendQuizIndex >= quizzsFlags.size) {
            val intent = Intent(this, ActivityPhotoPartageFlag::class.java)
            intent.putExtra("nombreFinalPointFlag", number)
            startActivity(intent)
        } else {
            answer1.setBackgroundColor(Color.parseColor("#FFFFFF"))
            answer2.setBackgroundColor(Color.parseColor("#FFFFFF"))
            answer3.setBackgroundColor(Color.parseColor("#FFFFFF"))
            answer4.setBackgroundColor(Color.parseColor("#FFFFFF"))

            showQuestion(quizzsFlags.get(currendQuizIndex))

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