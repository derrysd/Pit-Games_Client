package xanctuary.transgames_client;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    public void onSplash1(View view) {
        Intent i = new Intent(this, SplashActivity.class);
        startActivity(i);
    }

    public void onSplash2(View view) {
        Intent i = new Intent(this,SplashActivity2.class);
        startActivity(i);
    }
}
