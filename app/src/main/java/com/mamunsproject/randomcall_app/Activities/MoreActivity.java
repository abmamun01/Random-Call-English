package com.mamunsproject.randomcall_app.Activities;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.mamunsproject.randomcall_app.R;

import org.jetbrains.annotations.NotNull;

public class MoreActivity extends AppCompatActivity {
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        setTextColor(findViewById(R.id.report));
        setTextColor(findViewById(R.id.privacy));
        setTextColor(findViewById(R.id.contactus));
        setTextColor(findViewById(R.id.rateus));
        setTextColor(findViewById(R.id.invitefirend));
        setTextColor(findViewById(R.id.moreapp));

        admobAds();

        findViewById(R.id.report).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MoreActivity.this,"Report Submitted",Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.rateus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rateUs();
            }
        });

        findViewById(R.id.privacy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://www.freeprivacypolicy.com/privacy/view/c9d3a59f9f33d16a4ffe0ce777f6638c");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        findViewById(R.id.contactus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Uri uri = Uri.parse("https://www.facebook.com/strangerenglishcall");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        findViewById(R.id.invitefirend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Hey ! I am using English call App. It's free and very good for English Speaking practice. If you want to learn English. Download now from here http://play.google.com/store/apps/details?id="
                );
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });
        findViewById(R.id.moreapp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rateUs();
            }
        });



    }

    private void admobAds() {
        MobileAds.initialize(this,
                new OnInitializationCompleteListener() {
                    @Override
                    public void onInitializationComplete(@NonNull @NotNull InitializationStatus initializationStatus) {

                    }
                });

        mAdView = findViewById(R.id.adView1);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    private void rateUs() {
        Uri uri = Uri.parse("market://details?id=" + this.getPackageName());
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        // To count with Play market backstack, After pressing back button,
        // to taken back to our application, we need to add following flags to intent.
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        try {
            startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + this.getPackageName())));
        }
    }

    public void setTextColor(TextView textView)
    {

        Shader textShader=new LinearGradient(0, 0, 0, 90,
                new int[]{getResources().getColor(R.color.backgroundstart),getResources().getColor(R.color.backgroundend)},
                new float[]{0, 1}, Shader.TileMode.REPEAT);
        textView.getPaint().setShader(textShader);
    }
}
