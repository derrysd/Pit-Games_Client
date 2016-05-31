package xanctuary.transgames_client;

import android.content.Intent;
import android.os.Bundle;

import com.github.fcannizzaro.materialstepper.AbstractStep;
import com.github.fcannizzaro.materialstepper.style.TabStepper;

import java.util.ArrayList;
import java.util.List;

public class VoucherActivity2 extends TabStepper {

    private int i = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        boolean linear = getIntent().getBooleanExtra("linear", false);

        setErrorTimeout(1500);
        setLinear(linear);
        setTitle("VOUCHER");
        setDisabledTouch();
        setPreviousVisible();

        List<AbstractStep> stepList = new ArrayList<>();

        stepList.add(createFragment(new CartFragment2()));
        stepList.add(createFragment(new IsiDataFragment2()));
        stepList.add(createFragment(new KonfirmasiFragment2()));

        addSteps(stepList);
        super.onCreate(savedInstanceState);

    }



    private AbstractStep createFragment(AbstractStep fragment) {
        Bundle b = new Bundle();
        b.putInt("position", i++);
        fragment.setArguments(b);
        return fragment;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        
    }

    @Override
    public void onComplete() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    @Override
    public void onPrevious() {
//        if (mSteps.current() > 1) {
//           onError();
//        } else {
//            super.onPrevious();
//        }
        super.onPrevious();
    }

}
