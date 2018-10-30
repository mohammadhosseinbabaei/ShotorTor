package com.example.moham.shotortor.HomePage;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;
import com.example.moham.shotortor.Base.SingleFragmentActivity;


public class HomeActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context context) {
        return new Intent(context, HomeActivity.class);
    }

    @Override
    protected Fragment createFragment() {
        return HomeFragment.newInstance();
    }
}
