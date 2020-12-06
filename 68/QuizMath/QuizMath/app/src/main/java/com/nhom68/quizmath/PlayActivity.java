package com.nhom68.quizmath;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nhom68.quizmath.model.Question;
import com.nhom68.quizmath.model.User;

public class PlayActivity extends AppCompatActivity implements View.OnClickListener {
    public static final int MAX_TIMER = 10;

    private TextView txtScore;
    private TextView txtBest;
    private TextView txtTimer;
    private TextView txtQuestion;
    private ImageView btnTrue;
    private ImageView btnFalse;

    private Handler handler;
    private int mTimer;
    private int mScore;
    private int mLevel;
    private boolean isRunning;
    private Question mQuestion;
    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        initializeComponent();

        handler = new Handler() {
            @Override
            public void handleMessage(Message message) {
                switch (message.what) {
                    case com.nhom68.quizmath.MessageConstant.UPDATE_QUESTION:
                        mTimer = MAX_TIMER;
                        String question = mQuestion.show();

                        txtTimer.setText(String.valueOf(mTimer));
                        txtQuestion.setText(question);
                        break;

                    case com.nhom68.quizmath.MessageConstant.UPDATE_TIMER:
                        mTimer = message.arg1;
                        txtTimer.setText(String.valueOf(mTimer));
                        break;

                    case com.nhom68.quizmath.MessageConstant.QUESTION_TRUE:
                        mScore = mUser.getScore();
                        txtScore.setText("Score: " + String.valueOf(mScore));
                        Toast.makeText(PlayActivity.this, "Dung", Toast.LENGTH_SHORT).show();
                        break;

                    case com.nhom68.quizmath.MessageConstant.QUESTION_FALSE:
                        Toast.makeText(PlayActivity.this, "Sai", Toast.LENGTH_SHORT).show();
                        break;

                    default:
                        break;
                }
            }
        };

        playGame();

    }

    private void initializeComponent() {
        txtScore = (TextView) findViewById(R.id.txtScore);
        txtBest = (TextView) findViewById(R.id.txtBest);
        txtTimer = (TextView) findViewById(R.id.txtTimer);
        txtQuestion = (TextView) findViewById(R.id.txtQuestion);
        btnTrue = (ImageView) findViewById(R.id.btnTrue);
        btnFalse = (ImageView) findViewById(R.id.btnFalse);

        btnTrue.setOnClickListener(this);
        btnFalse.setOnClickListener(this);
    }

    private void playGame() {
        mUser = new User();
        mQuestion = new Question();
        mLevel = Question.EASY;
        mTimer = MAX_TIMER;
        makeQuestion();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (mTimer > -1 && isRunning) {
                    Message message = new Message();
                    message.what = com.nhom68.quizmath.MessageConstant.UPDATE_TIMER;
                    message.arg1 = mTimer;
                    handler.sendMessage(message);

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    mTimer--;

                }

                gameOver();

            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
        isRunning = true;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnTrue:
                checkQuestion(true);
                break;

            case R.id.btnFalse:
                checkQuestion(false);
                break;

            default:
                break;
        }
    }

    private void makeQuestion() {
        checkLevel();
        mQuestion.makeQuestion(mLevel);
        handler.sendEmptyMessage(com.nhom68.quizmath.MessageConstant.UPDATE_QUESTION);
    }

    private void checkQuestion(boolean b) {
        if (mQuestion.checkQuestion(b)) {
            mUser.increaseScore();
            handler.sendEmptyMessage(com.nhom68.quizmath.MessageConstant.QUESTION_TRUE);
            makeQuestion();

        } else {
            isRunning = false;
            handler.sendEmptyMessage(com.nhom68.quizmath.MessageConstant.QUESTION_FALSE);
        }
    }

    private void checkLevel() {
        if (mScore == Question.NORMAL) {
            mLevel = Question.NORMAL;
        }
        if (mScore == Question.HARD){
            mLevel = Question.HARD;
        }
    }

    private void gameOver() {
        Intent intent = new Intent(this, OverActivity.class);
        intent.putExtra("Score", mScore);
        startActivity(intent);
    }

}
