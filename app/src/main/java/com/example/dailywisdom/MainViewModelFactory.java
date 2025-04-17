package com.example.dailywisdom;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class MainViewModelFactory implements ViewModelProvider.Factory {

    Context context;

    MainViewModelFactory(Context ctx){
        context = ctx;
    }


    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == MainViewModel.class) {
            MainRepository repository = new MainRepository(context);
            MainViewModel mainViewModel = new MainViewModel(repository);
            return (T) mainViewModel;
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
