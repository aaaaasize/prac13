package com.example.testasize

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var questionTextView: TextView
    private lateinit var radioGroup: RadioGroup
    private lateinit var option1Button: Button
    private lateinit var option2Button: Button
    private lateinit var option3Button: Button
    private lateinit var option4Button: Button
    private lateinit var submitButton: Button

    private var questions = ArrayList<Question>()
    private var currentQuestionIndex = 0
    private var correctAnswers = 0
    private var incorrectAnswers = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        questionTextView = findViewById(R.id.question_text_view)
        radioGroup = findViewById(R.id.radio_group)
        option1Button = findViewById(R.id.option1_button)
        option2Button = findViewById(R.id.option2_button)
        option3Button = findViewById(R.id.option3_button)
        option4Button = findViewById(R.id.option4_button)
        submitButton = findViewById(R.id.submit_button)

        questions.add(
            Question(
                "1. В какой стране зародилось Versace?",
                "Англия",
                "Италия",
                "Испания",
                "Франция",
                "Италия")
        )
        questions.add(
            Question(
                "2. В каком году впервые была неделя моды?",
                "1943",
                "1920",
                "1867",
                "1960",
                "1943")
        )
        questions.add(
            Question(
                "3. В каком городе была первая неделя моды?",
                "Москва",
                "Милан",
                "Нью-Йорк",
                "Франция",
                "Нью-Йорк")
        )
        questions.add(
            Question(
                "4. Какой по мнению List является самый популярный бренд в мире?",
                "Gucci",
                "Balenciaga",
                "LV",
                "Tommy Hilfiger",
                "Balenciaga")
        )
        questions.add(
            Question(
                "5. С помощью какого тренда, Balenciaga первая фурор раньше всего?",
                "Тройная подошва",
                "Номер на носке",
                "Обувь-носки",
                "Поношенная одежда",
                "Тройная подошва")
        )
        questions.add(
            Question(
                "6. Самая популярная песня у Lana del Rey?",
                "Video Games",
                "National Anthem",
                "Young and Beautiful",
                "Summertime Sadness",
                "Young and Beautiful")
        )
        questions.add(
            Question(
                "7. На каком году жизни умер Цой",
                "Цой жив!!!",
                "31",
                "21",
                "28",
                "28")
        )
        questions.add(
            Question(
                "8. Кто первый начал играть рок?",
                "Фетс Домино",
                "Чак Берри",
                "Бо Диддли",
                "Стен Джемс",
                "Чак Берри")
        )
        questions.add(
            Question(
                "9. Какая у меня последняя добавленная песня в вк",
                "Give it to me",
                "Кажется",
                "Empire State Of Mind",
                "Мечта",
                "Empire State Of Mind")
        )
        questions.add(
            Question(
                "10. Какой по мнению Spotify сейчас самый популярный жанр?",
                "Рок",
                "Поп",
                "Реп",
                "Хип-хоп",
                "Хип-хоп")
        )
        questions.add(
            Question(
                "11. В каком году появилась первая компания кроссовок?",
                "1920",
                "1890",
                "1892",
                "1902",
                "1892")
        )
        questions.add(
            Question(
                "12. Первый бренд кроссовок?",
                "Keds",
                "Crocs",
                "Adidas",
                "Nike",
                "Keds")
        )
        questions.add(
            Question(
                "13. Первая модель Nike?",
                "Air max",
                "Cortez",
                "Air force",
                "Streetball",
                "Cortez")
        )
        questions.add(
            Question(
                "14. Какой бренд обуви самый богатый?",
                "Under Armour",
                "Carhartt",
                "Nike",
                "LV",
                "LV")
        )
        questions.add(
            Question(
                "15. Самая дорогая пара в данный момент?",
                "NIKE AIR YEEZY 1 КАНЬЕ УЭСТА",
                "NIKE AIR SHIP 1984 ГОДА",
                "AIR JORDAN 1 «SHATTERED BACKBOARD» С ОСКОЛКОМ СТЕКЛА",
                "AIR JORDAN 1 «CHICAGO» С ПОДПИСЬЮ МАЙКЛА ДЖОРДАНА",
                "NIKE AIR YEEZY 1 КАНЬЕ УЭСТА")
        )
        showQuestion()
        submitButton.setOnClickListener {
            checkAnswer()
        }
    }

    private fun showQuestion() {
        val currentQuestion = questions[currentQuestionIndex]
        questionTextView.text = currentQuestion.question
        option1Button.text = currentQuestion.option1
        option2Button.text = currentQuestion.option2
        option3Button.text = currentQuestion.option3
        option4Button.text = currentQuestion.option4
        radioGroup.clearCheck()
    }

    private fun checkAnswer() {
        val selectedOption = radioGroup.checkedRadioButtonId
        if (selectedOption == -1) {
            return
        }
        val selectedRadioButton = findViewById<RadioButton>(selectedOption)
        val answer = selectedRadioButton.text.toString()
        val currentQuestion = questions[currentQuestionIndex]
        if (answer == currentQuestion.answer) {
            correctAnswers++
        } else {
            incorrectAnswers++
        }
        currentQuestionIndex++
        if (currentQuestionIndex == questions.size) {
            showResult()
        } else {
            showQuestion()
        }
    }
    private fun getRating(correctPercent: Double): Int {
        return when {
            correctPercent < 50 -> 2
            correctPercent < 75 -> 3
            correctPercent < 85 -> 4
            else -> 5
        }
    }
    private fun showResult() {
        val correctPercent = correctAnswers * 100.0 / questions.size
        val incorrectPercent = incorrectAnswers * 100.0 / questions.size
        val resultText = "Верно отвечено на : $correctPercent%\nНеверно: $incorrectPercent%\nВаша оценка ${getRating(correctPercent)}"
        val rating = getRating(correctPercent)
        questionTextView.text = resultText
        option1Button.text = ""
        option2Button.text = ""
        option3Button.text = ""
        option4Button.text = ""
        submitButton.isEnabled = false
    }


}
class Question(val question: String, val option1: String, val option2: String,val option3: String, val option4: String, val answer: String)

