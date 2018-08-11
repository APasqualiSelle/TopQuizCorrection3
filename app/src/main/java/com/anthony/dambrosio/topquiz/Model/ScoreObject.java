package com.anthony.dambrosio.topquiz.Model;

public class ScoreObject {
    private String mScore;
    private String mName;



    public ScoreObject(String name, String score) {
        mName = name;
        mScore = score;
    }


    public String getScore() {
        return mScore;
    }

    public void setScore(String score) {
        mScore = score;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }
}
