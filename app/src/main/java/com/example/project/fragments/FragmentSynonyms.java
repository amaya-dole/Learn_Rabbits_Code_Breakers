package com.example.project.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.project.R;
import com.example.project.WordMeaningActivity;

public class FragmentSynonyms extends Fragment {
    public FragmentSynonyms() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_definition,container, false);//Inflate Layout

        Context context=getActivity();
        TextView text = (TextView) view.findViewById(R.id.textViewD);//Find textView Id

        String synonyms= ((WordMeaningActivity)context).synonyms;

        if(synonyms!=null)
        {
            synonyms = synonyms.replaceAll(",", ",\n");
            text.setText(synonyms);
        }
        if(synonyms==null)
        {
            text.setText("No synonyms found");
        }


        return view;
    }
}


