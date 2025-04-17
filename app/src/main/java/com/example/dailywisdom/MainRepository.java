package com.example.dailywisdom;

import android.content.Context;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;

import kotlin.text.Charsets;

public class MainRepository {
    Context context;
    MainRepository(Context ctx){
        context = ctx;
    }
    public Quote[] getQuotes() {
        try{
            InputStream inputStream = context.getAssets().open("quotes.json");
            Gson gson = new Gson();
            int stringSize = inputStream.available();
            byte[] buffer = new byte[stringSize];
            inputStream.read(buffer);
            inputStream.close();
            String json = new String(buffer, Charsets.UTF_8);
            return gson.fromJson(json,Quote[].class);
        }catch (IOException e){
            return new Quote[0];
        }
    }
}
