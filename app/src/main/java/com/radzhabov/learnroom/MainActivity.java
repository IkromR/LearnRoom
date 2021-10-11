package com.radzhabov.learnroom;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.radzhabov.learnroom.DataModel.DateConverter;
import com.radzhabov.learnroom.DataModel.User;
import com.radzhabov.learnroom.DataModel.UserDAO;
import com.radzhabov.learnroom.DataModel.UserDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    Bitmap bmpImage;
    EditText name, uName, pass, dob;

    UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.user_image);
        bmpImage = null;
        name = findViewById(R.id.full_name);
        uName = findViewById(R.id.user_name);
        pass = findViewById(R.id.user_password);
        dob = findViewById(R.id.user_dob);

        userDAO = UserDatabase.getDBInstanse(this).userDAO();
    }

    final int CAMERA_INTENT = 51;

    public void takePicture(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, CAMERA_INTENT);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CAMERA_INTENT:
                if(resultCode == Activity.RESULT_OK){
                    bmpImage = (Bitmap) data.getExtras().get("data");
                    if(bmpImage != null){
                        imageView.setImageBitmap(bmpImage);
                    }else {
                        Toast.makeText(
                                this,
                                "Bitmap is NULL",
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                }else {
                    Toast.makeText(
                            this,
                            "Result not OK",
                            Toast.LENGTH_SHORT
                    ).show();
                }
                break;
        }
    }

    public void saveUser(View view) {
        if (name.getText().toString().isEmpty() || uName.getText().toString().isEmpty()
            || pass.getText().toString().isEmpty() || dob.getText().toString().isEmpty()
            || bmpImage == null){
            Toast.makeText(
                    this,
                    "user Data is missing",
                    Toast.LENGTH_SHORT
            ).show();
        }else {
            User user = new User();
            user.setFullName(name.getText().toString());
            user.setUserName(uName.getText().toString());
            user.setPassword(pass.getText().toString());
            user.setImage(DateConverter.convertImage2ByteArray(bmpImage));
            try {
                user.setDob(new SimpleDateFormat("dd/mm/yyyy").parse(dob.getText().toString()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            userDAO.insertUser(user);
            Toast.makeText(
                    this,
                    "Insertion succefull!",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }

    public void showUsers(View view) {
        Intent intent = new Intent(this, ShowUsersActivity.class);
        startActivity(intent);
    }
}