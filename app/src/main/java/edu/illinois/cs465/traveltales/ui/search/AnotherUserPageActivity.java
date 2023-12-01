package edu.illinois.cs465.traveltales.ui.search;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import edu.illinois.cs465.traveltales.R;
import edu.illinois.cs465.traveltales.databinding.FragmentAnotherUserPageBinding;


public class AnotherUserPageActivity extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_another_user_page);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        button = findViewById(R.id.seeDescriptionButton);
        button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openUserJournalDescription();
                }
            }
        );
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    public void openUserJournalDescription(){
        Intent intent = new Intent(this, UserDescJourActivity.class);
        startActivity(intent);
    }

}