package com.example.ajalalalhoseini.myapplication;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class HistoryActivity extends AppCompatActivity {
    SimpleCursorAdapter dataAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder().setDefaultFontPath("fonts/Roboto-Thin.ttf").setFontAttrId(R.attr.fontPath).build()); // Declaring the globalized font face
        setContentView(R.layout.activity_history);

        //ListView list = new ListView(R.id.lis);
        DatabaseHandler db = new DatabaseHandler(this);
        Cursor cursor = db.fetchAllHistory();
        ///////For logging purposes code below for print out cursor
        //Log.d("DB UTILS", "DB UTILS");
        //DatabaseUtils.dumpCursorToString(cursor);
        // columns to be bound
        String[] columns = new String[] {
                DatabaseHandler.getKeyId(),
                DatabaseHandler.getKeyEquality(),
                DatabaseHandler.getKeyStatement()
        };
        //// the XML defined views which the data will be bound to
        int[] to = new int[] {
                R.id.tvId,
                R.id.tvEquality,
                R.id.tvStatement
        };

        // create the adapter using the cursor pointing to the desired data
        //as well as the layout information
        dataAdapter = new SimpleCursorAdapter(
                this, R.layout.activity_history,
                cursor,
                columns,
                to,
                0);

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(dataAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // selected item
                String selectedItem =((TextView)view.findViewById(R.id.tvStatement)).getText().toString();
                Toast.makeText(getApplicationContext(), selectedItem, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                intent.putExtra("HISTORY_SELECTED_INFO", selectedItem);
                startActivity(intent);
            }
        });

    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    } // inject the override context of font face

}
