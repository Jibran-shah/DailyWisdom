package com.example.dailywisdom;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    TextView quoteTv;
    TextView authorTv;
    ConstraintLayout previous_cl;
    ConstraintLayout next_cl;
    FloatingActionButton shareFB;

    MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        quoteTv = findViewById(R.id.quote_tv);
        authorTv = findViewById(R.id.author_tv);
        previous_cl = findViewById(R.id.previous_cl);
        next_cl = findViewById(R.id.next_cl);

        shareFB = findViewById(R.id.share_fb);

        next_cl.setOnClickListener((view)->setQuote(mainViewModel.nextQuote()));

        previous_cl.setOnClickListener((view)->setQuote(mainViewModel.previousQuote()));

        shareFB.setOnClickListener((view)->shareQuote());

        mainViewModel = new ViewModelProvider
                (
                this, new MainViewModelFactory(getApplicationContext()))
                .get(MainViewModel.class
                );
    }


    @Override
    protected void onResume() {
        super.onResume();
        setQuote(mainViewModel.getQuote());
    }

    void setQuote(Quote quote){
        quoteTv.setText(quote.text);
        authorTv.setText(quote.author);
    }

    void shareQuote(){
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, mainViewModel.getQuote().text);
        sendIntent.setType("text/plain");
        Intent shareIntent = Intent.createChooser(sendIntent, null);
        startActivity(shareIntent);
    }
}