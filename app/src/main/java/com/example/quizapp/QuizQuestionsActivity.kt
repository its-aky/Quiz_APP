package com.example.quizapp

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {
    //the below three variables are useful when any option textview is clicked
    //Note their uses throughout the code
    private var mCurrentPosition:Int=1
    private var mQuestionsList:ArrayList<Question>?=null
    private var mSelectedOptionPosition:Int=0

    private var mUserName:String?=null
    private var mCorrectAnswers:Int=0

    //creating global variables of each variable whose value is
    //to be accessed or changed
    private var progressBar:ProgressBar?= null
    private var tvProgress:TextView?=null
    private var tvQuestion:TextView?=null
    private var ivImage: ImageView?=null

    private var tvOptionOne:TextView?=null
    private var tvOptionTwo:TextView?=null
    private var tvOptionThree:TextView?=null
    private var tvOptionFour:TextView?=null

    private var btnSubmit:Button?=null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        mUserName=intent.getStringExtra(Constants.USER_NAME)
        //assigning the variables with values individually using findViewById

        progressBar=findViewById(R.id.progressBar)
        tvProgress=findViewById(R.id.tv_progress)
        tvQuestion=findViewById(R.id.tv_question)
        ivImage=findViewById(R.id.iv_image)

        tvOptionOne=findViewById(R.id.tv_option_one)
        tvOptionTwo=findViewById(R.id.tv_option_two)
        tvOptionThree=findViewById(R.id.tv_option_three)
        tvOptionFour=findViewById(R.id.tv_option_four)
        btnSubmit=findViewById(R.id.btn_submit)
        mQuestionsList = Constants.getQuestions()

        //The log is performed

//        //To get number of questions in questionsList in logcat
//        val questionsList=Constants.getQuestions()
//        Log.i("QuestionsList size is","${questionsList.size}")
//
//        //To get each question from questionList in logcat
//        for(i in questionsList){
//            Log.e("Questions",i.question)
//        }



        setQuestion()

        tvOptionOne?.setOnClickListener(this)//class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener
        //this is passed as a parameter to the class QuizQuestionsActivity
        tvOptionTwo?.setOnClickListener(this)
        tvOptionThree?.setOnClickListener(this)
        tvOptionFour?.setOnClickListener(this)

        btnSubmit?.setOnClickListener(this)


    }

    @SuppressLint("SetTextI18n")
    private fun setQuestion() {
        val question: Question = mQuestionsList!![mCurrentPosition - 1]
        defaultOptionsView()

        if(mCurrentPosition==mQuestionsList!!.size){
            btnSubmit?.text="FINISH"
        }
        else{
            btnSubmit?.text="SUBMIT"
        }

        ivImage?.setImageResource(question.image)
        progressBar?.progress = mCurrentPosition
        tvProgress?.text = "$mCurrentPosition"+"/"+ progressBar?.max
        tvQuestion?.text = question.question
        tvOptionOne?.text = question.optionOne
        tvOptionOne?.text = question.optionTwo
        tvOptionOne?.text = question.optionThree
        tvOptionOne?.text = question.optionFour



    }

    private fun defaultOptionsView(){//calling this function would
        //set the default properties to the option textviews

        val options=ArrayList<TextView>()

        //since tvOptions are nullabe so let will be used
        tvOptionOne?.let {
            options.add(0,it)
        }
        tvOptionTwo?.let {
            options.add(1,it)
        }
        tvOptionThree?.let {
            options.add(2,it)
        }
        tvOptionFour?.let {
            options.add(3,it)
        }

        for(option in options){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface= Typeface.DEFAULT
            option.background=ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg
            )
        }
    }

    private fun selectedOptionView(tv:TextView,selectedOptionNum:Int){
        //this function will change the properties of a selected
        //option textview
        defaultOptionsView()//To set all to default

        mSelectedOptionPosition=selectedOptionNum
        tv.setTextColor(Color.parseColor("363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)

        tv.background=ContextCompat.getDrawable(
            this,
            R.drawable.selected_option_border_bg
        )
    }


    

    override fun onClick(view: View) {
        when(view?.id){//in when clause -> operator is used to define lambda expression
            R.id.tv_option_one -> {
                tvOptionOne?.let {
                    selectedOptionView(it, 1)
                }
            }

            R.id.tv_option_two -> {
                tvOptionTwo?.let {
                    selectedOptionView(it, 2)
                }
            }

            R.id.tv_option_three -> {
                tvOptionThree?.let {
                    selectedOptionView(it, 3)
                }
            }

            R.id.tv_option_four -> {
                tvOptionFour?.let {
                    selectedOptionView(it, 4)
                }
            }

            R.id.btn_submit->{
                if(mSelectedOptionPosition==0){
                    mCurrentPosition++
                    when{
                        mCurrentPosition<=mQuestionsList!!.size->{
                            setQuestion()
                        }
                        else ->{
                            //Toast.makeText(this,"End of Quiz",Toast.LENGTH_LONG).show()
                            val intent=Intent(this,ResultActivity::class.java)
                            intent.putExtra(Constants.USER_NAME,mUserName)
                            intent.putExtra(Constants.CORRECT_ANSWER,mCorrectAnswers)
                            intent.putExtra(Constants.TOTAL_QUESTIONS,mQuestionsList?.size)
                            startActivity(intent)
                            finish()

                        }
                    }
                }
                else{
                    val question=mQuestionsList?.get(mCurrentPosition-1)
                    if(question!!.correctAnswer!=mSelectedOptionPosition){
                        answerView(mSelectedOptionPosition,R.drawable.wrong_option_border_bg)
                    }
                    else{
                        mCorrectAnswers++
                    }
                    answerView(question.correctAnswer,R.drawable.correct_option_border_bg)

                    if(mCurrentPosition==mQuestionsList!!.size){
                        btnSubmit?.text="FINISH"
                    }
                    else{
                        btnSubmit?.text="NEXT QUESTION"
                    }
                }

                mSelectedOptionPosition=0


            }
        }
    }

    private fun answerView(answer:Int,drawableView:Int){
        when(answer){
            1 -> {
                tvOptionOne?.background=ContextCompat.getDrawable(
                    this@QuizQuestionsActivity,
                    drawableView
                )
            }

            2 -> {
                tvOptionTwo?.background=ContextCompat.getDrawable(
                    this@QuizQuestionsActivity,
                    drawableView
                )
            }
            3 -> {
                tvOptionThree?.background=ContextCompat.getDrawable(
                    this@QuizQuestionsActivity,
                    drawableView
                )
            }

            4 -> {
                tvOptionFour?.background=ContextCompat.getDrawable(
                    this@QuizQuestionsActivity,
                    drawableView
                )
            }

        }
    }


}