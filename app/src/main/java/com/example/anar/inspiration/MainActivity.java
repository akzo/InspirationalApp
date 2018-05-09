package com.example.anar.inspiration;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //every time the app is open we want to show a random inspirational quote
        randomQuote();
    }

    //whenever the refresh button is clicked we just show a new quote baby
    public void refresher(View v){
        randomQuote();
    }

    //this is a method that randomly generates a quote
    private void randomQuote(){
        TextView quoteText = (TextView) findViewById(R.id.quote);
        //creating a random index that we will use to get stuff from database
        Random rand = new Random();
        int randIndex = rand.nextInt(9)+0;


        //opening the database and reading from it
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        List<String> quotes= databaseAccess.getQuotes();
        List<String> authors = databaseAccess.getAuthor();
        databaseAccess.close();

        //creating a string with an inspirational quotes and showing it to the user
        String message = "\""+quotes.get(randIndex) + "\"" + "<br><br> By " + authors.get(randIndex);
        quoteText.setText(Html.fromHtml(message));


    }
}
