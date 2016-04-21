package xanctuary.transgames_client;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Event_2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_2);
    }

    public void onBtnEvent2Click(View view) {
        Intent a = new Intent(this, Event_3.class);
        startActivity(a);
    }
}
