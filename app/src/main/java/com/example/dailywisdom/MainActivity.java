package com.example.dailywisdom;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    TextView quoteTv;
    TextView authorTv;
    TextView previous_tv;
    TextView next_tv;
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
        previous_tv = findViewById(R.id.previous_tv);
        next_tv = findViewById(R.id.next_tv);

        shareFB = findViewById(R.id.share_fb);

        next_tv.setOnClickListener((view)->{
            setQuote(mainViewModel.nextQuote());
        });

        previous_tv.setOnClickListener((view)->{
            setQuote(mainViewModel.previousQuote());
        });

        mainViewModel = new ViewModelProvider(
                this,
                new MainViewModelFactory(
                        getApplicationContext()
                )
        ).get(MainViewModel.class);
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
}