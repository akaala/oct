package com.example.myapp;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.myapp.task.LoadImageTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class CustomActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_detail);
        TextView label = (TextView) findViewById(R.id.show_data);

        String name = getIntent().getExtras().getString("name");
        String intro = getIntent().getExtras().getString("intro");
        String[] picUrls = getIntent().getExtras().getStringArray("picUrls");

        label.setText(name + ":" + intro);

        ImageView imView = (ImageView) findViewById(R.id.place_image1);

        if (picUrls.length >= 2) {
            new LoadImageTask(imView).execute( picUrls[0]);
//            imView.setImageBitmap(returnBitMap(picUrls[0]));
        }
    }

    private Bitmap returnBitMap(String url){
        URL myFileUrl = null;
        Bitmap bitmap = null;
        try {
            myFileUrl = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            HttpURLConnection conn = (HttpURLConnection) myFileUrl
                    .openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    public void doBook(View view) {

    }
}
