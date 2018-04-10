package com.example.shaheed.takeaclimbchallenge;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    ListView medicineList;
    FloatingActionButton fab;
    ArrayList<String> values;
    Cursor cursor, cursor2;
    List<Medicine> list = null;
    ArrayAdapter arrayAdapter;
    ContentValues contentValues;
    ArrayList<Medicine> databaseNameArrayList;
    SQLiteHelper sqLiteHelper;
    SearchView search_id;
    CharSequence searchQuery;
    SQLiteDatabase databaseWrite, databaseRead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fab = findViewById(R.id.fab);
        search_id = findViewById(R.id.search_id);
        searchQuery = search_id.getQuery();
        search_id.setBackgroundColor(Color.LTGRAY);
        medicineList = findViewById(R.id.medication_list);
        sqLiteHelper = new SQLiteHelper(this);
        databaseWrite = sqLiteHelper.getWritableDatabase();
        databaseRead = sqLiteHelper.getReadableDatabase();
        cursor = databaseRead.rawQuery("SELECT * FROM " + SQLiteHelper.TB_NAME,
                null);
        cursor2 = databaseRead.rawQuery("SELECT medName FROM " + SQLiteHelper.TB_NAME,
                null);
        contentValues = new ContentValues();
        databaseNameArrayList = new ArrayList<>();

        getValues();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Input.class);
                startActivity(i);
            }
        });

        medicineList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String mName = (String) medicineList.getItemAtPosition(position);
                int idName = (int) medicineList.getItemIdAtPosition(position);

                Intent mIntent = new Intent(MainActivity.this, DetailedMedication.class);
                mIntent.putExtra("name", mName);
                mIntent.putExtra("id", (idName + 1));
                startActivity(mIntent);
            }
        });

        search_id.setOnQueryTextListener(this);
    }

    public ArrayList<String> getValues() {

        if(cursor.moveToFirst()) {
            do {
                values.add(cursor.getString(cursor.getColumnIndex("medName")));
            }while(cursor.moveToNext());
        }

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, values);
        medicineList.setAdapter(arrayAdapter);
        sqLiteHelper.close();
        return values;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        filter(text);
        return false;
    }
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        list.clear();
        if (charText.length() == 0) {
            list.addAll(databaseNameArrayList);
        } else {
            for (Medicine mn : databaseNameArrayList) {
                if (mn.getName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    list.add(mn);
                }
            }
        }
    }

    @Override
    protected void onPause() {
        Intent myIntent = new Intent(this , Notification.class);
        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getService(this, 0, myIntent, 0);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.AM_PM, Calendar.AM);
        calendar.add(Calendar.DAY_OF_MONTH, 1);

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
                calendar.getTimeInMillis(), Long.parseLong(cursor2.getString(cursor2.getColumnIndex("interval"))), pendingIntent);

        super.onPause();
    }
}
