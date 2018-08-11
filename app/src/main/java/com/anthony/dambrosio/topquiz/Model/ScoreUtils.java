package com.anthony.dambrosio.topquiz.Model;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class ScoreUtils {
    public static final String SCORE_PREFERENCES = "SCORE_PREFERENCES";
    public static final String SCORE_DATA = "SCORE_DATA";

    public static ArrayList<ScoreObject> mScoreObject = new ArrayList<>();

    public ScoreUtils() {
    }

    public static void saveScore(Context activity){
        SharedPreferences sharedPreferences = activity.getSharedPreferences(SCORE_PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String jsonGson = gson.toJson(mScoreObject);
        editor.putString(SCORE_DATA, jsonGson);
        editor.apply();
    }

    public static void loadScore(Context activity){
        SharedPreferences sharedPreferences = activity.getSharedPreferences(SCORE_PREFERENCES, MODE_PRIVATE);
        Gson gson = new Gson();
        String jsonScore = sharedPreferences.getString(SCORE_DATA, null);
        Type type = new TypeToken<ArrayList<ScoreObject>>(){}.getType();
        mScoreObject = gson.fromJson(jsonScore, type);
        if (mScoreObject == null){
            mScoreObject = new ArrayList<>();
        }
    }
}
