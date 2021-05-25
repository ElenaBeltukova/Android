package com.example.secondapp;

import androidx.fragment.app.Fragment;

public class UserUpdActivity extends SingleFragmentActivity{
    @Override
    protected Fragment createFragment() {
        Fragment fragment = new UserUpdFragment();
        return fragment;
    }
}
