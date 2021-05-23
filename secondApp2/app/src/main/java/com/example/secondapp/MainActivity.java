package com.example.secondapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    UserAdapter userAdapter;
    Button addUser;
    User currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("SYSTEM INFO: ", "Метод onCreate() запущен");

        if(savedInstanceState != null){
            currentUser = (User) savedInstanceState.getSerializable("user");
        }

        setContentView(R.layout.activity_main);
        addUser = findViewById(R.id.addUser);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddUserActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onResume(){
        super.onResume();
        recyclerViewInit();
        Log.d("SYSTEM INFO: ", "Метод onResume() запущен");
    }

    private void recyclerViewInit(){
        Users users = Users.get(MainActivity.this);
        List<User> userList = users.getUserList();
        userAdapter = new UserAdapter(userList);
        recyclerView.setAdapter(userAdapter);
    }
    // UserHolder отвечает за каждый элемент списко по отдельности
    // Именно здесь мы будем наполнчть нашу activity_item контентом
    private class UserHolder extends RecyclerView.ViewHolder{
        TextView itemTextView;
        TextView itemPhoneTextView;
        Button btnDelete;
        Button btnUpdate;

        public UserHolder(View itemView) {
            super(itemView);

            Log.d("SYSTEM INFO: ", "Конструктор UserHolder запущен");
            //itemView - текущий layout activity_item
            itemTextView = itemView.findViewById(R.id.itemTextView);
            itemPhoneTextView = itemView.findViewById(R.id.itemPhoneTextView);

            btnDelete = findViewById(R.id.btnDelete);
            btnUpdate = findViewById(R.id.btnUpdate);
        }

        public void bind(User user){
            Log.d("SYSTEM INFO: ", "Метод bind() запущен");

            itemTextView.setText(user.getUserName() + " " + user.getUserLastName());
            itemPhoneTextView.setText(user.getPhone());

        }
    }
    // UserAdapter нужен для того, чтобы разместить элементы на RecyclerView
    private class UserAdapter extends RecyclerView.Adapter<UserHolder>{
        List<User> userList;
        Button btnDelete;
        Button btnUpdate;
        public UserAdapter(List<User> userList) {
            this.userList = userList;
            Log.d("SYSTEM INFO: ", "Конструктор UserAdapter запущен");
            btnDelete = findViewById(R.id.btnDelete);
            btnUpdate = findViewById(R.id.btnUpdate);
        }

        @Override
        public UserHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            Log.d("SYSTEM INFO: ", "Метод onCreateViewHolder() запущен");

            // inflater - наполнитель
            View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_item, viewGroup, false);
            RecyclerView.ViewHolder holder = new UserHolder(itemView);

            holder.itemView.findViewById(R.id.btnDelete).setOnClickListener(new View.OnClickListener() {
                @SuppressWarnings("deprecation")
                @Override
                public void onClick(View v) {
                    int position = holder.getAdapterPosition();
                    currentUser = userList.get(position);
                    Users users = Users.get(MainActivity.this);
                    users.deleteUser(currentUser);
                    recyclerViewInit();
                }
            });

            holder.itemView.findViewById(R.id.btnUpdate).setOnClickListener(new View.OnClickListener() {
                @SuppressWarnings("deprecation")
                @Override
                public void onClick(View v) {
                    int position = holder.getAdapterPosition();
                    currentUser = userList.get(position);
                    Intent intent = new Intent(MainActivity.this, UpdateUserActivity.class);
                    intent.putExtra("user", (Serializable) currentUser);
                    startActivity(intent);
                }
            });
            return (UserHolder) holder;
        }

        @Override
        public void onBindViewHolder(UserHolder userHolder, int position) {
            Log.d("SYSTEM INFO: ", "Метод onBindViewHolder() запущен");

            User user = userList.get(position);
            userHolder.bind(user);

        }

        @Override
        public int getItemCount() {
            Log.d("SYSTEM INFO: ", "Метод getItemCount() запущен");

            return userList.size();
        }
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        Log.d("SYSTEM INFO: ", "Метод onSaveInstanceState() запущен");
        if (currentUser!=null) savedInstanceState.putSerializable("user", (Serializable)currentUser);
    }

}