package xanctuary.transgames_client;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

public class PostClass extends AsyncTask<String,Void,String> {
    private ProgressDialog progressDialog;
    View view;
    TextView textView;
    Button next;
    Button request;
    Button revoke;
    int responseCode;
    Activity activity;

    public PostClass(View view, Activity activity) {
        this.view = view;
        progressDialog = new ProgressDialog(view.getContext());
        textView = (TextView) view.findViewById(R.id.tokenText);
        next = (Button)view.findViewById(R.id.next);
        request = (Button)view.findViewById(R.id.request);
        revoke = (Button)view.findViewById(R.id.revoke);
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        progressDialog.setMessage("Loading...");
        progressDialog.show();
    }

    @Override
    protected String doInBackground(String... params) {
        String result = "";
        String bodyHTTP = params[3];
        HttpsURLConnection conn = null;

        try{
            URL url = new URL(params[0]);

            String clientId = URLEncoder.encode(params[1], "UTF-8");
            result +="LOGG Client ID " + clientId + "\n";
            String clientSecret = URLEncoder.encode(params[2], "UTF-8");
            result +="LOGG Client Secret " + clientSecret + "\n";

            String concatedString = clientId + ":" + clientSecret;
            result +="LOGG Concatenated " + concatedString + "\n";
            String basicAuthString = "Basic " + Base64.encodeToString(concatedString.getBytes(), Base64.NO_WRAP);
            result +="LOGG " + basicAuthString + "\n";

            conn = (HttpsURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /*milliseconds*/);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", basicAuthString);
            conn.setRequestProperty("Content-Type", params[4]);

            conn.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
            wr.writeBytes(bodyHTTP);
            wr.flush();
            wr.close();

            responseCode = conn.getResponseCode();
            System.out.println("POST Response Code :: " + responseCode);

            if (responseCode == HttpsURLConnection.HTTP_OK) { //success
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // print result
                result = response.toString();
                System.out.println(response.toString());


                SharedPreferences.Editor editor = activity.getSharedPreferences(params[5], Context.MODE_PRIVATE).edit();
                if(params[3].equals("grant_type=client_credentials")){
                    JSONObject jsonObject = new JSONObject(result);
                    String tokenJson = jsonObject.getString("access_token");
                    editor.putString(params[6], tokenJson);
                    result = tokenJson;
                } else {
                    editor.remove(params[6]);
                    result = "Token Revoked";
                }
                editor.apply();

            } else {
                System.out.println("POST request not worked");
                result = String.valueOf(responseCode) +" : " + conn.getErrorStream().toString();
            }


        } catch (IOException | JSONException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        progressDialog.dismiss();
        System.out.println(s);
        textView.setText(s);
        Toast.makeText(activity.getApplicationContext(), s, Toast.LENGTH_LONG).show();

        if (s.equals("Token Revoked")){
            request.setEnabled(true);
            revoke.setEnabled(false);
            next.setEnabled(false);
        } else {
            request.setEnabled(false);
            revoke.setEnabled(true);
            next.setEnabled(true);
        }
    }
}