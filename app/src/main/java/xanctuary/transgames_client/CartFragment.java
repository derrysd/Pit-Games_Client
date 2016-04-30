package xanctuary.transgames_client;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.Gson;

import java.util.ArrayList;

import ivb.com.materialstepper.stepperFragment;

public class CartFragment extends stepperFragment {
    TableLayout tableLayout;
    String price = "";

    public CartFragment() {
        // Required empty public constructor
    }

    @Override
    public boolean onNextButtonHandler() {
        return tableLayout.getChildCount() > 1;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        Button tambah = (Button) view.findViewById(R.id.tambah);
        tableLayout = (TableLayout) view.findViewById(R.id.table_layout);

        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                boolean wrapInScrollView = true;
                final Spinner spinnerJenis, spinnerNominal;
                final TextView infoHarga, infoStok;

                SharedPreferences sharedPreferences = view.getContext().getSharedPreferences("mPrefs", Context.MODE_PRIVATE);
                String json  = sharedPreferences.getString("MyJson","");
                Gson gson = new Gson();
                final ResourceVoucher[] resourceVoucher = gson.fromJson(json, ResourceVoucher[].class);
                ArrayList<String> namaVoucher = new ArrayList<>();
                namaVoucher.add("PILIH JENIS VOUCHER");
                for (ResourceVoucher resource: resourceVoucher){
                    namaVoucher.add(resource.getTitle());
                }

                final MaterialDialog materialDialog = new MaterialDialog.Builder(view.getContext())
                        .title("Tambah Barang")
                        .positiveText("Tambah")
                        .customView(R.layout.custom_dialog, wrapInScrollView)
                        .show();

                spinnerJenis = (Spinner) materialDialog.findViewById(R.id.spinner_jenis);
                spinnerNominal = (Spinner) materialDialog.findViewById(R.id.spinner_nominal);
                infoHarga = (TextView) materialDialog.findViewById(R.id.info_harga);
                infoStok = (TextView) materialDialog.findViewById(R.id.info_stok);
                final ArrayAdapter<String> adapterJenis = new ArrayAdapter<>(
                        view.getContext(), android.R.layout.simple_spinner_item, namaVoucher);
                adapterJenis.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerJenis.setAdapter(adapterJenis);
                View positive = materialDialog.getActionButton(DialogAction.POSITIVE);
                positive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(spinnerJenis.getSelectedItemPosition() > 0 && spinnerNominal.getSelectedItemPosition() > 0){
                            TableRow.LayoutParams rowLayoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
                            TableLayout.LayoutParams tableLayoutParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
                            TableRow.LayoutParams textViewLayParams = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
                            textViewLayParams.setMargins(1,1,1,1);

                            TableRow tableRow = new TableRow(view.getRootView().getContext());
                            tableRow.setLayoutParams(rowLayoutParams);

                            TextView txNo = new TextView(view.getRootView().getContext());
                            TextView txBarang = new TextView(view.getRootView().getContext());
                            TextView txNominal = new TextView(view.getRootView().getContext());
                            TextView txHarga = new TextView(view.getRootView().getContext());


                            txNo.setText(String.valueOf(tableLayout.getChildCount()));
                            txBarang.setText(spinnerJenis.getSelectedItem().toString());
                            txHarga.setText(price);
                            txNominal.setText(spinnerNominal.getSelectedItem().toString());

                            txNo.setLayoutParams(textViewLayParams);
                            txBarang.setLayoutParams(textViewLayParams);
                            txHarga.setLayoutParams(textViewLayParams);
                            txNominal.setLayoutParams(textViewLayParams);

                            txNo.setPadding(1,1,1,1);
                            txBarang.setPadding(1,1,1,1);
                            txHarga.setPadding(1,1,1,1);
                            txNominal.setPadding(1,1,1,1);

                            txNo.setBackgroundColor(Color.WHITE);
                            txBarang.setBackgroundColor(Color.WHITE);
                            txHarga.setBackgroundColor(Color.WHITE);
                            txNominal.setBackgroundColor(Color.WHITE);

                            txNo.setGravity(Gravity.CENTER);
                            txBarang.setGravity(Gravity.CENTER);
                            txHarga.setGravity(Gravity.CENTER);
                            txNominal.setGravity(Gravity.CENTER);

                            tableRow.addView(txNo);
                            tableRow.addView(txBarang);
                            tableRow.addView(txHarga);
                            tableRow.addView(txNominal);

                            tableLayout.addView(tableRow,tableLayoutParams);
                            materialDialog.dismiss();
                        }

                        Snackbar.make(view.getRootView(), "Pilih Jenis Voucher & Nominal Voucher", Snackbar.LENGTH_SHORT).show();
                    }
                });

                spinnerJenis.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, final int i, long l) {
                        if (i>0){
                            ArrayList<String> nominalVoucher = resourceVoucher[i-1].getListNominal();
                            ArrayAdapter<String> adapterNominal = new ArrayAdapter<>(
                                    view.getContext(), android.R.layout.simple_spinner_item, nominalVoucher);
                            adapterNominal.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinnerNominal.setAdapter(adapterNominal);

                            spinnerNominal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> adapterView, View view, int j, long m) {
                                    if(j>0){
                                        ArrayList<String> harga = resourceVoucher[i-1].getListPrice();
                                        ArrayList<String> stok = resourceVoucher[i-1].getListStock();
                                        infoHarga.setText("Harga : " + String.valueOf(harga.get(j-1)));
                                        infoStok.setText("Stok : " + String.valueOf(stok.get(j-1)));

                                        price = harga.get(j-1);
                                    }
                                    else {
                                        infoHarga.setText("Harga : ");
                                        infoStok.setText("Stok : ");
                                    }
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> adapterView) {

                                }
                            });
                        }

                        else {
                            infoHarga.setText("Harga : ");
                            infoStok.setText("Stok : ");
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

            }
        });

        return view;
    }

}
