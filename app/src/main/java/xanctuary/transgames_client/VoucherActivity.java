package xanctuary.transgames_client;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;

import me.drozdzynski.library.steppers.OnCancelAction;
import me.drozdzynski.library.steppers.OnFinishAction;
import me.drozdzynski.library.steppers.SteppersItem;
import me.drozdzynski.library.steppers.SteppersView;

public class VoucherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voucher);

        SteppersView.Config steppersViewConfig = new SteppersView.Config();
        steppersViewConfig.setOnFinishAction(new OnFinishAction() {
            @Override
            public void onFinish() {
                // Action on last step Finish button
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });

        steppersViewConfig.setOnCancelAction(new OnCancelAction() {
            @Override
            public void onCancel() {
                // Action when click cancel on one of steps
                new MaterialDialog.Builder(VoucherActivity.this)
                        .title("Batal")
                        .content("Yakin Ingin Batal Pemesanan?")
                        .positiveText("Ya")
                        .negativeText("Tidak")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                Intent intent = new Intent(getApplicationContext(), VoucherActivity.class);
                                startActivity(intent);
                                dialog.dismiss();
                                finish();
                            }
                        })
                        .onNegative(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                dialog.dismiss();
                            }
                        })
                        .show();

            }
        });

        // Setup Support Fragment Manager for fragments in steps
        steppersViewConfig.setFragmentManager(getSupportFragmentManager());

        ArrayList<SteppersItem> steps = new ArrayList<>();

        SteppersItem stepOne = new SteppersItem();

        stepOne.setLabel("LANGKAH 1");
        stepOne.setSubLabel("Pilih Voucher & Pesan");
        stepOne.setFragment(new CartFragment2());
        stepOne.setPositiveButtonEnable(true);

        steps.add(stepOne);

        SteppersItem stepTwo = new SteppersItem();

        stepTwo.setLabel("LANGKAH 2");
        stepTwo.setSubLabel("Isi Data Lengkap Anda");
        stepTwo.setFragment(new IsiDataFragment2());
        stepTwo.setPositiveButtonEnable(true);

        steps.add(stepTwo);

        SteppersItem stepThree = new SteppersItem();

        stepThree.setLabel("LANGKAH 3");
        stepThree.setSubLabel("Konfirmasi Pemesanan Anda");
        stepThree.setFragment(new KonfirmasiFragment2());
        stepThree.setPositiveButtonEnable(true);

        steps.add(stepThree);

        SteppersView steppersView = (SteppersView) findViewById(R.id.steppersView);
        steppersView.setConfig(steppersViewConfig);
        steppersView.setItems(steps);
        steppersView.build();
    }

}
