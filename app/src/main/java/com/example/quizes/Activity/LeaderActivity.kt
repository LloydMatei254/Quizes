package com.example.quizes.Activity

import android.content.Intent
import android.os.Bundle
import android.view.Window
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.quizes.Adapter.LeaderAdapter
import com.example.quizes.Domain.UserModel
import com.example.quizes.R
import com.example.quizes.databinding.ActivityLeaderBinding

class LeaderActivity : AppCompatActivity() {

    lateinit var binding: ActivityLeaderBinding
    private val leaderAdapter by lazy { LeaderAdapter()}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLeaderBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        val window: Window = this@LeaderActivity.window
        window.statusBarColor = ContextCompat.getColor(this@LeaderActivity, R.color.grey)

        binding.apply {
            scoreTop1Txt.text=loadData()[0].score.toString()
            scoreTop2Txt.text=loadData()[1].score.toString()
            scoreTop3Txt.text=loadData()[2].score.toString()
            titleTop1Txt.text=loadData()[0].name
            titleTop2Txt.text=loadData()[1].name
            titleTop3Txt.text=loadData()[2].name

            val drawableResourceId1:Int=binding.root.resources.getIdentifier(loadData().get(0).pic,
                "drawable",binding.root.context.packageName)

            Glide.with(binding.root.context)
                .load(drawableResourceId1)
                .into(firstPlaceImage)

            val drawableResourceId2:Int=binding.root.resources.getIdentifier(loadData().get(1).pic,
                "drawable",binding.root.context.packageName)

            Glide.with(binding.root.context)
                .load(drawableResourceId2)
                .into(secondPlaceImage)

            val drawableResourceId3:Int=binding.root.resources.getIdentifier(loadData().get(2).pic,
                "drawable",binding.root.context.packageName)

            Glide.with(binding.root.context)
                .load(drawableResourceId3)
                .into(thirdPlaceImage)

            // Bottom menu setup
            leaderMenu.setItemSelected(R.id.board)
            leaderMenu.setOnItemSelectedListener {
                when (it) {
                    R.id.home -> {
                        startActivity(Intent(this@LeaderActivity, MainActivity::class.java))
                        finish()
                    }
                    R.id.favorites -> {
                        // Handle favorites navigation
                    }
                    R.id.profile -> {
                        // Handle profile navigation
                    }
                }
            }

            var list:MutableList<UserModel> = loadData()
            list.removeAt(0)
            list.removeAt(1)
            list.removeAt(2)

            leaderAdapter.differ.submitList(list)
            leaderView.apply {
                layoutManager= LinearLayoutManager(this@LeaderActivity)
                adapter=leaderAdapter
            }


        }


    }

    private fun loadData(): MutableList<UserModel> {
        val users = mutableListOf<UserModel>()
        users.add(UserModel(1,"Lloyd","person1",2000))
        users.add(UserModel(2,"John","person2",1500))
        users.add(UserModel(3,"Mark","person3",1000))
        users.add(UserModel(4,"Peter","person4",500))
        users.add(UserModel(5,"James","person5",250))
        users.add(UserModel(6,"Jack","person6",100))
        users.add(UserModel(7,"Harry","person7",50))
        users.add(UserModel(8,"Jacob","person8",25))
        users.add(UserModel(9,"Mason","person9",10))
        users.add(UserModel(10,"Ethan","person10",5))
        return users
    }
}