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
    Spinner filterSpin, kiesSpin, orderSpin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_list);

        filterSpin = (Spinner) findViewById(R.id.filterTypeSpinner);
        ArrayAdapter a = ArrayAdapter.createFromResource(this, R.array.filterTypeSpinnerStrings,android.R.layout.simple_spinner_item);
        filterSpin.setAdapter(a);
        filterSpin.setOnItemSelectedListener(this);

        kiesSpin = (Spinner) findViewById(R.id.filterSpinner);
        ArrayAdapter b = ArrayAdapter.createFromResource(this, R.array.filterSpinnerStrings,android.R.layout.simple_spinner_item);
        kiesSpin.setAdapter(b);
        kiesSpin.setOnItemSelectedListener(this);

        orderSpin = (Spinner) findViewById(R.id.sortBySpinner);
        ArrayAdapter c = ArrayAdapter.createFromResource(this, R.array.sortBySpinnerStrings,android.R.layout.simple_spinner_item);
        orderSpin.setAdapter(c);
        orderSpin.setOnItemSelectedListener(this);
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
