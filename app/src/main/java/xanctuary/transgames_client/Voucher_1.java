package xanctuary.transgames_client;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;

public class Voucher_1 extends AppCompatActivity {
    final Context context = this;
    Spinner spinnerJenis, spinnerNominal;
    Dialog dialog;
    int harga = 999999;
    ResourceVoucher vd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voucher_1);

        dialog = new Dialog(context);

        spinnerJenis = (Spinner) findViewById(R.id.jenis);
        spinnerNominal = (Spinner) findViewById(R.id.nominal);

        final SharedPreferences sharedPreferences = this.getSharedPreferences("mPrefs", Context.MODE_PRIVATE);
        String json = sharedPreferences.getString("MyJson", "");
        Gson gson = new Gson();
        final ResourceVoucher[] resourceVoucher = gson.fromJson(json, ResourceVoucher[].class);
        final ArrayList<String> listNamaVoucher = new ArrayList<>();
        listNamaVoucher.add("PILIH JENIS VOUCHER");
        final ArrayList<String> listNominalVoucher = new ArrayList<>();
        listNominalVoucher.add("PILIH NOMINAL");
        for (ResourceVoucher aResourceVoucher : resourceVoucher) {
            listNamaVoucher.add(aResourceVoucher.getTitle());
        }


        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listNamaVoucher);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerJenis.setAdapter(arrayAdapter);
//        spinnerJenis.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                @Override
//                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                  //  i = i-1;
//                    if (i>0) {
//
//                        for (int j = 0; j < listNamaVoucher.size(); j++) {
//
//                            listNominalVoucher.add(String.valueOf(resourceVoucher[i].getVoucherDetails()[j].getNominal()));
//                        }
//                        ArrayAdapter<String> a = new ArrayAdapter<>(adapterView.getContext(), android.R.layout.simple_spinner_item,
//                                listNominalVoucher);
//                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                        spinnerNominal.setAdapter(a);
//
//                        vd = resourceVoucher[i];
//                    }
//                }
//
//                @Override
//                public void onNothingSelected(AdapterView<?> adapterView) {
//
//                }
//        });
//
//        spinnerNominal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                harga = vd.getVoucherDetails()[i].getPrice();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
    }



    public void onCartClicked(View view) {

        // custom dialog
        dialog.setContentView(R.layout.cart_dialog);
        dialog.setTitle("Cart");



        Button dialogButton = (Button) dialog.findViewById(R.id.BtnCartKembali);
        Button dialogButton2 = (Button) dialog.findViewById(R.id.BtnCartSelesai);

        // if button is clicked, close the custom dialog
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialogButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), Voucher_2.class);
                startActivity(i);
            }
        });

        dialog.show();
    }


    public void onBtnVoucher1TambahClicked(View view) {
//        String selectedJenis = spinnerJenis.getSelectedItem().toString();
//        String selectedNominal = spinnerNominal.getSelectedItem().toString();
//
//        TableLayout tab = (TableLayout) dialog.findViewById(R.id.CartTableLay);
//        TableRow tableRow = new TableRow(view.getContext());
//        tableRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
//
//        TextView txJenis = new TextView(view.getContext());
//        txJenis.setText(selectedJenis);
//        txJenis.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
//
//        TextView txNominal = new TextView(view.getContext());
//        txNominal.setText(selectedNominal);
//        txNominal.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
//
//        TextView txHarga= new TextView(view.getContext());
//        txHarga.setText(String.valueOf(harga));
//        txHarga.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
//
//
//        tableRow.addView(txJenis,1);
//        tableRow.addView(txHarga,2);
//        tableRow.addView(txNominal,3);
//
//        tab.addView(tableRow, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));


    }

    public void onBtnVoucher1SelesaiClick(View view) {
        Intent i = new Intent(this, Voucher_2.class);
        startActivity(i);
    }
}
