package com.nhom68.quizmath;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView txtGameName;
    private TextView txtBestScore;
    private Button btnPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeComponent();
    }

    private void initializeComponent() {
        txtGameName = (TextView) findViewById(R.id.txtGameName);
        txtBestScore = (TextView) findViewById(R.id.txtBestScore);
        btnPlay = (Button) findViewById(R.id.btnPlay);

        btnPlay.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnPlay:
                clickPlay();
                break;
        }
    }

    private void clickPlay() {
        Intent itent = new Intent(this, PlayActivity.class);
        startActivity(itent);
    }

}
