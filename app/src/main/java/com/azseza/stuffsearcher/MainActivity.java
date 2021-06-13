package com.azseza.stuffsearcher;
// imports
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
// Activity class
public class MainActivity extends AppCompatActivity {
    // Buttons and Fields
    Button btnSearch;
    EditText txtStuff;
    Button btnAddStuff;
    SQLiteDatabase database;
/*HOW TO USE ANOOTHER DATABASE ?
You could see this for NoSQL exemple
https://docs.mongodb.com/realm/sdk/android/
* */

// View initilisation
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Mapping buttons with the findViewById method
        btnSearch = findViewById(R.id.btnGoToSearch);
        txtStuff = findViewById(R.id.txtStuff);
        btnAddStuff = findViewById(R.id.btnAddStuff);
        // Connecting to SQLite Database and creating tables and data
        database = openOrCreateDatabase("MyStuff", MODE_PRIVATE,null);
        // And we are just going to create a simple table called stuff that could be searched later on
        database.execSQL("CREATE TABLE IF NOT EXISTS stuff (_id long primary key, description varchar);");
        // And then we implement oour first button , that saves stuff 
        //      First we add an event listener for adding Stuff
        btnAddStuff.setOnClickListener(new View.OnClickListener() {
            // and then we define the actions that comes after the click !
            @Override
            public void onClick(View v) {
                // we consume the the inputs
                String stuff = txtStuff.getText().toString();
                // verify stuff ain't empty
                if (stuff.equals("")) {
                    Toast.makeText(MainActivity.this, "We first need stuff in order ", Toast.LENGTH_SHORT).show();
                }
                // then we make our Querry With a cursor, the cursor object is used to test the result of the querry
                else {
                    try {
                        database.execSQL("INSERT INTO stuff VALUES (?)", new Object[]{stuff});
                    } catch(SQLiteException e){
                        Toast.makeText(MainActivity.this, "An Error Ocured , sorry :$", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });
        //      Then we add an other event listener for switching to the next view
        btnSearch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(),SearchActivity.class) ;
            }
        });
    }
}