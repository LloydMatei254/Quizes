package com.example.quizes.Activity

import android.content.Intent
import android.os.Bundle
import android.view.Window
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.quizes.Adapter.QuestionAdapter
import com.example.quizes.Adapter.Score
import com.example.quizes.Domain.QuestionModel
import com.example.quizes.R
import com.example.quizes.databinding.ActivityQuestionBinding

class QuestionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuestionBinding
    private var position = 0
    private var receivedList: MutableList<QuestionModel> = mutableListOf()
    private var allScore = 0

    private val questionAdapter = QuestionAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()

        val window: Window = this@QuestionActivity.window
        window.statusBarColor = ContextCompat.getColor(this@QuestionActivity, R.color.grey)

        @Suppress("DEPRECATION")
        receivedList = intent.getParcelableArrayListExtra<QuestionModel>("list")!!.toMutableList()

        setupUi()
        loadQuestion()
    }

    private fun loadQuestion() {
        val questionModel = receivedList[position]

        // Update question number
        binding.questionNumberTxt.text = "Question ${position + 1}/${receivedList.size}"
        
        // Set question text
        binding.questionTxt.text = questionModel.question

        // Load image
        val drawableId: Int = resources.getIdentifier(questionModel.picPath, "drawable", packageName)
        Glide.with(this@QuestionActivity)
            .load(drawableId)
            .centerCrop()
            .apply(RequestOptions.bitmapTransform(RoundedCorners(60)))
            .into(binding.pic)

        // Load answers
        loadAnswers(questionModel)
    }

    private fun loadAnswers(questionModel: QuestionModel) {
        // Create answers list
        val answers = mutableListOf<String>()
        answers.add(questionModel.answer_1.orEmpty())
        answers.add(questionModel.answer_2.orEmpty())
        answers.add(questionModel.answer_3.orEmpty())
        answers.add(questionModel.answer_4.orEmpty())
        
        // Set correct answer
        questionAdapter.setCorrectAnswer(mapCorrectAnswerToLetter(questionModel))
        
        // Submit new list to adapter
        questionAdapter.differ.submitList(answers)
    }

    private fun setupUi() {
        binding.backBtn.setOnClickListener { finish() }
        
        // Set up progress bar
        binding.progressBar.max = receivedList.size
        binding.progressBar.progress = position + 1

        // Set up RecyclerView
        binding.questionList.layoutManager = LinearLayoutManager(this@QuestionActivity)
        binding.questionList.adapter = questionAdapter

        // Set up navigation
        binding.rightArrow.setOnClickListener {
            if (position >= receivedList.lastIndex) {
                val intent = Intent(this@QuestionActivity, ScoreActivity::class.java)
                intent.putExtra("score", allScore)
                startActivity(intent)
                finish()
                return@setOnClickListener
            }
            position++
            binding.progressBar.progress = position + 1
            loadQuestion()
        }

        binding.leftArrow.setOnClickListener {
            if (position <= 0) return@setOnClickListener
            position--
            binding.progressBar.progress = position + 1
            loadQuestion()
        }

        // Set up score callback
        questionAdapter.setScoreCallback(object : Score {
            override fun amount(number: Int, clickedAnswer: String) {
                allScore += number
                receivedList[position].clickedAnswer = clickedAnswer
            }
        })
    }

    private fun mapCorrectAnswerToLetter(model: QuestionModel): String {
        return when (model.correctAnswer) {
            model.answer_1 -> "a"
            model.answer_2 -> "b"
            model.answer_3 -> "c"
            model.answer_4 -> "d"
            else -> ""
        }
    }
}