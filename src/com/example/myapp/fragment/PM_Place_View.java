package com.example.myapp.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.myapp.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class PM_Place_View extends Fragment implements View.OnClickListener {
    private Button mBtn;

    public String name;
    private List<String> picUrls;
    private List<String> scores;
    private String intro;

    public void buildData(String place) {
        String[] subString = place.split(";");
        if (subString.length >=3 ) {
            name = subString[0];
            scores = Arrays.asList(subString[1].split(","));
            picUrls = Arrays.asList(subString[2].split(","));
            intro = subString[3];
        }
    }

    @Override
    public void onClick(View v) {
        listener.onItemSelected(name, picUrls, intro);
    }

    public interface OnArticleSelectedListener {
        public void onItemSelected(String name, List<String> picUrls, String intro);
    }

    private static OnArticleSelectedListener listener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (!(activity instanceof OnArticleSelectedListener)) {
            throw new IllegalStateException("Activity must implement fragment's callbacks.");
        } else {
            listener = (OnArticleSelectedListener) activity;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_place_view, container, false);

        TextView name = (TextView) view.findViewById(R.id.place_name);
        name.setText(this.name);

        TextView intro = (TextView) view.findViewById(R.id.place_intro);
        intro.setText(this.intro);

        TextView score = (TextView) view.findViewById(R.id.place_score);
        score.setText(buildScore(this.scores));

        mBtn = (Button)view.findViewById(R.id.place_view_button_detail);
        mBtn.setOnClickListener(this);

        /** * Inflate the layout for this fragment */
        return view;
    }

    private String buildScore(List<String> scores) {
        if (scores.size() >=4) {
            StringBuilder sb = new StringBuilder();
            sb.append("总分: ");
            sb.append(scores.get(0));
            sb.append(" 景色: ");
            sb.append(scores.get(1));
            sb.append(" 趣味： ");
            sb.append(scores.get(2));
            sb.append(" 性价比：");
            sb.append(scores.get(3));
            return sb.toString();
        } else {
            return "unknown Score.";
        }

    }
}
