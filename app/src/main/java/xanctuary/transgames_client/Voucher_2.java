package xanctuary.transgames_client;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Voucher_2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voucher_2);
    }

    public void onBtnVoucher2Click(View view) {
        Intent a = new Intent(this, Voucher_3.class);
        startActivity(a);
    }
}
