package xanctuary.transgames_client;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

public class ZoomImage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindowManager();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        int imageResource = getIntent().getIntExtra("image_source",0);
        TouchImageView imageZoom = new TouchImageView(this);
        imageZoom.setImageResource(imageResource);
        imageZoom.setMaxZoom(4f);
        setContentView(imageZoom);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MainActivity.isZoomed = false;
    }
}
