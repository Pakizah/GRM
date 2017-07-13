package com.example.romana.grm;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;

public class SplashScreen extends Activity {
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView=(ImageView)findViewById(R.id.imsplash);
        imageView.setBackgroundResource(R.drawable.bgsplash);
        AnimationDrawable animationDrawable= (AnimationDrawable) imageView.getBackground();
        animationDrawable.start();

        Thread timerThread = new Thread(){
            public void run(){
                try
                {
                    sleep(2000);
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    Intent intent = new Intent(SplashScreen.this,BitmapFactory.Options.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        timerThread.start();
    }
    }

