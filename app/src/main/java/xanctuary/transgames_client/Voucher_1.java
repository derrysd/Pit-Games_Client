package xanctuary.transgames_client;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Voucher_1 extends AppCompatActivity {
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voucher_1);

    }

    public void onCartClicked(View view) {

        // custom dialog
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.cart_dialog);
        dialog.setTitle("Cart");



        Button dialogButton = (Button) dialog.findViewById(R.id.BtnCartKembali);
        // if button is clicked, close the custom dialog
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
