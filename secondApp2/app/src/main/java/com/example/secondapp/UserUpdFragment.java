package com.example.secondapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class UserUpdFragment extends Fragment {
    User user;
    int position;
    Button updateUserBtnFr;
    EditText editTextNameUpdFr;
    EditText editTextLastNameUpdFr;
    EditText editTextPhoneUpdFr;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getActivity().getIntent().getIntExtra("position",1);
        user = (User) getActivity().getIntent().getSerializableExtra("user");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_upd, viewGroup, false);

        updateUserBtnFr = view.findViewById(R.id.updateUserBtnFr);
        editTextNameUpdFr = view.findViewById(R.id.editTextNameUpdFr);
        editTextLastNameUpdFr = view.findViewById(R.id.editTextLastNameUpdFr);
        editTextPhoneUpdFr = view.findViewById(R.id.editTextPhoneUpdFr);

        editTextNameUpdFr.setText(user.getUserName());
        editTextLastNameUpdFr.setText(user.getUserLastName());
        editTextPhoneUpdFr.setText(user.getPhone());

        updateUserBtnFr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Users users = Users.get(getActivity());

                user.setUserName(editTextNameUpdFr.getText().toString());
                user.setUserLastName(editTextLastNameUpdFr.getText().toString());
                user.setPhone(editTextPhoneUpdFr.getText().toString());
                users.updateUser(user);

                Intent intent = new Intent(getActivity(), UserPagerActivity.class);
                intent.putExtra("position", position);
                startActivity(intent);

            }
        });
        return view;
    }
}
