package com.example.dailywisdom;

import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    Quote [] quotesList;
    int size;
    int index = 0;

    MainViewModel(MainRepository repository){
        quotesList = repository.getQuotes();
        size = quotesList.length;
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
