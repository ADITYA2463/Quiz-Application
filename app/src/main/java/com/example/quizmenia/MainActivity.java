package com.example.quizmenia;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView totalQuestionsTextView;
    TextView questionTextView;
    Button ansA, ansB, ansC, ansD;
    Button submitBtn;

    int score = 0;
    int totalQuestions = QuestionsAnswer.questions.length;
    int currentQuestionIndex = 0;
    String selectedAnswer = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalQuestionsTextView = findViewById(R.id.quiz_questions);
        questionTextView = findViewById(R.id.Question);
        ansA = findViewById(R.id.ans_A);
        ansB = findViewById(R.id.ans_B);
        ansC = findViewById(R.id.ans_C);
        ansD = findViewById(R.id.ans_D);
        submitBtn = findViewById(R.id.submit_btn);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        submitBtn.setOnClickListener(this);

        totalQuestionsTextView.setText("Total questions :" + totalQuestions);

        loadNewQuestion();

    }

    @Override
    public void onClick(View view) {

        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        ansC.setBackgroundColor(Color.WHITE);
        ansD.setBackgroundColor(Color.WHITE);

        Button clickedButton = (Button) view;
        if (clickedButton.getId() == R.id.submit_btn) {

            if(selectedAnswer.equals((QuestionsAnswer.correctAnswers[currentQuestionIndex])))
            {
                score++;
            }

            currentQuestionIndex++;
            loadNewQuestion();


        } else {
            selectedAnswer = clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.MAGENTA);
        }

    }



        void loadNewQuestion ()
        {
            if(currentQuestionIndex == totalQuestions)
            {
                finishQuiz();
                return;
            }
            questionTextView.setText(QuestionsAnswer.questions[currentQuestionIndex]);
            ansA.setText(QuestionsAnswer.choices[currentQuestionIndex][0]);
            ansB.setText(QuestionsAnswer.choices[currentQuestionIndex][1]);
            ansC.setText(QuestionsAnswer.choices[currentQuestionIndex][2]);
            ansD.setText(QuestionsAnswer.choices[currentQuestionIndex][3]);
        }

        void finishQuiz(){
        String passStatus = "";
        if(score > totalQuestions*0.60)
        {
            passStatus = "Passed";
        }else{
            passStatus = "Failed";
        }

        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("score is " + score + " out of " + totalQuestions)
                .setPositiveButton("Restart", (dialog, i) -> restartQuiz() )
                .setCancelable(false)
                .show();
        }

        void restartQuiz(){
        score =0;
        currentQuestionIndex = 0;
        loadNewQuestion();
        }
    }

