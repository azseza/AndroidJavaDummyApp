# Android 101

# Introduction

This repository is built with  educational purposes in mind.

It's a simple android app with two activities, that showcases the basics in angular development, may it serve as a guideline for new comers to get a hold of more 'savoir faire' in Android Development. 

As always feel free to do what ever you want with the code and the repository as long as it serves you right 🙂

# Prerequisites

You'll need an IDE, hopefully the best one for learning this amazing and fun technology : [Android Studio](https://developer.android.com/studio/install) 

# What is an Android App :

Basically an android application is made of two sorts of files and a building system; 

- Java or Kotlin
- *.XML ( like AndroidManifest.xml, and all the activity definitions ... )
- Gradle files

### Understanding an Android apk App :

Well in short those three types of files, surely with the help of compilators and build systems build the app like shown in this image : 

![Android%20101%20258213c52dbb441895239c3e8693d905/android1.png](Android%20101%20258213c52dbb441895239c3e8693d905/android1.png)

In the first stage all source code and library java files are compiled to *.dex files and in the other way all xml and resources files (also xml) are compiled to become Compiled resources.

Finally the apk compilator builds the java xml juice to produce an Android Package which is instalable and run-able in any almost any android platform. 

This whole process is done with the help of Gradle which is responsible for all the routines of the building process, Gradle is a magnificent tool, have a look at it [here](https://docs.gradle.org/current/userguide/what_is_gradle.html), and have a look at it's cousins [here](https://maven.apache.org/what-is-maven.html) and [here](https://cwiki.apache.org/confluence/display/ANT/) .

### The app that we showcase in here

This is a simple app made to of two activities, the first activity (main activity) simply adds stuff to a table in an SQLite database , and the second activity simply searches the stuff stach for the best result.

# Creating the App

open your editor and create a no activity app

1. right click on your project tree, New > Empty Activity 
2. smash this design in the design part 

![Android%20101%20258213c52dbb441895239c3e8693d905/androi2.png](Android%20101%20258213c52dbb441895239c3e8693d905/androi2.png)

- We add two buttons and an edit text field, one button for the adding and one for routing us to the searching activity, the text field thing discribes the stuff we are going to add in our stuff stash.

Since we want to make this our main activity , we need to specify it inside the AndroidManifest.xml file like so 

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.azseza.stuffsearcher">

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/Theme.StuffSearcher">
        <activity android:name=".SearchActivity"></activity>
        <!--We specify the main activity with the "intent-filter" box -->
	<activity android:name=".MainActivity" >
            <!--Like so .... -->
	    <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>

</manifest>
```

# Defining our first activity

We create a new class, our main activity class that describes and defines our main activity; like so 

An activity class has certain form to respect 

```java
package com.azseza.stuffsearcher;
// imports
[ ... ] 
// Activity class
public class MainActivity extends AppCompatActivity {
    // Buttons, Fields, and DataBases 
/*HOW TO USE ANOOTHER DATABASE ?
You could see this for NoSQL exemple
https://docs.mongodb.com/realm/sdk/android/
* */

    // View initilisation
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // <== For initiation purpeses
        setContentView(R.layout.activity_main); // <= To mapp the disired layout
        // Mapping buttons with the findViewById 
        // Connecting to SQLite Database and creating tables and 
        // And we are just going to create a simple table called stuff that 
	// could be searched later 
        // And then we implement oour first button , that saves stuff 
        //      First we add an event listener for adding Stuff
        btnAddStuff.setOnClickListener(new View.OnClickListener() {
            // and then we define the actions that comes after the click !
            @Override
            public void onClick(View v) {
		//We Do stuff 
            }
        });
        //      Then we add an other event listener for switching to the next view
        btnSearch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
		// We do some other cool stuff 
            }
        });
    }
}
```

As you see an activity class "extends" an "AppCompatActivity" wich is as discribed in the [docs](https://developer.android.com/reference/androidx/appcompat/app/AppCompatActivity#summary) is a Base class for activities that wish to use some of the newer platform features on older Android devices. Some of these backported features include:.

- Using the action bar, including action items, navigation modes
- Built-in switching between light and dark themes
- Integration with DrawerLayout by using the getDrawerToggleDelegate() API.

So as shown in the code above, this class has to be initilized with a onCreate() function, in which we define all of our view components; and by view components we mean buttons, database connection, actions , views, messages 

### Buttons & Fields & DataBases

In order to refrence a button or a field from a xml view file in the java class we have to do so in the onCreate function, this way 

```java
public class MainActivity extends AppCompatActivity {
    // Buttons and Fields and Databases
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
```

 For data bases , we are using a module 

```java
import android.database.sqlite.SQLiteDatabase;
```

the 'SQLiteDatabase' object is an object derivated from the 'android.daatabase' package; which is discribed in the [docs](https://developer.android.com/reference/android/database/package-summary) as follows 

> Contains classes to explore data returned through a content provider.
If you need to manage data in a private database, use the android.database.sqlite classes. These classes are used to manage the Cursor object returned from a content provider query. Databases are usually created and opened with openOrCreateDatabase(String, int, SQLiteDatabase.CursorFactory) To make requests through content providers, you can use the content.ContentResolver class.
All databases are stored on the device in /data/data/<package_name>/databases

So as you may have noticied , there are two types of databases in android : 

- Local databases : SQLite database stored in  ' /data/data/<package_name>/databases'
- Remote databases : Any type of SQL, NoSQL database will work , the important thing is that we create a request with a body that has all the data needed, and a response to know if it was successfull; this [peace of code](https://www.c-sharpcorner.com/article/send-data-to-the-remote-database-in-android-application/) showcases an exemple

```java

/*
* URL = 'https://www.c-sharpcorner.com/article/send-data-to-the-remote-database-in-android-application/'
* sign in and sign up exemple to a database 
* I DO NOT OWN THIS ! 
*/ 
public class MainActivity extends AppCompatActivity {    
    Button button;    
    EditText Name , Email;    
    String server_url = "http://192.168.43.246/app/dbconfig.php";    
    AlertDialog.Builder builder;    
    
    @Override    
    protected void onCreate(Bundle savedInstanceState) {    
	// Initilizing the activity class         
	super.onCreate(savedInstanceState);    
        setContentView(R.layout.activity_main);    
	    // linking buttons to actions 
        button = (Button) findViewById(R.id.bn);    
        Name = (EditText) findViewById(R.id.name1);    
        Email = (EditText) findViewById(R.id.email1);    
        builder = new AlertDialog.Builder(MainActivity.this);    
	// Creating an action in a button 	        
	button.setOnClickListener(new View.OnClickListener() {    
            @Override    
            public void onClick(View v) {    
                final String name , email ;    
                name =Name.getText().toString();    
                email=Email.getText().toString();    
                // Creating a request
		StringRequest stringRequest = new 
			StringRequest(Request.Method.POST, server_url, new Response.Listener<String>() {    
                    // Here we define what to do with the response 
		    @Override    
                    public void onResponse(String response) {    
    
                        builder.setTitle("Server Response");    
                        builder.setMessage("Response :"+response);    
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {    
                            @Override    
                            public void onClick(DialogInterface dialog, int which) {    
                                Name.setText("");    
                                Email.setText("");    
                            }    
                        });    
                        AlertDialog alertDialog = builder.create();    
                        alertDialog.show();    
    
                    }    
                }    
    
                        , new Response.ErrorListener() {    
                    @Override    
                    public void onErrorResponse(VolleyError error) {    
                        Toast.makeText(MainActivity.this,"some error found .....",Toast.LENGTH_SHORT).show();    
                        error.printStackTrace();    
    
                    }    
                }){    
                    @Override    
                    protected Map<String, String> getParams() throws AuthFailureError {    
                        Map <String,String> Params = new HashMap<String, String>();    
                        Params.put("name",name);    
                        Params.put("email",email);    
                        return Params;    
    
                    }    
                };    
                Mysingleton.getInstance(MainActivity.this).addTorequestque(stringRequest);    
            }    
        });    
    
    }    
}
```

In our case we are going to use a local SQLite database, but before that , I'm going to show you some important features that the 'android.database' packege provides in order to deal with Input/Output stream with the SQLiteDataBase 

- Cursor : A cursor is well known object in SQL in general, in PL-SQL for instance Oracle creates a memory area, known as the context area, for  processing an SQL statement, which contains all the information needed  for processing the statement; for example, the number of rows processed, etc. Therefor a  **cursor** is a pointer to this context area. PL/SQL controls  the context area through a cursor. A cursor holds the rows (one or more)  returned by a SQL statement. The set of rows the cursor holds is  referred to as the **active set**.  In Android Cursors have the same concept but it's a little bit more owesomer since SQLite is allready embeded in android , and there is no need to make an admin or to make any thing fancy like managinig an SQLite server (unlike oracle) ...So, when we want to interact with a database, inside an Android Application, we have to grant our Activity with the right to random read-write access to the contents of this database. For this reason, Android has the Cursor interface, that provides random read-write access to the result set returned by a database query. Her's an [exmple](https://examples.javacodegeeks.com/android/core/database/android-cursor-example/)

```java
try {
            // open or create the sqlite database
            sqliteDB = this.openOrCreateDatabase(dbName, MODE_PRIVATE, null);
            // execute the query
            sqliteDB.execSQL("CREATE TABLE IF NOT EXISTS " + tableName + " (bookTitle VARCHAR);");
            // insert bookTitle Array versions into table created
            for (String ver : bookTitle) {
                sqliteDB.execSQL("INSERT INTO " + tableName + " Values ('" + ver + "');");
            }
 
            // create Cursor in order to parse our sqlite results
            Cursor cursor = sqliteDB.rawQuery("SELECT bookTitle FROM " + tableName, null);
            // if Cursor is contains results
            if (cursor != null) {
                // move cursor to first row
                if (cursor.moveToFirst()) {
                    do {
                        // Get version from Cursor
                        String bookName = cursor.getString(cursor.getColumnIndex("bookTitle"));
 
                        // add the bookName into the bookTitles ArrayList
                        bookTitles.add(bookName);
                        // move to next row
                    } while (cursor.moveToNext());
                }
            }
```

- More awesome Cursors : Cross Process Cursors : This type of cursors can be used with multiple proceses , check [this](https://developer.android.com/reference/android/database/CrossProcessCursor) and [this](https://stuff.mit.edu/afs/sipb/project/android/docs/reference/android/database/CrossProcessCursor.html) out
- Exceptions: 'android.database' package comes filled with exceptions to help you define and control data input and output to users , check [this](https://developer.android.com/reference/android/database/DatabaseErrorHandler)  out.

now back to our app , we still have to cover how to add data to database through a button and how to pass data to an other view , and make a quick search method 🙂

### Creating the firs button

Buttons are created to represent a [View](https://developer.android.com/reference/android/view/View) , which basicly the building block for user interface components. A View occupies a rectangular area on the screen and is responsible for drawing and event handling.

to code now 

```java
public class MainActivity extends AppCompatActivity {
[ ... ] 
Button btnAddStuff;
[ ... ]
    @Override
    protected void onCreate(Bundle savedInstanceState) {
	[ ... ]
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
```

### Creating our first intent !

Intent class is a class that sends you from an activity to another, we are going to embed a button with an intent function  

```java
//      Then we add an other event listener for switching to the next view
        btnSearch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(),SearchActivity.class) ;
            }
        });
```

### Creating our first SearchQuerry !!!! 🤤

```java
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
```

# That's It !

Hope you find this intresting; for further resources try [this](http://www.google.com) 🙂