package edu.illinois.cs465.traveltales.ui.add;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.util.Objects;

import edu.illinois.cs465.traveltales.R;


public class WriteDescriptionActivity extends AppCompatActivity
        implements DiscardEditDialogFragment.DiscardEditDialogListener {

    EditText edit_text_title;
    EditText edit_text_description;
    String title;
    String description;

    boolean myItemShouldBeEnabled;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_description);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        edit_text_title = findViewById(R.id.edit_text_title);
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
        getMenuInflater().inflate(R.menu.custom_action_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.action_done);
        item.setEnabled(myItemShouldBeEnabled);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        edit_text_title = findViewById(R.id.edit_text_title);
        edit_text_description = findViewById(R.id.edit_text_description);

        title = edit_text_title.getText().toString();
        description = edit_text_description.getText().toString();

        if (item.getItemId() == R.id.action_done) {
            // TODO: store input value and return to the previous page with updated identifier
            Toast.makeText(WriteDescriptionActivity.this, title + description, Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, ClickPhotoActivity.class);
            startActivity(intent);

        } else if (item.getItemId() == android.R.id.home) {
            if (title.length() != 0 || description.length() != 0) {
                edit_text_title.clearFocus();
                edit_text_description.clearFocus();
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(edit_text_title.getWindowToken(), 0);
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
