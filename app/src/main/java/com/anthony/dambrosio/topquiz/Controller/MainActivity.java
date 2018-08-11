package com.anthony.dambrosio.topquiz.Controller;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.anthony.dambrosio.topquiz.Model.ScoreObject;
import com.anthony.dambrosio.topquiz.Model.ScoreUtils;
import com.anthony.dambrosio.topquiz.Model.User;
import com.anthony.dambrosio.topquiz.R;

import static com.anthony.dambrosio.topquiz.Model.ScoreUtils.mScoreObject;
import static com.anthony.dambrosio.topquiz.Model.ScoreUtils.saveScore;

public class MainActivity extends AppCompatActivity {
    private TextView mWelcomeTextView;
    private EditText mNameEditText;
    private Button mPlayButton;
    private Button mScoresButton;
    private User mUserFirstName;
    private static final int GAME_ACTIVITY_REQUEST_CODE = 42;
    private static final int SCORE_ACTIVITY_ID = 02;
    private SharedPreferences mSharedPreferences;

    public static final String PREF_KEY_SCORE = "PREF_KEY_SCORE";
    public static final String PREF_KEY_FIRSTNAME = "PREF_KEY_FIRSTNAME";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWelcomeTextView = (TextView) findViewById(R.id.activity_main_welcome_text_view);
        mNameEditText = (EditText) findViewById(R.id.activity_main_name_edit_text_view);
        mPlayButton = (Button) findViewById(R.id.activity_main_play_button_view);
        mScoresButton = (Button) findViewById(R.id.activity_main_scores_button_view);
        mUserFirstName = new User();

        ScoreUtils.loadScore(this);

        mSharedPreferences = getPreferences(MODE_PRIVATE);

        mUserFirstName.setFirstName(mNameEditText.getText().toString());

        mNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                mPlayButton.setEnabled(charSequence.toString().length() != 0);

                mPlayButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String firstname = mNameEditText.getText().toString();
                        mUserFirstName.setFirstName(firstname);

                        mSharedPreferences.edit().putString(PREF_KEY_FIRSTNAME, mUserFirstName.getFirstName()).apply();

                        Intent gameActivity = new Intent(MainActivity.this, GameActivity.class);
                        startActivityForResult(gameActivity, GAME_ACTIVITY_REQUEST_CODE);
                    }
                });
            }

            @Override
            public void afterTextChanged(Editable s) {
                mPlayButton.setEnabled(true);
            }
        });

        if (mScoreObject.size() == 0)
            mScoresButton.setVisibility(View.GONE);

        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = mNameEditText.getText().toString();
                mUserFirstName.setFirstName(firstName);
                mSharedPreferences.edit().putString(PREF_KEY_FIRSTNAME, mUserFirstName.getFirstName()).apply();
                Context context = MainActivity.this;
                Class gameActivityClass = GameActivity.class;
                Intent gameActivityIntent = new Intent(context, gameActivityClass);
                startActivityForResult(gameActivityIntent, GAME_ACTIVITY_REQUEST_CODE);
            }
        });

        mScoresButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = MainActivity.this;
                Class scoresActivityClass = ScoresActivity.class;
                Intent scoresActivityIntent = new Intent(context, scoresActivityClass);
                startActivityForResult(scoresActivityIntent, SCORE_ACTIVITY_ID);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (GAME_ACTIVITY_REQUEST_CODE == requestCode && RESULT_OK == resultCode){
            int score = data.getIntExtra(GameActivity.BUNDLE_EXTRA_SCORE, 0);
            mSharedPreferences.edit().putInt(PREF_KEY_SCORE, score).apply();
            mScoresButton.setVisibility(View.VISIBLE);
            greetUser();
        }
    }

    private void greetUser(){
        String firstName = mSharedPreferences.getString(PREF_KEY_FIRSTNAME, null);
        if (firstName != null){
            int score = mSharedPreferences.getInt(PREF_KEY_SCORE, 0);
            String text = "Welcome back, " + firstName
                    + "!\nYour last score was " + score
                    + ", will you do better this time?";
            scoreHandler(firstName, score);
            mWelcomeTextView.setText(text);
            mNameEditText.setText(firstName);
            mNameEditText.setSelection(firstName.length());
            mPlayButton.setEnabled(true);
        }
    }

    public void scoreHandler(String name, int score) {
        if (mScoreObject.size() == 5)
            mScoreObject.remove(0);
        mScoreObject.add(new ScoreObject(name, String.valueOf(score)));
        saveScore(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        System.out.println("MainActivity::onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();

        System.out.println("MainActivity::onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();

        System.out.println("MainActivity::onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();

        System.out.println("MainActivity::onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        System.out.println("MainActivity::onDestroy()");
    }
}
