package edu.illinois.cs465.traveltales.ui.search;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import edu.illinois.cs465.traveltales.R;

public class UserDescJourActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_user_journal_description);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}