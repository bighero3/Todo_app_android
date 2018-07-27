package com.wolken.todo.activities;

import android.app.DatePickerDialog;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.wolken.todo.R;
import com.wolken.todo.utils.FeedReaderContract;
import com.wolken.todo.utils.FeedReaderDbHelper;

import java.util.Calendar;

public class EditTaskScreenActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private TextView mTextView;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private Button mAddButton;
    private String date;
    private EditText mName;
    private EditText mDescription;
    private TextView mDate;
    private String mTaskName;
    private String mTaskDesc;
    private Long id;


    private FeedReaderDbHelper feedReaderDbHelper;


    private static final String TAG = EditTaskScreenActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);


        mTextView = findViewById(R.id.text_selectDate);

        feedReaderDbHelper = new FeedReaderDbHelper(EditTaskScreenActivity.this);
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Toast.makeText(getApplicationContext(), "Showing Date", Toast.LENGTH_LONG).show();


                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        EditTaskScreenActivity.this,
                        mDateSetListener,
                        year, month, day);

                dialog.show();

            }
        });


        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;

                date = day + "/" + month + "/" + year;

                mTextView.setText(date);


            }
        };

        mAddButton = findViewById(R.id.button_addTask);

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                mName = (EditText) findViewById(R.id.text_taskName);
                mTaskName = mName.getText().toString();

                mDescription = (EditText) findViewById(R.id.text_taskDescription);
                mTaskDesc = mDescription.getText().toString();

                mDate = (TextView) findViewById(R.id.text_selectDate);

                if (date!=null) {
                    if (mTaskDesc.length()!=0){
                        if (mTaskName.length()!=0){

                            try {
                                id = feedReaderDbHelper.insertData(mTaskName, mTaskDesc, date);
                                if (id > 0) {
                                    Toast.makeText(EditTaskScreenActivity.this, "Data Added", Toast.LENGTH_SHORT).show();


                                } else {
                                    Toast.makeText(EditTaskScreenActivity.this, "Data Not Added", Toast.LENGTH_LONG).show();
                                }

                                startActivity(new Intent(EditTaskScreenActivity.this, AddScreen.class));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }else {mName.setError(getResources().getString(R.string.no_name));}
                    }else {mDescription.setError(getResources().getString(R.string.no_description));}
                } else {mDate.setError(getResources().getString(R.string.no_date));}

            }
        });


    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

    }
}
