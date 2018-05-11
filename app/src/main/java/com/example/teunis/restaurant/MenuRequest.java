package com.example.teunis.restaurant;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;

public class MenuRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    Context context;
    Callback activity;

    public MenuRequest (Context context) {
        this.context = context;
    }

    // methods later to be implemented
    public interface Callback {
        void gotMenu(ArrayList<MenuItem> itemsMenu);
        void gotMenuError(String message);
    }

    @Override
    public void onErrorResponse(VolleyError error) {

        // give error to activity (for displaying)
        String thisError = error.getMessage();
        activity.gotMenuError(thisError);
    }

    @Override
    public void onResponse(JSONObject response) {

        // Arraylist with MenuItem's to be filled
        ArrayList<MenuItem> items = new ArrayList<>();

        // try filling
        try {

            // get JSONArray with all items
            JSONArray itemsArray = response.getJSONArray("items");

            // for all items: retrieve JSONObject with name, description, etc.
            for (int i = 0; i < itemsArray.length(); i++) {
                JSONObject thisItem = itemsArray.getJSONObject(i);

                // make MenuItem with info and add to Arraylist for retrieving later
                String name = thisItem.getString("name");
                String description = thisItem.getString("description");
                String imageUrl = thisItem.getString("image_url");
                String category = thisItem.getString("category");
                Double price = thisItem.getDouble("price");
                MenuItem foodItem = new MenuItem(name, description, imageUrl, category, price);
                items.add(foodItem);
            }

        // catch JSONException
        } catch (JSONException e){
        }

        // finished? Start gotMenu()
        activity.gotMenu(items);
    }

    public void getItems(Callback activity, String thisCategory) {

        // request JSON for certain category (retrieved from intent)
        this.activity = activity;
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://resto.mprog.nl/menu?category=" + thisCategory;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null , this , this);
        queue.add(jsonObjectRequest);
    }
}
