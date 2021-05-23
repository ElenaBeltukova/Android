package com.example.secondapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateUserActivity extends AppCompatActivity {

    Button updateUserBtn;
    EditText editTextNameUpd;
    EditText editTextLastNameUpd;
    EditText editTextPhoneUpd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);

        updateUserBtn = findViewById(R.id.updateUserBtn);
        editTextNameUpd = findViewById(R.id.editTextNameUpd);
        editTextLastNameUpd = findViewById(R.id.editTextLastNameUpd);
        editTextPhoneUpd = findViewById(R.id.editTextPhoneUpd);

        User user;
        user = (User) getIntent().getSerializableExtra("user");

        editTextNameUpd.setText(user.getUserName());
        editTextLastNameUpd.setText(user.getUserLastName());
        editTextPhoneUpd.setText(user.getPhone());


        updateUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Users users = Users.get(UpdateUserActivity.this);

                user.setUserName(editTextNameUpd.getText().toString());
                user.setUserLastName(editTextLastNameUpd.getText().toString());
                user.setPhone(editTextPhoneUpd.getText().toString());
                users.updateUser(user);
                onBackPressed();
            }
        });
    }
}