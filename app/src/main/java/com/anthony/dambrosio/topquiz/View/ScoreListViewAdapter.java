package com.anthony.dambrosio.topquiz.View;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.anthony.dambrosio.topquiz.Model.ScoreObject;
import com.anthony.dambrosio.topquiz.R;

import java.util.List;

public class ScoreListViewAdapter extends ArrayAdapter<ScoreObject>{

    public ScoreListViewAdapter(Context context, List<ScoreObject> scoreObjectList) {
        super(context, 0, scoreObjectList);
    }

    private class ScoreListViewHolder{
        public TextView name;
        public TextView score;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.score_item, parent, false);
        }

        ScoreListViewHolder scoreListViewHolder = (ScoreListViewHolder) convertView.getTag();
        if (scoreListViewHolder == null) {
            scoreListViewHolder = new ScoreListViewHolder();
            scoreListViewHolder.name = (TextView) convertView.findViewById(R.id.name_item);
            scoreListViewHolder.score = (TextView) convertView.findViewById(R.id.scores_item);
            convertView.setTag(scoreListViewHolder);
        }

        ScoreObject scoreObjectPosition = getItem(position);

        assert scoreObjectPosition != null;
        scoreListViewHolder.name.setText(scoreObjectPosition.getName());
        scoreListViewHolder.score.setText(scoreObjectPosition.getScore());

        return convertView;
    }
}
