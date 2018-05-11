package com.example.teunis.restaurant;

import android.app.Activity;
import android.content.Context;
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

public class CategoriesRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    Context context;
    Callback activity;

    public CategoriesRequest(Context context) {
        this.context = context;
    }

    // methods later to be implemented
    public interface Callback {
        void gotCategories(ArrayList<String> categories);
        void gotCategoriesError(String message);
    }

    @Override
    public void onErrorResponse(VolleyError error) {

        // give error to activity (for displaying)
        String thisError = error.getMessage();
        activity.gotCategoriesError(thisError);
    }

    @Override
    public void onResponse(JSONObject response) {

        // Arraylist with categories to be filled
        ArrayList<String> categories = new ArrayList<>();

        // try filling list with JSONArray
        try {
            JSONArray categoriesArray = response.getJSONArray("categories");
            for (int i = 0; i < categoriesArray.length(); i++) {
                categories.add(categoriesArray.getString(i));
            }

        // catch JSONException
        } catch (JSONException e){
        }

        // finished? Start gotCategories()
        activity.gotCategories(categories);
    }

    public void getCategories(Callback activity) {

        // new JSONRequest for categories
        this.activity = activity;
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("https://resto.mprog.nl/categories", null , this , this);
        queue.add(jsonObjectRequest);
    }
}
