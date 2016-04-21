package xanctuary.transgames_client;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class PageFragment extends Fragment {

    private int imageResource;
    private Bitmap bitmap;


    public static PageFragment getInstance(int resourceID) {
        PageFragment f = new PageFragment();
        Bundle args = new Bundle();
        args.putInt("image_source", resourceID);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imageResource = getArguments().getInt("image_source");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_page, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView imageView = (ImageView) view.findViewById(R.id.image);
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inSampleSize = 1;
        o.inDither = false;
        bitmap = BitmapFactory.decodeResource(getResources(), imageResource, o);
        imageView.setImageBitmap(bitmap);
        imageView.setOnClickListener(onClickImageZoom());

    }

    private View.OnClickListener onClickImageZoom() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.isZoomed = true;

                Intent intent = new Intent(view.getContext(), ZoomImage.class);
                intent.putExtra("image_source", imageResource);
                startActivity(intent);
            }
        };
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bitmap.recycle();
        bitmap = null;
    }
}

