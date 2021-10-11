package com.radzhabov.learnroom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.radzhabov.learnroom.DataModel.UserDAO;
import com.radzhabov.learnroom.DataModel.UserDatabase;

public class ShowUsersActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_users);

        recyclerView = findViewById(R.id.user_recyclerview);

        userDAO = UserDatabase.getDBInstanse(this).userDAO();

        UserRecycler userRecycler = new UserRecycler(userDAO.getAllUsers());

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(userRecycler);
    }
}