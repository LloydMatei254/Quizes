package com.example.quizes.Activity

import android.content.Intent
import android.os.Bundle
import android.view.Window
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.quizes.Domain.QuestionModel
import com.example.quizes.R
import com.example.quizes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val window: Window = this@MainActivity.window
        window.statusBarColor = ContextCompat.getColor(this@MainActivity, R.color.grey)

        binding.apply {
            menu.setItemSelected(R.id.home)
            menu.setOnItemSelectedListener {
                when (it) {
                    R.id.board -> {
                    startActivity(Intent(this@MainActivity, LeaderActivity::class.java))
                    }
                    R.id.favorites -> {
                        // Handle favorites navigation
                    }
                    R.id.profile -> {
                        // Handle profile navigation
                    }
                }
            }

            singleBtn.setOnClickListener {
                val intent = Intent(this@MainActivity, QuestionActivity::class.java)
                intent.putParcelableArrayListExtra("list", ArrayList(questionList()))
                startActivity(intent)
            }
        }
    }

    private fun questionList(): MutableList<QuestionModel> {
        val questions: MutableList<QuestionModel> = mutableListOf()
        questions.add(
            QuestionModel(
                id = 1,
                question = "What is the capital of India?",
                answer_1 = "Delhi",
                answer_2 = "Mumbai",
                answer_3 = "Kolkata",
                answer_4 = "Chennai",
                correctAnswer = "Delhi",
                picPath = "q_1",
                score = 5,
                clickedAnswer = null
            )
        )
        questions.add(
            QuestionModel(
                id = 2,
                question = "What is the largest planet in the solar system?",
                answer_1 = "Earth",
                answer_2 = "Mars",
                answer_3 = "Jupiter",
                answer_4 = "Saturn",
                correctAnswer = "Jupiter",
                picPath = "q_2",
                score = 5,
                clickedAnswer = null
            )
        )
        questions.add(
            QuestionModel(
                id = 3,
                question = "What is the capital city of France?",
                answer_1 = "Paris",
                answer_2 = "London",
                answer_3 = "Berlin",
                answer_4 = "Madrid",
                correctAnswer = "Paris",
                picPath = "q_3",
                score = 5,
                clickedAnswer = null
            )
        )
        questions.add(
            QuestionModel(
                id = 4,
                question = "What is the largest country in the world?",
                answer_1 = "Russia",
                answer_2 = "China",
                answer_3 = "India",
                answer_4 = "USA",
                correctAnswer = "Russia",
                picPath = "q_4",
                score = 5,
                clickedAnswer = null
            )
        )
        questions.add(
            QuestionModel(
                id = 5,
                question = "What is the capital city of Australia?",
                answer_1 = "Sydney",
                answer_2 = "Melbourne",
                answer_3 = "Canberra",
                answer_4 = "Brisbane",
                correctAnswer = "Canberra",
                picPath = "q_5",
                score = 5,
                clickedAnswer = null
            )
        )
        questions.add(
            QuestionModel(
                id = 6,
                question = "What is the largest ocean in the world?",
                answer_1 = "Atlantic Ocean",
                answer_2 = "Indian Ocean",
                answer_3 = "Arctic Ocean",
                answer_4 = "Pacific Ocean",
                correctAnswer = "Pacific Ocean",
                picPath = "q_6",
                score = 5,
                clickedAnswer = null
            )
        )
        questions.add(
            QuestionModel(
                id = 7,
                question = "What is the capital city of Canada?",
                answer_1 = "Ottawa",
                answer_2 = "Toronto",
                answer_3 = "Montreal",
                answer_4 = "Vancouver",
                correctAnswer = "Ottawa",
                picPath = "q_7",
                score = 5,
                clickedAnswer = null
            )
        )
        questions.add(
            QuestionModel(
                id = 8,
                question = "Which of these is a gas giant?",
                answer_1 = "Mercury",
                answer_2 = "Venus",
                answer_3 = "Neptune",
                answer_4 = "Earth",
                correctAnswer = "Neptune",
                picPath = "q_8",
                score = 5,
                clickedAnswer = null
            )
        )
        questions.add(
            QuestionModel(
                id = 9,
                question = "Which city is the capital of Japan?",
                answer_1 = "Tokyo",
                answer_2 = "Osaka",
                answer_3 = "Kyoto",
                answer_4 = "Nagoya",
                correctAnswer = "Tokyo",
                picPath = "q_9",
                score = 5,
                clickedAnswer = null
            )
        )
        questions.add(
            QuestionModel(
                id = 10,
                question = "Which country hosted the 2016 Summer Olympics?",
                answer_1 = "Brazil",
                answer_2 = "China",
                answer_3 = "UK",
                answer_4 = "Greece",
                correctAnswer = "Brazil",
                picPath = "q_10",
                score = 5,
                clickedAnswer = null
            )
        )
        return questions
    }
}