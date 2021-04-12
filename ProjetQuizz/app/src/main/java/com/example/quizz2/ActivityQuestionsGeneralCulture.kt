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

class ActivityQuestionsGeneralCulture : AppCompatActivity() {
    var quizzsGeneralCulture = ArrayList<Quizz>()
    var currendQuizIndex = 0
    var number = 0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions_general_culture)

        quizzsGeneralCulture.add(Quizz("Quel célèbre dictateur dirigea l’URSS du milieu des années 1920 à 1953 ?", "Trotski", "Staline", "Molotov", "Lenine",
            R.drawable.urss,
            2))
        quizzsGeneralCulture.add(Quizz("Dans quel pays peut-on trouver l’Andalousie et la Castille ?", "Espagne", "France", "Portugal", "Italie",
            R.drawable.castille,
            1))
        quizzsGeneralCulture.add(Quizz("Qui a dit : « Le sort en est jeté »", "Attila", "Auguste", "César", "Vercingétorix",
            R.drawable.sort,
            3))
        quizzsGeneralCulture.add(Quizz("Quel cinéaste a réalisé « Parle avec elle » et « Volver » ?", "Trapero", "Allen", "del Toro", "Almodovar",
            R.drawable.parleavecelle,
            4))
        quizzsGeneralCulture.add(Quizz("À qui doit-on la chanson « I Shot the Sheriff » ?", "Morrisson", "Clapton", "Bob Marley", "UB40",
            R.drawable.musique,
            3))
        quizzsGeneralCulture.add(Quizz("Dans quelle ville l’action de la pièce « Roméo et Juliette » se déroule-t-elle ?", "Vérone", "Venise", "Milan", "Rome",
            R.drawable.shakespeare,
            1))
        quizzsGeneralCulture.add(Quizz("Par quel mot désigne-t-on une belle-mère cruelle ?", "Godiche", "Marâtre", "Chenapan", "Jocrisse",
            R.drawable.maratre,
            2))
        quizzsGeneralCulture.add(Quizz("Qui était le dieu de la guerre dans la mythologie grecque ?", "Arès", "Apollon", "Hermès", "Hadès",
            R.drawable.ares,
            1))
        quizzsGeneralCulture.add(Quizz("Quel animal peut se déplacer le plus rapidement ?", "Léopart", "Chevreuil", "Mgbobe ", "Springbok",
            R.drawable.springbok,
            4))


        quizzsGeneralCulture.shuffle() //mélange les questions

        val btnQuestSvt = findViewById<Button>(R.id.buttonQuestionSuivante)
        btnQuestSvt.setOnClickListener {
            onClickQuestions()
        }

        showQuestion(quizzsGeneralCulture.get(currendQuizIndex))

        var scoreQuestion = findViewById<TextView>(R.id.scoreQuestionsGeneralCulture)
        scoreQuestion.text = number.toString()
    }

    fun showQuestion(quizz: Quizz) {

        var QuestionGeneralCulture1 = findViewById<TextView>(R.id.QuestionGeneralCulture1)
        var answer1 = findViewById<TextView>(R.id.ReponseGeneralCulture1)
        var answer2 = findViewById<TextView>(R.id.ReponseGeneralCulture2)
        var answer3 = findViewById<TextView>(R.id.ReponseGeneralCulture3)
        var answer4 = findViewById<TextView>(R.id.ReponseGeneralCulture4)
        var img = findViewById<ImageView>(R.id.imageQuestion)



        QuestionGeneralCulture1.text = quizz.Question
        answer1.text = quizz.Answer1
        answer2.text = quizz.Answer2
        answer3.text = quizz.Answer3
        answer4.text = quizz.Answer4
        img.setImageResource(quizz.image)

    }


    fun handleAnswer(answerID: Int) {
        val quiz = quizzsGeneralCulture.get(currendQuizIndex)
        var scoreQuestion = findViewById<TextView>(R.id.scoreQuestionsGeneralCulture)

        var answer1 = findViewById<TextView>(R.id.ReponseGeneralCulture1)
        var answer2 = findViewById<TextView>(R.id.ReponseGeneralCulture2)
        var answer3 = findViewById<TextView>(R.id.ReponseGeneralCulture3)
        var answer4 = findViewById<TextView>(R.id.ReponseGeneralCulture4)
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
        var answer1 = findViewById<TextView>(R.id.ReponseGeneralCulture1)
        var answer2 = findViewById<TextView>(R.id.ReponseGeneralCulture2)
        var answer3 = findViewById<TextView>(R.id.ReponseGeneralCulture3)
        var answer4 = findViewById<TextView>(R.id.ReponseGeneralCulture4)

        currendQuizIndex++

        if (currendQuizIndex >= quizzsGeneralCulture.size) {
            val intent = Intent(this, ActivityPhotoPartageGeneralCulture::class.java)
            intent.putExtra("nombreFinalPointGeneralCulture", number)
            startActivity(intent)
            number = 0
        } else {
            answer1.setBackgroundColor(Color.parseColor("#FFFFFF"))
            answer2.setBackgroundColor(Color.parseColor("#FFFFFF"))
            answer3.setBackgroundColor(Color.parseColor("#FFFFFF"))
            answer4.setBackgroundColor(Color.parseColor("#FFFFFF"))

            showQuestion(quizzsGeneralCulture.get(currendQuizIndex))

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