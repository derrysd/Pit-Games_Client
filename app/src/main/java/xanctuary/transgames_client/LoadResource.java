package xanctuary.transgames_client;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoadResource extends AsyncTask<Context, String , String> {

    // JSON Node names
    private static final String TAG_RESOURCE_VOUCHER = "resource_voucher";
    private static final String TAG_NAMA_VOUCHER = "nama_voucher";
    private static final String TAG_NOMINAL_VOUCHER = "nominal_voucher";
    private static final String TAG_HARGA_VOUCHER = "harga_voucher";

    Context context;
    HttpURLConnection urlConnection;

    public LoadResource(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Context... args) {
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL("https://api.myjson.com/bins/2g3xw");
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());

            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

        }catch( Exception e) {
            e.printStackTrace();
        }
        finally {
            urlConnection.disconnect();
        }
        return result.toString();
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        saveSharedPreferencesLogList(context, s);
    }

    public static void saveSharedPreferencesLogList(Context context, String json) {
       if (json != null){
           SharedPreferences mPrefs = context.getSharedPreferences("mPrefs", Context.MODE_PRIVATE);
           SharedPreferences.Editor prefsEditor = mPrefs.edit();
           prefsEditor.putString("MyJson", json);
           prefsEditor.apply();
       //    Toast.makeText(context, "Object stored in SharedPreferences", Toast.LENGTH_SHORT).show();
       }
   //    else Toast.makeText(context, "Object FAILED to store", Toast.LENGTH_LONG).show();
    }
}