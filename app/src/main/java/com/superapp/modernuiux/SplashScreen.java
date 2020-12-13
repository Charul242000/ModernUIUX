package com.superapp.modernuiux;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class SplashScreen extends AppCompatActivity {
    //variables
    Animation topAnim,bottomAnim;
    CircleImageView CIV;
    TextView splashtv1,splashtv2;
    //splash
    private  static int SPLASH_SCREEN=5000; //in milisec


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_screen);
        //Animations
        topAnim= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim=AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        CIV=findViewById(R.id.CIV);
        splashtv1=findViewById(R.id.splashtv1);
        splashtv2=findViewById(R.id.splashtv2);

        CIV.setAnimation(topAnim);
        splashtv1.setAnimation(bottomAnim);
        splashtv2.setAnimation(bottomAnim);

        //splash
         new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, Login.class);
                Pair[] pairs=new Pair[2];
                pairs[0] =new Pair<View,String>(CIV,"logoimage");
                pairs[1] =new Pair<View,String>(splashtv1,"logintv1");
                ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(SplashScreen.this,pairs);
                startActivity(intent,options.toBundle());
                finish();
            }
        },SPLASH_SCREEN);


    }
}
