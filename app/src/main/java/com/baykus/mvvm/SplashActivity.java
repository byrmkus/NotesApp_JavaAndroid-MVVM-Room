package com.baykus.mvvm;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                //--------------giriş animasyonumuzu oluşturuyoruz--------------------------
                LinearLayout view = new LinearLayout(SplashActivity.this);
                ImageView icon = new ImageView(SplashActivity.this);
                view.setGravity(Gravity.CENTER);
                view.setBackgroundColor(getResources().getColor(R.color.white));
                icon.setImageResource(R.drawable.note_sprash);

                //iconun genişlik ve yükseklik özelliğini  250 olarak veriyoruz ve view nesnesine ekliyoruz
                view.addView(icon, 250, 250);
                getWindow().addContentView(view, new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));


                Animation scaleAnim = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.splash_anim);
                icon.clearAnimation();
                icon.startAnimation(scaleAnim);

                scaleAnim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "alpha", 1.0f, 0.0f);
                        animator.setDuration(300);
                        animator.setInterpolator(new LinearInterpolator());
                        animator.start();
                        animator.addListener(new Animator.AnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animator animation) {
                                //animasyon bittikten sonra görünürlük özelliği GONE olacak yani hiç orada yokmuş gibi olacak
                                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                                finish();
                                view.setVisibility(View.GONE);

                            }

                            @Override
                            public void onAnimationCancel(Animator animation) {

                            }

                            @Override
                            public void onAnimationRepeat(Animator animation) {

                            }
                        });

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                //-----------Animasyon işlemlerinin sonu-----------------


            }
        },1);


    }
}