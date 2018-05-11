package com.example.teunis.restaurant;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MenuAdapter extends ArrayAdapter<MenuItem> {
    public MenuAdapter(@NonNull Context context, int resource, @NonNull ArrayList<MenuItem> objects) {
        super(context, resource, objects);
        items = objects;
    }

    ArrayList<MenuItem> items;

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listitem_menu, parent, false);
        }

        // what item is clicked?
        MenuItem item = items.get(position);

        // retrieve views to be filled
        ImageView imageMenu = convertView.findViewById(R.id.imageMenu);
        TextView titleMenu = convertView.findViewById(R.id.titleMenu);
        TextView priceMenu = convertView.findViewById(R.id.priceMenu);

        // fill views with item info, use Picasso to load item image
        titleMenu.setText(item.name);
        String stringPrice = "€" + Double.toString(item.price);
        priceMenu.setText(stringPrice);
        Picasso.get().load(item.imageUrl).into(imageMenu);

        return convertView;
    }
}
