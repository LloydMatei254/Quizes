package com.example.quizes.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.quizes.R
import com.example.quizes.databinding.ViewholderQuestionBinding

class QuestionAdapter : RecyclerView.Adapter<QuestionAdapter.ViewHolder>() {

    private var correctAnswer: String = ""
    private var returnScore: Score? = null
    private var isAnswered = false

    private val differCallback = object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem
        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem
    }

    val differ = AsyncListDiffer(this, differCallback)

    fun setCorrectAnswer(answerLetter: String) {
        correctAnswer = answerLetter
        isAnswered = false
        notifyDataSetChanged()
    }

    fun setScoreCallback(callback: Score) {
        returnScore = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewholderQuestionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position < differ.currentList.size) {
            holder.bind(differ.currentList[position], position)
        }
    }

    override fun getItemCount(): Int = differ.currentList.size

    inner class ViewHolder(private val binding: ViewholderQuestionBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(answerText: String, position: Int) {
            // Set answer text
            binding.answerTxt.text = answerText

            // Reset to default state
            binding.answerTxt.setBackgroundResource(R.color.white)
            binding.answerTxt.setTextColor(ContextCompat.getColor(binding.root.context, R.color.navy_blue))
            binding.answerTxt.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null)
            binding.answerTxt.isClickable = !isAnswered

            // Set click listener on the answer text view
            binding.answerTxt.setOnClickListener {
                if (isAnswered) return@setOnClickListener

                val chosenLetter = when (position) {
                    0 -> "a"
                    1 -> "b"
                    2 -> "c"
                    3 -> "d"
                    else -> ""
                }

                isAnswered = true

                if (chosenLetter == correctAnswer) {
                    binding.answerTxt.setBackgroundResource(R.drawable.green_background)
                    binding.answerTxt.setTextColor(ContextCompat.getColor(binding.root.context, R.color.white))
                    val drawable = ContextCompat.getDrawable(binding.root.context, R.drawable.tick)
                    binding.answerTxt.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null)
                    returnScore?.amount(5, chosenLetter)
                } else {
                    binding.answerTxt.setBackgroundResource(R.drawable.red_background)
                    binding.answerTxt.setTextColor(ContextCompat.getColor(binding.root.context, R.color.white))
                    val drawable = ContextCompat.getDrawable(binding.root.context, R.drawable.thieves)
                    binding.answerTxt.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null)
                    returnScore?.amount(0, chosenLetter)
                }

                // Disable all answer clicks after answering
                notifyDataSetChanged()
            }
        }
    }
}

interface Score {
    fun amount(number: Int, clickedAnswer: String)
}
