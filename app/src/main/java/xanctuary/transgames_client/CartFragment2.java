package xanctuary.transgames_client;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.fcannizzaro.materialstepper.AbstractStep;
import com.google.gson.Gson;

import java.util.ArrayList;

public class CartFragment2 extends AbstractStep {
    TableLayout tableLayout;
    String price = "";

    public CartFragment2() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart2, container, false);

        FloatingActionButton tambah = (FloatingActionButton) view.findViewById(R.id.tambah);
        tableLayout = (TableLayout) view.findViewById(R.id.table_layout);

        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                boolean wrapInScrollView = true;
                final Spinner spinnerJenis, spinnerNominal;
                final TextView infoHarga, infoStok;

//                SharedPreferences sharedPreferences = view.getContext().getSharedPreferences("mPrefs", Context.MODE_PRIVATE);
//                String json  = sharedPreferences.getString("MyJson","");
                String json = "[{\"id\":\"7ff4a8b9-e750-43f1-b1b7-e3502e056dc7\",\"title\":\"GEMSCOOL\",\"info\":\"Gemscool Indonesia\",\"available\":\"N\",\"remark\":\"ONTESTING\",\"voucerDetails\":[{\"id\":\"900c7c79-37ee-41dd-b4be-9208b08775af\",\"nominal\":50000,\"price\":52000,\"stock\":50,\"remark\":\"ONTESTING\"},{\"id\":\"cf5f0b9c-9871-4eb6-a8f3-b742925cdcce\",\"nominal\":100000,\"price\":100000,\"stock\":100,\"remark\":\"ONTESTING\"},{\"id\":\"fbbe4e80-dae1-4a28-a6ad-178f5fb0d688\",\"nominal\":20000,\"price\":25000,\"stock\":10,\"remark\":\"ONTESTING\"}]},{\"id\":\"bcd07206-9193-4cd4-acbb-b815c538010b\",\"title\":\"ROG Voucer\",\"info\":\"Republic of Gamer Voucer\",\"available\":\"N\",\"remark\":\"ONTESTING\",\"voucerDetails\":[{\"id\":\"54f4ac47-d6ea-4127-9ee1-ceeb3df9ddbe\",\"nominal\":100000,\"price\":100000,\"stock\":100,\"remark\":\"ONTESTING\"},{\"id\":\"804a4d1c-f733-4f1e-9391-4c6ae579fc3a\",\"nominal\":50000,\"price\":52000,\"stock\":50,\"remark\":\"ONTESTING\"},{\"id\":\"b66c2dd5-207f-41b0-8b82-150a66c859ee\",\"nominal\":20000,\"price\":25000,\"stock\":10,\"remark\":\"ONTESTING\"}]}]";
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

                        Snackbar snackbar = Snackbar.make(view.getRootView(), "Pilih Jenis Voucher & Nominal Voucher", Snackbar.LENGTH_SHORT);
                        View snackView = snackbar.getView();
                        snackView.setBackgroundColor(ContextCompat.getColor(view.getContext(),R.color.colorSnackBar));
                        snackbar.show();
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

    @Override
    public boolean isOptional() {
        return false;
    }

    @Override
    public String optional() {
        return "Pilih Voucher & Pesan";
    }

    @Override
    public String name() {
        return "Langkah " + getArguments().getInt("position", 0);
    }

    @Override
    public boolean nextIf() {
        return tableLayout.getChildCount() > 1;
    }

    @Override
    public String error() {
        return  "<b>Keranjang Kosong!</b> <small> Klik Button Tambah!</small>";
    }
}