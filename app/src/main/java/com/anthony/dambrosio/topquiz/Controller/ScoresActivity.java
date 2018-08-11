package com.anthony.dambrosio.topquiz.Controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.anthony.dambrosio.topquiz.Model.ScoreObject;
import com.anthony.dambrosio.topquiz.Model.ScoreUtils;
import com.anthony.dambrosio.topquiz.R;
import com.anthony.dambrosio.topquiz.View.ScoreListViewAdapter;

import java.util.Collections;
import java.util.Comparator;

import static com.anthony.dambrosio.topquiz.Model.ScoreUtils.mScoreObject;

public class ScoresActivity extends AppCompatActivity {
    private Button mScoresByNameButton;
    private Button mScoresByScoresButton;
    private ListView mScoreListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);

        mScoresByNameButton = (Button) findViewById(R.id.activity_scores_sort_by_name_button_view);
        mScoresByScoresButton = (Button) findViewById(R.id.activity_scores_sort_by_scores_button_view);
        mScoreListView = (ListView) findViewById(R.id.activity_scores_score_list_view);

        ScoreUtils.loadScore(this);
        final ScoreListViewAdapter listViewAdapter = new ScoreListViewAdapter(this, mScoreObject);
        mScoreListView.setAdapter(listViewAdapter);

        mScoresByScoresButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(mScoreObject, sortByScore);
                listViewAdapter.notifyDataSetChanged();
            }
        });

        mScoresByNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(mScoreObject, sortByName);
                listViewAdapter.notifyDataSetChanged();
            }
        });
    }

    public static Comparator<ScoreObject> sortByName = new Comparator<ScoreObject>() {
        @Override
        public int compare(ScoreObject o1, ScoreObject o2) {
            return o1.getName().compareTo(o2.getName());
        }
    };

    public static Comparator<ScoreObject> sortByScore = new Comparator<ScoreObject>() {
        @Override
        public int compare(ScoreObject o1, ScoreObject o2) {
            return o2.getScore().compareTo(o1.getScore());
        }
    };
}
