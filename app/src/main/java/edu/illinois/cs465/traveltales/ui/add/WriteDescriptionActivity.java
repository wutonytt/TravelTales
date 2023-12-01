package edu.illinois.cs465.traveltales.ui.add;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;
import java.util.Objects;

import edu.illinois.cs465.traveltales.R;


public class WriteDescriptionActivity extends AppCompatActivity
        implements DiscardEditDialogFragment.DiscardEditDialogListener {

    EditText edit_text_title;
    EditText edit_text_location;
    EditText edit_text_description;
    String title = "";
    String location = "";
    String description = "";
    int visibility;

    boolean myItemShouldBeEnabled;

    ArrayList<Uri> images;
    int coverPhotoId;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_description);

        // get intent extras
        Intent intent = getIntent();
        images = (ArrayList<Uri>) intent.getSerializableExtra("selected_images");
        coverPhotoId = intent.getIntExtra("cover_photo_id", 0);
        title = intent.getStringExtra("title");
        location = intent.getStringExtra("location");
        description = intent.getStringExtra("description");
        visibility = intent.getIntExtra("visibility", 1);

        edit_text_title = findViewById(R.id.edit_text_title);
        edit_text_location = findViewById(R.id.edit_text_location);
        edit_text_description = findViewById(R.id.edit_text_description);
        edit_text_title.setText(title);
        edit_text_location.setText(location);
        edit_text_description.setText(description);
        myItemShouldBeEnabled = title != null;
        invalidateOptionsMenu();

        Log.v("tony", "writeDescription: " + coverPhotoId + " " + title + " " + location + " " + description);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        edit_text_title.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                myItemShouldBeEnabled = s.toString().trim().length() != 0;
                invalidateOptionsMenu();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.custom_action_bar_next, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.action_next);
        item.setEnabled(myItemShouldBeEnabled);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        edit_text_title = findViewById(R.id.edit_text_title);
        edit_text_location = findViewById(R.id.edit_text_location);
        edit_text_description = findViewById(R.id.edit_text_description);

        title = edit_text_title.getText().toString();
        location = edit_text_location.getText().toString();
        description = edit_text_description.getText().toString();

        if (item.getItemId() == R.id.action_next) {
            // TODO: store input value and return to the previous page with updated identifier
            Toast.makeText(WriteDescriptionActivity.this, title + description, Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, ConfirmPost.class);
            intent.putExtra("selected_images", images);
            Log.v("tony", "Sending the cover photo id =" + coverPhotoId);
            intent.putExtra("cover_photo_id", coverPhotoId);
            intent.putExtra("title", title);
            intent.putExtra("location", location);
            intent.putExtra("description", description);
            intent.putExtra("visibility", visibility);


            startActivity(intent);
        } else if (item.getItemId() == android.R.id.home) {
            if (title.length() != 0 || location.length() != 0 || description.length() != 0) {
                edit_text_title.clearFocus();
                edit_text_location.clearFocus();
                edit_text_description.clearFocus();
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(edit_text_title.getWindowToken(), 0);
                imm.hideSoftInputFromWindow(edit_text_location.getWindowToken(), 0);
                imm.hideSoftInputFromWindow(edit_text_description.getWindowToken(), 0);
                showDiscardEditDialog();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);  // false for proceeding
    }

    public void showDiscardEditDialog() {
        // Create an instance of the dialog fragment and show it.
        DiscardEditDialogFragment discardEditDialogFragment = new DiscardEditDialogFragment();
        discardEditDialogFragment.show(getSupportFragmentManager(), "DISCARD_EDIT_DIALOG");
    }

    // The dialog fragment receives a reference to this Activity through the
    // Fragment.onAttach() callback, which it uses to call the following
    // methods defined by the NoticeDialogFragment.NoticeDialogListener
    // interface.
    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        // User taps the dialog's positive button.
        finish();
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        // User taps the dialog's negative button.
    }
}
