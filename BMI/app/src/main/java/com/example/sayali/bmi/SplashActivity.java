package com.example.sayali.bmi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;


public class SplashActivity extends AppCompatActivity {

    ImageView ivBmi;
    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ivBmi=findViewById(R.id.ivBmi);
        animation=AnimationUtils.loadAnimation(this,R.anim.a1);
        ivBmi.startAnimation(animation);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
                Intent a=new Intent(SplashActivity.this,MainActivity.class);
                startActivity(a);
                finish();
            }
        }).start();
    }
}
