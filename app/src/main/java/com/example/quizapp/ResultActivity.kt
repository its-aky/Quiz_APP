package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val tv_Name:TextView =findViewById(R.id.tv_name)
        val tv_Score:TextView=findViewById(R.id.tv_score)
        val btn_Finish: Button =findViewById(R.id.btn_finish)

        tv_Name.text=intent.getStringExtra(Constants.USER_NAME)

        val totalQuestion=intent.getIntExtra(Constants.TOTAL_QUESTIONS,0)
        val correctAnswers=intent.getIntExtra(Constants.CORRECT_ANSWER,0)

        tv_Score.text="You Scored ${correctAnswers} out of ${totalQuestion}"

        btn_Finish.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }

    }
}