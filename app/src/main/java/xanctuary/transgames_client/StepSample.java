package xanctuary.transgames_client;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.github.fcannizzaro.materialstepper.AbstractStep;

public class StepSample extends AbstractStep {

    private int i = 1;
    private Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.step, container, false);
        button = (Button) v.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button.setText(Html.fromHtml("Tap <b>" + (i++) + "</b>"));
                if (mStepper != null)
                    mStepper.getExtras().putInt("Click", i);
            }
        });

        //TextView tex = new TextView(mStepper.getApplication().getApplicationContext());

        return v;
    }

    @Override
    public void onStepVisible() {
        super.onStepVisible();
    }

    @Override
    public void onPrevious() {

    }

    @Override
    public String name() {
        return "Tab " + getArguments().getInt("position", 0);
    }

    @Override
    public boolean isOptional() {
        return false;
    }

    @Override
    public String optional() {
        return "You cant skip";
    }

    @Override
    public boolean nextIf() {
        return i > 1;
    }

    @Override
    public String error() {
        Toast.makeText(getView().getContext(), "<b>You must click!</b> <small>this is the condition!</small>", Toast.LENGTH_SHORT).show();

        return "<b>You must click!</b> <small>this is the condition!</small>";
    }

}