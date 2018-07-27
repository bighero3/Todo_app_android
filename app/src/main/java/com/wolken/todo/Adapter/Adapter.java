package com.wolken.todo.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.wolken.todo.R;
import com.wolken.todo.models.Events;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {


    private List<RecyclerView> rvNotes;
    private OnItemClickListener listener ;
    private List<Events> taskList;
    private Context mContext;




    //CREATE AN INTERFACE
    public interface OnItemClickListener{

        void  onItemClick(Events task);
    }


    public Adapter(List<Events> taskList, Context mContext, OnItemClickListener listener) {
        this.taskList = taskList;
        this.mContext = mContext;
        this.listener = listener;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_task_helper, viewGroup, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder viewHolder, int i) {



        //WRITE A CUSTOM BIND METHOD IN THE VIEW HOLDER CLASS
        if (taskList != null) {

            viewHolder.bind(taskList.get(i),listener);

        }



    }



    @Override
    public int getItemCount() {

        if (taskList == null) {
            return 0;
        }
        return taskList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView desc;
        private TextView date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.tv_name);
            desc = itemView.findViewById(R.id.tv_description);
            date = itemView.findViewById(R.id.tv_date);
        }


        public void bind(final Events task, final OnItemClickListener onItemClickListener){

            if(null != task){

                //BINDING THE DATA TO THE VIEW

                name.setText(task.getTaskName001());
                desc.setText(task.getTaskDescription001());

                date.setText(task.getTaskDate001());


            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    onItemClickListener.onItemClick(task);


                }
            });


        }
    }
}