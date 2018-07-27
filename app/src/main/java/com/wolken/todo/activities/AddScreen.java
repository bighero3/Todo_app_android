package com.wolken.todo.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.wolken.todo.Adapter.Adapter;
import com.wolken.todo.R;
import com.wolken.todo.models.Events;
import com.wolken.todo.utils.FeedReaderDbHelper;

public class AddScreen extends AppCompatActivity {

    private FloatingActionButton mfloatingActionButton;
    private FloatingActionButton mfloatingActionButton2;
    private RecyclerView mRecyclerView;
    private Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private FeedReaderDbHelper mFeedReaderDbHelper;
    private RecyclerView mtaskRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_notes_add_screen);

        mFeedReaderDbHelper = new FeedReaderDbHelper(this);

        mfloatingActionButton = findViewById(R.id.fab_show_button);

        mFeedReaderDbHelper = new FeedReaderDbHelper(getApplicationContext());


        mRecyclerView = findViewById(R.id.rv_tasks);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new Adapter(mFeedReaderDbHelper.getAllNotes(), getApplicationContext(), new Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(Events task) {

                Toast.makeText(getApplicationContext(),"Item Clicked",Toast.LENGTH_SHORT).show();




                AlertDialog.Builder builder = new AlertDialog.Builder(AddScreen.this);
                builder.setMessage("How would you like to proceed?");
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Delete function to be called

                        //PERFORM DELETE OPERATION ON THE DATABASE SIDE


                    }
                });

                builder.setNegativeButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Update function to be called

                        //PERFORM UPDATE OPERATION ON THE DB
                    }
                });
                //CREATE AN ALERT DIALOGUE OBJECT
                AlertDialog alert11 = builder.create();
                alert11.show();







            }
        });
        mRecyclerView.setAdapter(mAdapter);


        mfloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(AddScreen.this, EditTaskScreenActivity.class));
                /*View show_add_button = findViewById(R.id.fab_add_button);
                show_add_button.setVisibility(View.VISIBLE);*/

            }
        });


        mfloatingActionButton2 = findViewById(R.id.fab_add_button);

        mfloatingActionButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(AddScreen.this, EditTaskScreenActivity.class));

            }
        });

        mRecyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(AddScreen.this, "Recycler view on clicked", Toast.LENGTH_SHORT).show();
            }
        });


    }
} //
