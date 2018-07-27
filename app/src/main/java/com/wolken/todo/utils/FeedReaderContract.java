package com.wolken.todo.utils;

        import android.provider.BaseColumns;

public class FeedReaderContract {

    private FeedReaderContract() {}

    /* Inner class that defines the table contents */
    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "todo";
        public static final String COLUMN_NAME_ID= "ID";
        public static final String COLUMN_NAME_NAME = "taskName";
        public static final String COLUMN_NAME_DESCRIPTION = "taskDescription";
        public static final String COLUMN_NAME_DATE = "taskDate";
    }

}
