package com.azseza.stuffsearcher;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SearchActivity extends AppCompatActivity {
    // As usual we add our buttons fields and databases
    Button btnSearch ;
    TextView txtResult;
    EditText txtSearchQuerry;
    SQLiteDatabase database ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        database = openOrCreateDatabase("MyStuff", MODE_PRIVATE,null);
        btnSearch = findViewById(R.id.btnSearch);
        txtSearchQuerry = findViewById(R.id.txtSearchQuerry);
        btnSearch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                // we define our search querry
                String searchString = txtSearchQuerry.getText().toString();
                //prepare our search result
                Cursor cursor =  database.rawQuery("SELECT description FROM stuff WHERE ? LIKE description", new String[]{searchString});
                try {
                    cursor.moveToFirst();
                    String result = cursor.getString(0);
                    txtResult.append(result);

                } catch (Exception e){
                    Toast.makeText(getApplicationContext(),"An error ocured :$ ", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}