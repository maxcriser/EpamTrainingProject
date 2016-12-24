package com.maxcriser.cards.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

import com.maxcriser.cards.R;
import com.maxcriser.cards.utils.FlipAnimation;

public class TestFlipAnimation extends AppCompatActivity {

    private View btnStart;
    private View btnFinish;
    private RelativeLayout mFrameLayout;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_flip);
        btnStart = findViewById(R.id.front);
        btnFinish = findViewById(R.id.back);
        mFrameLayout = (RelativeLayout) findViewById(R.id.framelayout_flip);

        final FlipAnimation flipAnimation = new FlipAnimation(btnStart, btnFinish);

        btnStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View pView) {
                flipAnimation.setReverse();
                mFrameLayout.startAnimation(flipAnimation);
            }
        });

        btnFinish.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View pView) {
                flipAnimation.setReverse();
                mFrameLayout.startAnimation(flipAnimation);
            }
        });

    }
}
