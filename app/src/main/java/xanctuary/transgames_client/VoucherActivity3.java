package xanctuary.transgames_client;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import ivb.com.materialstepper.progressMobileStepper;

public class VoucherActivity3 extends progressMobileStepper {

    @Override
    public void onStepperCompleted() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    @Override
    public List<Class> init() {

        List<Class> stepperFragmentList = new ArrayList<>();
        stepperFragmentList.add(CartFragment.class);
        stepperFragmentList.add(IsiDataFragment.class);
        stepperFragmentList.add(KonfirmasiFragment.class);
        return stepperFragmentList;
    }
}
