package xanctuary.transgames_client;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import fr.tvbarthel.lib.blurdialogfragment.BlurDialogFragment;

public class BlurDialog extends BlurDialogFragment {

    /**
     * Bundle key used to start the blur dialog with a given scale factor (float).
     */
    private static final String BUNDLE_KEY_DOWN_SCALE_FACTOR = "bundle_key_down_scale_factor";

    /**
     * Bundle key used to start the blur dialog with a given blur radius (int).
     */
    private static final String BUNDLE_KEY_BLUR_RADIUS = "bundle_key_blur_radius";

    /**
     * Bundle key used to start the blur dialog with a given dimming effect policy.
     */
    private static final String BUNDLE_KEY_DIMMING = "bundle_key_dimming_effect";

    /**
     * Bundle key used to start the blur dialog with a given debug policy.
     */
    private static final String BUNDLE_KEY_DEBUG = "bundle_key_debug_effect";


    private String CLIENT_ID = "uOHK8UViIetuTCiH27aZ6ZZaA";
    private String CLIENT_SECRET ="l9IbzrwyFUHCN9w5OT7V546y9Tiz14SN7DZznEnSLW71x6A8JS";
    private String AUTH_URL = "https://api.twitter.com/oauth2/token";
    private String AUTH_BODY = "grant_type=client_credentials";
    private String REVOKE_URL = "https://api.twitter.com/oauth2/invalidate_token";
    private String REVOKE_BODY = "access_token=";
    private String CONTENT_TYPE = "application/x-www-form-urlencoded;charset=UTF-8";

    public static final String TAG_PREFERENCES= "App Preferences";
    public static final String TAG_TOKEN = "Token";

    private int mRadius;
    private float mDownScaleFactor;
    private boolean mDimming;
    private boolean mDebug;
    private SharedPreferences sharedPreferences;

    private TextView textView;
    private String textToken;
    private Button revoke;
    private Button request;
    private Button next;
    private View mview;

    public static BlurDialog newInstance() {
        BlurDialog fragment = new BlurDialog();
        Bundle args = new Bundle();
        args.putInt(
                BUNDLE_KEY_BLUR_RADIUS,
                8
        );
        args.putFloat(
                BUNDLE_KEY_DOWN_SCALE_FACTOR,
                4.0f
        );
        args.putBoolean(
                BUNDLE_KEY_DIMMING,
                false
        );
        args.putBoolean(
                BUNDLE_KEY_DEBUG,
                false
        );

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        Bundle args = getArguments();
        mRadius = args.getInt(BUNDLE_KEY_BLUR_RADIUS);
        mDownScaleFactor = args.getFloat(BUNDLE_KEY_DOWN_SCALE_FACTOR);
        mDimming = args.getBoolean(BUNDLE_KEY_DIMMING);
        mDebug = args.getBoolean(BUNDLE_KEY_DEBUG);
        sharedPreferences = getActivity().getSharedPreferences(TAG_PREFERENCES, Context.MODE_PRIVATE);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        mview = getActivity().getLayoutInflater().inflate(R.layout.dialog_token, null);
        textView = (TextView) mview.findViewById(R.id.tokenText);
        textToken = sharedPreferences.getString(TAG_TOKEN, null);

        revoke = (Button) mview.findViewById(R.id.revoke);
        request = (Button) mview.findViewById(R.id.request);
        next = (Button) mview.findViewById(R.id.next);

        if(textToken == null){
            textView.setText("Silahkan Request Token");
            request.setEnabled(true);
            revoke.setEnabled(false);
            next.setEnabled(false);
        } else {
            textView.setText(textToken);
            request.setEnabled(false);
            revoke.setEnabled(true);
            next.setEnabled(true);
        }


        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new PostClass(mview, getActivity())
                        .execute(AUTH_URL, CLIENT_ID, CLIENT_SECRET, AUTH_BODY,
                        CONTENT_TYPE, TAG_PREFERENCES, TAG_TOKEN);
//                try{
//                    progressDialog.show();
//                    String clientId = URLEncoder.encode(CLIENT_ID, "UTF-8");
//                    String clientSecret = URLEncoder.encode(CLIENT_SECRET, "UTF-8");
//                    String concatedString = clientId + ":" + clientSecret;
//                    String basicAuthString = "Basic " + Base64.encodeToString(concatedString.getBytes(), Base64.NO_WRAP);
//
//                    HttpAgent
//                            .post(AUTH_URL)
//                            .queryParams("Authorization", basicAuthString ,"Content-Type", CONTENT_TYPE)
//                            .withBody(AUTH_BODY)
//                            .goJson(new JsonCallback() {
//                                @Override
//                                protected void onDone(boolean success, JSONObject jsonResults) {
//                                    if(success){
//                                        SharedPreferences.Editor editor = getActivity().getSharedPreferences(TAG_PREFERENCES, Context.MODE_PRIVATE).edit();
//                                        String tokenJson = null;
//                                        try {
//                                            tokenJson = jsonResults.getString("access_token");
//                                        } catch (JSONException e) {
//                                            e.printStackTrace();
//                                        }
//                                        editor.putString(TAG_TOKEN, tokenJson);
//                                        editor.apply();
//
//                                        textToken = tokenJson;
//                                        textView.setText(textToken);
//                                        request.setEnabled(false);
//                                        revoke.setEnabled(true);
//                                        next.setEnabled(true);
//                                        progressDialog.dismiss();
//                                    } else{
//                                        Toast.makeText(getActivity().getApplicationContext(),
//                                                getResponseCode() + getErrorMessage() + getStringResults(),
//                                                Toast.LENGTH_LONG).show();
//                                        progressDialog.dismiss();
//                                    }
//                                }
//                            });
//                } catch (UnsupportedEncodingException e) {
//                    e.printStackTrace();
//                }
            }
        });

        revoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new PostClass(mview, getActivity())
                        .execute(REVOKE_URL, CLIENT_ID, CLIENT_SECRET, REVOKE_BODY + textToken,
                                CONTENT_TYPE, TAG_PREFERENCES, TAG_TOKEN);
//                try{
//                    progressDialog.show();
//                    String clientId = URLEncoder.encode(CLIENT_ID, "UTF-8");
//                    String clientSecret = URLEncoder.encode(CLIENT_SECRET, "UTF-8");
//                    String concatedString = clientId + ":" + clientSecret;
//                    String basicAuthString = "Basic " + Base64.encodeToString(concatedString.getBytes(), Base64.NO_WRAP);
//
//                    HttpAgent
//                            .post(REVOKE_URL)
//                            .contentType(CONTENT_TYPE)
//                            .queryParams("Authorization",basicAuthString)
//                            .withBody(REVOKE_BODY + sharedPreferences.getString(TAG_TOKEN, null) )
//                            .goJson(new JsonCallback() {
//                                @Override
//                                protected void onDone(boolean success, JSONObject jsonResults) {
//                                    if(success){
//                                        SharedPreferences.Editor editor = sharedPreferences.edit();
//                                        editor.remove(TAG_TOKEN);
//                                        editor.apply();
//
//                                        Toast.makeText(getActivity().getApplicationContext(),
//                                               "Token Sudah di Revoke",
//                                                Toast.LENGTH_SHORT).show();
//
//                                        textToken = "Silahkan Request Token";
//                                        textView.setText(textToken);
//                                        request.setEnabled(true);
//                                        revoke.setEnabled(false);
//                                        next.setEnabled(false);
//                                        progressDialog.dismiss();
//                                    } else{
//                                        Toast.makeText(getActivity().getApplicationContext(),
//                                                getResponseCode() + getErrorMessage() + getStringResults(),
//                                                Toast.LENGTH_SHORT).show();
//                                        progressDialog.dismiss();
//                                    }
//                                }
//                            });
//                } catch (UnsupportedEncodingException e) {
//                    e.printStackTrace();
//                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        builder.setView(mview);
        return builder.create();
    }

    @Override
    protected boolean isDebugEnable() {
        return mDebug;
    }

    @Override
    protected boolean isDimmingEnable() {
        return mDimming;
    }

    @Override
    protected boolean isActionBarBlurred() {
        return true;
    }

    @Override
    protected float getDownScaleFactor() {
        return mDownScaleFactor;
    }

    @Override
    protected int getBlurRadius() {
        return mRadius;
    }



}