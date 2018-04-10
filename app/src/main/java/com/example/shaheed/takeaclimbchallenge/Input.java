package com.example.shaheed.takeaclimbchallenge;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.widget.DatePicker;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Input extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    private static final String TAG = "Post Fragment";
    Button start_date, end_date, save_btn;
    TextView medName, medDescription, medInterval;
    String start_date_str, end_date_str, medName_str, medInterval_str, medDescription_str;
    SQLiteHelper sqLiteHelper;
    SQLiteDatabase databaseWrite, databaseRead;
    ContentValues contentValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        save_btn = findViewById(R.id.save_id);
        start_date = findViewById(R.id.start_date_id);
        end_date = findViewById(R.id.end_date_id);
        medDescription = findViewById(R.id.description_id);
        medName = findViewById(R.id.name_id);
        medInterval = findViewById(R.id.interval_id);

        sqLiteHelper = new SQLiteHelper(this);
        databaseWrite = sqLiteHelper.getWritableDatabase();
        databaseRead = sqLiteHelper.getReadableDatabase();
        contentValues = new ContentValues();

        start_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                com.example.shaheed.takeaclimbchallenge.DatePicker datePicker = new com.example.shaheed.takeaclimbchallenge.DatePicker();
                datePicker.show(getFragmentManager(), TAG);
            }
        });

        end_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                com.example.shaheed.takeaclimbchallenge.DatePicker datePicker = new com.example.shaheed.takeaclimbchallenge.DatePicker();
                datePicker.show(getFragmentManager(), TAG);;
            }
        });

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                medName_str = medName.getText().toString();
                medDescription_str = medDescription.getText().toString();
                medInterval_str = medInterval.getText().toString();
                end_date_str = end_date.getText().toString();
                start_date_str = start_date.getText().toString();

                Medicine med = new Medicine(medName_str, medDescription_str, medInterval_str, start_date_str, end_date_str);

                String medication_name = med.getName();
                String medication_des = med.getDescription();
                String medication_interval = med.getInterval();
                String medication_endDate = med.getEndDate();
                String medication_startDate = med.getStartDate();


                boolean insertion = sqLiteHelper.insertEntry(medication_interval, medication_name,
                        medication_startDate, medication_endDate, medication_des);

                if (insertion){
                    Toast.makeText(Input.this,
                            "Inserted Successfully!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar mCalendar = new GregorianCalendar(year, month, dayOfMonth);
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.YEAR_FIELD);
        String cldr = dateFormat.format(mCalendar.getTime());
    }

    private String setDate(final Calendar calendar){
        final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.YEAR_FIELD);
        return dateFormat.format(calendar.getTime());
    }
}