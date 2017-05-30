package com.example.hajali.bigimageopenerdemo;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.github.piasy.biv.BigImageViewer;
import com.github.piasy.biv.indicator.progresspie.ProgressPieIndicator;
import com.github.piasy.biv.loader.glide.GlideImageLoader;
import com.github.piasy.biv.view.BigImageView;
import com.github.piasy.biv.view.ImageSaveCallback;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BigImageViewer.initialize(GlideImageLoader.with(getBaseContext()));

        setContentView(R.layout.activity_main);

        String url = "https://onbranch.com/images/fd-objects/branch-floorplan-f2f-ab9ae769d5470d2b70f458f379438502-165-1.jpg";

        BigImageView bigImageView = (BigImageView) findViewById(R.id.mBigImage);
        bigImageView.showImage(Uri.parse(url));

// you can show thumbnail before the big image is loaded
//        bigImageView.showImage(Uri.parse(thumbnail), Uri.parse(url));
        bigImageView.setProgressIndicator(new ProgressPieIndicator());

        bigImageView.getSSIV().setZoomEnabled(false);
        bigImageView.getSSIV().setMinScale(4);

        bigImageView.setImageSaveCallback(new ImageSaveCallback() {
            @Override
            public void onSuccess(String uri) {
                Toast.makeText(getBaseContext(),
                        "Success",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFail(Throwable t) {
                t.printStackTrace();
                Toast.makeText(getBaseContext(),
                        "Fail",
                        Toast.LENGTH_SHORT).show();
            }
        });

        LinearLayout tempEmptySeat = new LinearLayout(this);
        tempEmptySeat.setBackgroundColor(ResourcesCompat.getColor(getResources(),R.color.colorPrimary, null));

        int size = 100;

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(size, size);

        layoutParams.leftMargin = 100;
        layoutParams.topMargin = 100;

        RelativeLayout layout = (RelativeLayout) findViewById(R.id.relative);

        layout.addView(tempEmptySeat, layoutParams);

//        bigImageView.saveImageIntoGallery();

    }
}
