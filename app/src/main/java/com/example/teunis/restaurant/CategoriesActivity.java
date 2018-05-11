package com.example.teunis.restaurant;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class CategoriesActivity extends AppCompatActivity implements CategoriesRequest.Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        // request categories
        CategoriesRequest request = new CategoriesRequest(this);
        request.getCategories(this);

        // enable clicking on categories
        ListView listItems = findViewById(R.id.listItems);
        listItems.setOnItemClickListener(new listItemClickListener());
        }

    @Override
    public void gotCategories(ArrayList<String> categories) {

        // adapter to fill list categories with listitem_categories layout
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.listitem_categories, R.id.textView, categories);
        ListView listItems = findViewById(R.id.listItems);
        listItems.setAdapter(arrayAdapter);

    }

    @Override
    public void gotCategoriesError(String message) {

        // show error to user
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }

    public class listItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            // click on category, put what category for MenuActivity and start
            Object clickedCategory = parent.getItemAtPosition(position);
            String stringClickedCategory = clickedCategory.toString();
            Intent intent = new Intent(CategoriesActivity.this, MenuActivity.class);
            intent.putExtra("clickedCategory", stringClickedCategory);
            startActivity(intent);
        }
    }
}
