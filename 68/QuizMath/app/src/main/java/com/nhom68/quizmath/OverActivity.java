package com.nhom68.quizmath;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class OverActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView txtYourScore;
    private Button btnTryAgain;
    private Button btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_over);
        initializeComponent();
        int score = getIntent().getExtras().getInt("Score");
        txtYourScore.setText("YOUR SCORE\n" + String.valueOf(score));
    }

    private void initializeComponent() {
        TextView txtGameOver = (TextView) findViewById(R.id.txtGameOver);
        txtYourScore = (TextView) findViewById(R.id.txtYourScore);
        btnTryAgain = (Button) findViewById(R.id.btnTryAgain);
        btnHome = (Button) findViewById(R.id.btnHome);

        btnTryAgain.setOnClickListener(this);
        btnHome.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnTryAgain:
                clickTryAgain();
                break;

            case R.id.btnHome:
                clickHome();
                break;

            default:
                break;
        }
    }

    private void clickTryAgain() {
        Intent itent = new Intent(this, PlayActivity.class);
        startActivity(itent);
    }

    private void clickHome() {
        Intent itent = new Intent(this, MainActivity.class);
        startActivity(itent);
    }
}
