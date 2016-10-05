package com.maxcriser.copytwitter;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String BLUE_COLOR = "#1ba4f4";
    public static final String NULL_COLOR = "#8899a1";

    ImageButton btnNotifications;
    ImageButton btnAdded;
    ImageButton btnFollow;

    TextView tweets;
    TextView media;
    TextView likes;

    Button btnTweets;
    Button btnMedia;
    Button btnLikes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton keyboard = (ImageButton) findViewById(R.id.btn_three_dots);
        keyboard.setRotation(90);

        btnNotifications = (ImageButton) findViewById(R.id.btn_notifications);
        btnAdded = (ImageButton) findViewById(R.id.btn_added);
        btnFollow = (ImageButton) findViewById(R.id.btn_follow);

        btnNotifications.setVisibility(View.INVISIBLE);
        btnAdded.setVisibility(View.INVISIBLE);

        tweets = (TextView) findViewById(R.id.line_tweets);
        media = (TextView) findViewById(R.id.line_media);
        likes = (TextView) findViewById(R.id.line_likes);

        btnTweets = (Button) findViewById(R.id.btn_tweets);
        btnMedia = (Button) findViewById(R.id.btn_media);
        btnLikes = (Button) findViewById(R.id.btn_likes);

    }

    public void ClearStatus() {
        tweets.setBackground(null);
        media.setBackground(null);
        likes.setBackground(null);

        btnTweets.setTextColor(Color.parseColor(NULL_COLOR));
        btnMedia.setTextColor(Color.parseColor(NULL_COLOR));
        btnLikes.setTextColor(Color.parseColor(NULL_COLOR));
    }

    public void ShowHighPhoto(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }

    public void Follow(View view) {
        btnFollow.setVisibility(View.INVISIBLE);
        btnAdded.setVisibility(View.VISIBLE);
        btnNotifications.setVisibility(View.VISIBLE);
    }

    public void UnFollowing(View view) {
        btnFollow.setVisibility(View.VISIBLE);
        btnAdded.setVisibility(View.INVISIBLE);
        btnNotifications.setVisibility(View.INVISIBLE);
    }

    public void TweetsClicked(View view) {
        ClearStatus();
        btnTweets.setTextColor(Color.parseColor(BLUE_COLOR));
        tweets.setBackgroundColor(Color.parseColor(BLUE_COLOR));
    }

    public void MediaClicked(View view) {
        ClearStatus();
        btnMedia.setTextColor(Color.parseColor(BLUE_COLOR));
        media.setBackgroundColor(Color.parseColor(BLUE_COLOR));
    }

    public void LikesClicked(View view) {
        ClearStatus();
        btnLikes.setTextColor(Color.parseColor(BLUE_COLOR));
        likes.setBackgroundColor(Color.parseColor(BLUE_COLOR));
    }

    public void BackPress(View view) {
        Toast.makeText(this, "Back", Toast.LENGTH_SHORT).show();
    }


    public void SettingsPress(View view) {
        Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
    }

    public void SearchPress(View view) {
        Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show();
    }

    public void NewTweetPress(View view) {
        Toast.makeText(this, "New tweet", Toast.LENGTH_SHORT).show();
    }
}
