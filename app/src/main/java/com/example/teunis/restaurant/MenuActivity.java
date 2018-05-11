package com.example.teunis.restaurant;

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

public class MenuActivity extends AppCompatActivity implements MenuRequest.Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // get intent to retrieve clicked category
        Intent intent = getIntent();
        String categoryName = (String) intent.getSerializableExtra("clickedCategory");

        // request menu-items for category
        MenuRequest requestMenu = new MenuRequest(this);
        requestMenu.getItems(this, categoryName);

        // enable clicking on menu's
        ListView listMenu = findViewById(R.id.listMenu);
        listMenu.setOnItemClickListener(new MenuActivity.listItemClickListener());
    }

    @Override
    public void gotMenu(ArrayList<MenuItem> itemsMenu) {

        // adapter to fill list menu's with listitem_menu layout
        MenuAdapter menuAdapter = new MenuAdapter(this, R.layout.listitem_menu, itemsMenu);
        ListView listMenu = findViewById(R.id.listMenu);
        listMenu.setAdapter(menuAdapter);
    }

    @Override
    public void gotMenuError(String message) {

        // display error to user
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }

    public class listItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            // click on menu, put what menu for MenuItemActivity and start
            MenuItem clickedMenu = (MenuItem) parent.getItemAtPosition(position);
            Intent intent = new Intent(MenuActivity.this, MenuItemActivity.class);
            intent.putExtra("clickedMenu", clickedMenu);
            startActivity(intent);
        }
    }
}
