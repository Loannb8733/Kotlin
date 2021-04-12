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

class ActivityQuestionsKitchen : AppCompatActivity() {
    var quizzsKitchen = ArrayList<Quizz>()
    var currendQuizIndex = 0
    var number = 0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions_kitchen)

        quizzsKitchen.add(Quizz("De quelle région est originaire le fromage le brillat-savarin ?", "Champagne-"+ "\n" +"Ardenne", "Haute-"+ "\n" +"Normandie", "Rhône-Alpes", "Poitou-"+ "\n" +"Charentes",
            R.drawable.brilletsavarin,
            2))
        quizzsKitchen.add(Quizz("Quelle province française a pour spécialité le poulet ?", "La Bresse", "Le Béarn", "L'Anjou", "Le Berry",
            R.drawable.poulet,
            1))
        quizzsKitchen.add(Quizz("À quel chef le film « Ratatouille » rend-il hommage ?", "Jamie Oliver", "Michel Guérard", "Bernard Loiseau", "Georges Blanc",
            R.drawable.ratatouille,
            3))
        quizzsKitchen.add(Quizz("Quel légume n'entre pas dans la composition de la traditionnelle salade niçoise ?", "Tomate", "Oignon rouge", "Poivron", "Haricot vert",
            R.drawable.saladenicoise,
            4))
        quizzsKitchen.add(Quizz("Quel poisson n'entre pas dans la recette traditionnelle de la bouillabaisse ?", "La rascasse", "Le congre", "Le cabillaud", "La baudroie",
            R.drawable.bouillabaisse,
            3))
        quizzsKitchen.add(Quizz("Avec le lait de quel animal produit-on le fromage le morbier ?", "Vache", "Chèvre", "Brebis", "Mouflonne",
            R.drawable.morbier,
            1))
        quizzsKitchen.add(Quizz("Dans quelle région viticole produit-on le vin Beaumes-de-Venise ?", "Le Jura", "La Vallée"+ "\n" +"du Rhône", "Le Bordelais", "La Bourgogne",
            R.drawable.vin,
            2))
        quizzsKitchen.add(Quizz("Quel grand chef a fait obtenir 3 étoiles Michelin au restaurant Louis XV à Monaco ?", "P.Bocuse", "J-F.Piège", "A.Ducasse", "B.Loiseau",
            R.drawable.resto,
            1))
        quizzsKitchen.add(Quizz("Quel condiment est essentiel à la piperade ?", "Sauge", "Moutarde", "Poivre ", "Piment",
            R.drawable.piperade,
            4))


        quizzsKitchen.shuffle() //mélange les questions

        val btnQuestSvt = findViewById<Button>(R.id.buttonQuestionSuivante)
        btnQuestSvt.setOnClickListener {
            onClickQuestions()
        }

        showQuestion(quizzsKitchen.get(currendQuizIndex))

        var scoreQuestion = findViewById<TextView>(R.id.scoreQuestionsKitchen)
        scoreQuestion.text = number.toString()
    }

    fun showQuestion(quizz: Quizz) {

        var QuestionKitchen1 = findViewById<TextView>(R.id.QuestionKitchen1)
        var answer1 = findViewById<TextView>(R.id.ReponseKitchen1)
        var answer2 = findViewById<TextView>(R.id.ReponseKitchen2)
        var answer3 = findViewById<TextView>(R.id.ReponseKitchen3)
        var answer4 = findViewById<TextView>(R.id.ReponseKitchen4)
        var img = findViewById<ImageView>(R.id.imageQuestion)



        QuestionKitchen1.text = quizz.Question
        answer1.text = quizz.Answer1
        answer2.text = quizz.Answer2
        answer3.text = quizz.Answer3
        answer4.text = quizz.Answer4
        img.setImageResource(quizz.image)

    }


    fun handleAnswer(answerID: Int) {
        val quiz = quizzsKitchen.get(currendQuizIndex)
        var scoreQuestion = findViewById<TextView>(R.id.scoreQuestionsKitchen)

        var answer1 = findViewById<TextView>(R.id.ReponseKitchen1)
        var answer2 = findViewById<TextView>(R.id.ReponseKitchen2)
        var answer3 = findViewById<TextView>(R.id.ReponseKitchen3)
        var answer4 = findViewById<TextView>(R.id.ReponseKitchen4)
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
        var answer1 = findViewById<TextView>(R.id.ReponseKitchen1)
        var answer2 = findViewById<TextView>(R.id.ReponseKitchen2)
        var answer3 = findViewById<TextView>(R.id.ReponseKitchen3)
        var answer4 = findViewById<TextView>(R.id.ReponseKitchen4)

        currendQuizIndex++

        if (currendQuizIndex >= quizzsKitchen.size) {
            val intent = Intent(this, ActivityPhotoPartageKitchen::class.java)
            intent.putExtra("nombreFinalPointKitchen", number)
            startActivity(intent)
            number = 0
        } else {
            answer1.setBackgroundColor(Color.parseColor("#FFFFFF"))
            answer2.setBackgroundColor(Color.parseColor("#FFFFFF"))
            answer3.setBackgroundColor(Color.parseColor("#FFFFFF"))
            answer4.setBackgroundColor(Color.parseColor("#FFFFFF"))

            showQuestion(quizzsKitchen.get(currendQuizIndex))

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