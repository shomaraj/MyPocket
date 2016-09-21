package com.example.administrator.mypocket.data;

import android.provider.BaseColumns;

/**
 * Created by Administrator on 9/20/2016.
 */
public class LoginContract {
    public static final class UserDetailsEntry implements BaseColumns {
        public static final String TABLE_NAME = "user_details";
        public static final String COLUMN_USERNAME = "column_username";
        public static final String COLUMN_PASSWORD = "column_password";
    }
}
