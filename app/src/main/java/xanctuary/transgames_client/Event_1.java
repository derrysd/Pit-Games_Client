package xanctuary.transgames_client;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Event_1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_1);
    }

    public void onBtnEvent1Clicked(View view) {
        Intent a = new Intent(this, Event_2.class);
        startActivity(a);
    }
}
