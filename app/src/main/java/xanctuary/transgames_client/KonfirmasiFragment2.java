package xanctuary.transgames_client;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.fcannizzaro.materialstepper.AbstractStep;


public class KonfirmasiFragment2 extends AbstractStep {


    public KonfirmasiFragment2() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_konfirmasi2, container, false);
    }

    @Override
    public String name() {
        return  "Langkah " + getArguments().getInt("position", 0);
    }

    @Override
    public boolean isOptional() {
        return false;
    }

    @Override
    public String error() {
        return "<b>Tidak Bisa Kembali!</b> <small> Silahkan tekan complete</small>";
    }
}
