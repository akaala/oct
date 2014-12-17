package com.example.myapp.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.example.myapp.R;

import java.util.ArrayList;
import java.util.Map;

public class PM_Place_View extends Fragment implements View.OnClickListener {
    private Button mBtn;

    public String name;
    public String grade;

    ArrayList<String> data = new ArrayList<>();

    public void buildData(Map<String, String> map) {
        for (String name : map.keySet()) {
            data.add(name);
        }
    }

    @Override
    public void onClick(View v) {
        listener.onItemSelected(name, grade);
    }


    public interface OnArticleSelectedListener {
        public void onItemSelected(String name, String grade);
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

//    @Override
//    public void onListItemClick(ListView l, View v, int position, long id) {
//        super.onListItemClick(l, v, position, id);
//        listener.onItemSelected(name, grade);
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setListAdapter(new ArrayAdapter<>(getActivity(),
//                android.R.layout.simple_list_item_activated_1, data));
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_place_view, container, false);

        mBtn = (Button)view.findViewById(R.id.place_view_button_detail);
        mBtn.setOnClickListener(this);

        TextView textView = (TextView) view.findViewById(R.id.textView1);
        textView.setText("Name: " + name + ", Grade: " + grade);

        /** * Inflate the layout for this fragment */
        return view;
    }
}
