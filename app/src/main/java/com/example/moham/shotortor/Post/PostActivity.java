package com.example.moham.shotortor.Post;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;
import com.example.moham.shotortor.Base.SingleFragmentActivity;

public class PostActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, PostActivity.class);

        return intent;
    }

    @Override
    protected Fragment createFragment() {
        return PostFragment.newInstance();
    }
}
