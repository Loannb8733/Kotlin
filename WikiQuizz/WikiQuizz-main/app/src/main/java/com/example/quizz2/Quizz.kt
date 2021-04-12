package com.example.quizz2

class Quizz(var Question : String, var Answer1 : String, var Answer2 : String,
            var Answer3 : String, var Answer4 : String, var correctAnswer: Int) {

    fun CorrectAnswer(answerNumber: Int): Boolean {
        if (answerNumber == correctAnswer) {
            return true
        }
        return false
    }
}