package com.example.storage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.storage.model.Note;
import com.example.storage.repository.DatabaseHelper;

import java.sql.SQLException;

public class CreateNoteActivity extends AppCompatActivity {

    public EditText etNoteTitle,etNoteBody;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        etNoteTitle = findViewById(R.id.etNoteTitle);
        etNoteBody = findViewById(R.id.etNoteBody);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.create_note_top_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.btn_share_note:
                Toast.makeText(this, "Share Note", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.btn_save_note:
                createNote();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void createNote(){
        String noteTitle = etNoteTitle.getText().toString();
        String noteBody = etNoteBody.getText().toString();

        if (!noteBody.equals("") && !noteTitle.equals("")){
            DatabaseHelper db = new DatabaseHelper(getApplicationContext());

            db.createNote(new Note(noteTitle,noteBody));
            Toast.makeText(this, "Note Created", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        }
    }
}