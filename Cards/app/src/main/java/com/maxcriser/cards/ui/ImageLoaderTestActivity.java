package com.maxcriser.cards.ui;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.maxcriser.cards.R;
import com.maxcriser.cards.async.OnResultCallback;
import com.maxcriser.cards.loader.image.ImageLoader;
import com.maxcriser.cards.util.AlertImageViewer;

import java.util.ArrayList;
import java.util.List;

public class ImageLoaderTestActivity extends AppCompatActivity {

    private ImageView image1;
    private ImageView image2;
    private ImageView image3;
    private ImageView image4;
    private ImageView image5;
    private Bitmap mBitmap;

    private List<String> urls = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imageloader);
        urls.add("http://www.zastavki.com/pictures/640x480/2015/Auto___Nissan_Tuned_Nissan_350Z_on_the_road_111065_29.jpg");
        urls.add("http://www.zastavki.com/pictures/640x480/2015/Girls_The_green-eyed_blonde_in_the_car_111062_29.jpg");
        urls.add("http://www.zastavki.com/pictures/640x480/2015/Girls_White_teeth_smile_cute_girl_094614_29.jpg");
        urls.add("https://pp.vk.me/c836327/v836327084/18633/l8Yb4Y8mczs.jpg");
        urls.add("https://pp.vk.me/c836327/v836327084/1863a/cQ9uznc8mZo.jpg");
        image1 = (ImageView) findViewById(R.id.first);
        image2 = (ImageView) findViewById(R.id.second);
        image3 = (ImageView) findViewById(R.id.third);
        image4 = (ImageView) findViewById(R.id.fourth);
        image5 = (ImageView) findViewById(R.id.fifth);
        ImageLoader.getInstance().downloadAndDraw(urls.get(1), image1, new OnResultCallback<Bitmap, Void>() {
            @Override
            public void onSuccess(Bitmap pBitmap) {
                mBitmap = pBitmap;
            }

            @Override
            public void onError(Exception pE) {

            }

            @Override
            public void onProgressChanged(Void pVoid) {

            }
        });
        ImageLoader.getInstance().downloadAndDraw(urls.get(1), image2, null);
        ImageLoader.getInstance().downloadAndDraw(urls.get(2), image3, null);
        ImageLoader.getInstance().downloadAndDraw(urls.get(3), image4, null);
        ImageLoader.getInstance().downloadAndDraw(urls.get(4), image5, null);


    }
}