package com.nhom68.quizmath.model;

import java.util.Random;


public class Question {
    public static final int EASY = 0;
    public static final int NORMAL = 10;
    public static final int HARD = 20;
    private int numberOne;

    private int numberTwo;

    private int trueAnswer;

    private int answer;

    public Question() {}

    public int getNumberOne() {
        return numberOne;
    }

    public void makeQuestion(int level) {
        switch (level) {
            case EASY:
                makeQuestion(0, 10);
                break;

            case NORMAL:
                makeQuestion(10, 100);
                break;

            case HARD:
                makeQuestion(100, 1000);
                break;

            default:
                break;
        }
    }

    private void makeQuestion(int start, int end) {
        Random random = new Random();
        int range = end - start;

        numberOne = random.nextInt(range)+ start;
        numberTwo = random.nextInt(range) + start;
        trueAnswer = numberOne + numberTwo;
        if (random.nextBoolean()) {
            answer = trueAnswer;
        }
        else {
            if (random.nextBoolean()) {
                answer = trueAnswer + random.nextInt(10) + 1;
            }
            else {
                answer = trueAnswer + random.nextInt(10) - 10;
            }
        }
    }

    public boolean checkQuestion(boolean b) {
        if (b) {
            if (answer == trueAnswer) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            if (answer == trueAnswer) {
                return false;
            }
            else {
                return true;
            }
        }
    }

    public String show() {
        return numberOne + " + " + numberTwo + " = " + answer;
    }

    public void setNumberOne(int numberOne) {
        this.numberOne = numberOne;
    }

    public int getNumberTwo() {
        return numberTwo;
    }

    public void setNumberTwo(int numberTwo) {
        this.numberTwo = numberTwo;
    }

    public int getTrueAnswer() {
        return trueAnswer;
    }

    public void setTrueAnswer(int trueAnswer) {
        this.trueAnswer = trueAnswer;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "ClassPojo [numberOne = " + numberOne + ", numberTwo = " + numberTwo + ", trueAnswer = " + trueAnswer + ", answer = " + answer + "]";
    }
}