package com.example.whrabbit.bioscoop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.whrabbit.bioscoop.API.ApiConnector;
import com.example.whrabbit.bioscoop.API.KeywordAdapter;
import com.example.whrabbit.bioscoop.API.KeywordConnectorListener;
import com.example.whrabbit.bioscoop.API.KeywordsApiConnector;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

public class FilterActivity extends AppCompatActivity implements KeywordConnectorListener {

    KeywordAdapter possible_adapter;
    KeywordAdapter selected_adapter;
    ListView possibleList, selectedList;
    ArrayList<String> possible_keywords;
    ArrayList<String> selected_keywords;
    ArrayList<Integer> possible_keyword_ids;
    ArrayList<Integer> selected_keyword_ids;
    Button searchBtn, filterBtn;
    EditText searchText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        possible_keywords = new ArrayList<>();
        selected_keywords = new ArrayList<>();
        possible_keyword_ids = new ArrayList<>();
        selected_keyword_ids = new ArrayList<>();

        possibleList = (ListView) findViewById(R.id.possibleList);
        selectedList = (ListView) findViewById(R.id.selectedList);
        searchBtn = (Button) findViewById(R.id.discoverSearchBtnID);
        searchText = (EditText) findViewById(R.id.keywordSearchID);

        filterBtn = (Button) findViewById(R.id.filterButtonID);
        filterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent data = new Intent();
                data.putExtra("KEYWORD_IDS", selected_keyword_ids);
                setResult(RESULT_OK, data);
                finish();
            }
        });

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getKeywords(searchText.getText().toString());
            }
        });

        possible_adapter = new KeywordAdapter(getApplicationContext(), possible_keywords);
        possibleList.setAdapter(possible_adapter);

        possibleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Boolean keywordAlreadyInList;
                keywordAlreadyInList = false;

                for (String keyword : selected_keywords) {
                    if (keyword.equals(possible_keywords.get(i))){
                        keywordAlreadyInList = true;
                    }
                }

                if (!keywordAlreadyInList) {

                    selected_keywords.add(possible_keywords.get(i));
                    selected_keyword_ids.add(possible_keyword_ids.get(i));
                    selected_adapter.notifyDataSetChanged();

                }

                possible_keywords.remove(i);
                possible_keyword_ids.remove(i);
                possible_adapter.notifyDataSetChanged();
            }
        });

        selected_adapter = new KeywordAdapter(getApplicationContext(), selected_keywords);
        selectedList.setAdapter(selected_adapter);

        selectedList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selected_keywords.remove(i);
                selected_keyword_ids.remove(i);
                selected_adapter.notifyDataSetChanged();
            }
        });
    }

    private void getKeywords(String search) {
        String url;

        possible_keywords.clear();
        possible_keyword_ids.clear();
        possible_adapter.notifyDataSetChanged();

        if (!search.equals("")) {
            KeywordsApiConnector connector = new KeywordsApiConnector(this);

            try {
                String encSearch = URLEncoder.encode(search, "UTF-8");

                url = "https://api.themoviedb.org/3/search/keyword?api_key=863618e1d5c5f5cc4e34a37c49b8338e&query=" + encSearch + "&page=1";

                String[] urls = new String[]{url};
                connector.execute(urls);

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onKeywordAvailable(String keyword, int keyword_id) {
        if (keyword.equals(null)) {
            possible_keywords.clear();
            possible_keyword_ids.clear();
            possible_adapter.notifyDataSetChanged();
        } else {
            possible_keywords.add(keyword);
            possible_keyword_ids.add(keyword_id);
            possible_adapter.notifyDataSetChanged();
        }
    }
}
