package com.example.quizes.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.quizes.Domain.UserModel
import com.example.quizes.databinding.ViewholderLeadersBinding

class LeaderAdapter: RecyclerView.Adapter<LeaderAdapter.ViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<UserModel>() {
        override fun areItemsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = ViewholderLeadersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = differ.currentList[position]
        holder.bind(currentItem, position)
    }

    override fun getItemCount() = differ.currentList.size

    inner class ViewHolder(private val binding: ViewholderLeadersBinding) : RecyclerView.ViewHolder(binding.root) {
        
        fun bind(user: UserModel, position: Int) {
            binding.titleTxt.text = user.name

            val drawableResourceId = binding.root.resources.getIdentifier(
                user.pic,
                "drawable",
                binding.root.context.packageName
            )

            Glide.with(binding.root.context)
                .load(drawableResourceId)
                .into(binding.pic)

            binding.rowTxt.text = (position + 4).toString()
            binding.scoreTxt.text = user.score.toString()
        }
    }
}
