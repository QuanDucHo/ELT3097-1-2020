package com.nhom68.quizmath.model;

public class User {

    private int score;

    public User() {

        this.score = 0;
    }
    public void increaseScore() {
        score++;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
