package com.example.whrabbit.bioscoop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class FilmList extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner filterType, filter, order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_list);

        filterType = (Spinner) findViewById(R.id.filterType);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.filterType,android.R.layout.simple_spinner_item);
        filterType.setAdapter(adapter);
        filterType.setOnItemSelectedListener(this);

        filter = (Spinner) findViewById(R.id.filter);
        ArrayAdapter a = ArrayAdapter.createFromResource(this, R.array.filter,android.R.layout.simple_spinner_item);
        filter.setAdapter(a);
        filter.setOnItemSelectedListener(this);

        order = (Spinner) findViewById(R.id.order);
        ArrayAdapter o = ArrayAdapter.createFromResource(this, R.array.order,android.R.layout.simple_spinner_item);
        order.setAdapter(o);
        order.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        TextView tekst = (TextView) view;
        Toast.makeText(this, "You selected " + tekst.getText(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
