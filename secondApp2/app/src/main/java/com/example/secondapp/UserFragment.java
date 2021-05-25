package com.example.secondapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.io.Serializable;

public class UserFragment extends Fragment {
    User user;
    int position;
    TextView userInfo;
    ImageButton btnUpdate;
    ImageButton btnDelete;
    ImageButton btnList;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        position = getActivity().getIntent().getIntExtra("position",1);
        user = Users.get(getActivity()).getUserList().get(position);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_user, viewGroup, false);
        userInfo = view.findViewById(R.id.userInfo);
        userInfo.setText(user.getUserName()+" "+
                user.getUserLastName()+"\n"+
                user.getPhone());

        Users users = Users.get(getActivity());
        btnUpdate = view.findViewById(R.id.btnUpdate);
        btnDelete = view.findViewById(R.id.btnDelete);
        btnList = view.findViewById(R.id.btnList);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                users.deleteUser(user);
                userInfo.setText("Пользователь " + user.getUserName()+" "+user.getUserLastName() + " удален");
                userInfo.setTextColor(Color.RED);
                btnUpdate.setEnabled(false);
                btnDelete.setEnabled(false);
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UserUpdActivity.class);
                intent.putExtra("user", (Serializable) user);
                intent.putExtra("position", position);
                startActivity(intent);

            }
        });

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}