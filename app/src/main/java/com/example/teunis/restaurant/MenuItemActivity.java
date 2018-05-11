package com.example.teunis.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MenuItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_item);

        // retrieve what menu is clicked
        Intent intent = getIntent();
        MenuItem menuItemName = (MenuItem) intent.getSerializableExtra("clickedMenu");

        // retrieve views to be filled
        TextView nameMenuItem = findViewById(R.id.nameMenuItem);
        TextView descriptionMenuItem = findViewById(R.id.descriptionMenuItem);
        TextView priceMenuItem = findViewById(R.id.priceMenuItem);
        ImageView imageMenuItem = findViewById(R.id.imageMenuItem);

        // fill views with menuItem info (use Picasso for loading image)
        nameMenuItem.setText(menuItemName.name);
        descriptionMenuItem.setText(menuItemName.description);
        String stringPrice = "€" + Double.toString(menuItemName.price);
        priceMenuItem.setText(stringPrice);
        Picasso.get().load(menuItemName.imageUrl).into(imageMenuItem);
    }
}
