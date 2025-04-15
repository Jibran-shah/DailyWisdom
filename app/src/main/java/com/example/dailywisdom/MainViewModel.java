package com.example.dailywisdom;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;

import kotlin.text.Charsets;

public class MainViewModel extends ViewModel {
    Quote [] quotesList = new Quote[0];
    int size;
    int index = 0;
    Context context;

    MainViewModel(Context ctx){
        context = ctx;
        getQuotes();
    }

    private void getQuotes() {
        try{
            InputStream inputStream = context.getAssets().open("quotes.json");
            Gson gson = new Gson();
            int stringSize = inputStream.available();
            byte[] buffer = new byte[stringSize];
            inputStream.read(buffer);
            inputStream.close();
            String json = new String(buffer, Charsets.UTF_8);
            quotesList = gson.fromJson(json,Quote[].class);
            size = quotesList.length;
        }catch (IOException e){

        }
    }

    Quote getQuote(){
        return quotesList[index];
    }

    Quote nextQuote(){
        ++index;
        if(index == size){
            index = 0;
        }
        return quotesList[index];
    }

    Quote previousQuote(){
        --index;
        if(index < 0){
            index = size-1;
        }
        return quotesList[index];
    }


}
