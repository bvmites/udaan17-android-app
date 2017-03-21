package in.udaan17.android.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.jsibbold.zoomage.ZoomageView;

import java.io.File;
import java.io.FileOutputStream;

import in.udaan17.android.R;

public class ImageActivity extends AppCompatActivity {

    ZoomageView imageView;

    private Toolbar toolbar;
    private AppCompatImageButton shareButton;
    private int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        imageView = (ZoomageView) findViewById(R.id.myZoomageView);
        toolbar = (Toolbar) findViewById(R.id.activity_image_toolbar);
        shareButton = (AppCompatImageButton) findViewById(R.id.activity_image_share);

        this.setSupportActionBar(toolbar);
        ActionBar actionBar = this.getSupportActionBar();

        if (actionBar != null) {
            actionBar.setTitle("Tech Schedule");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        i = this.getIntent().getExtras().getInt("Schedule");

        if (i == 1) {
            imageView.setImageResource(R.drawable.schedule_tech);
        } else {
            imageView.setImageResource(R.drawable.schedule_non_tech);

        }


        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(ImageActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(ImageActivity.this,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {

//                    // Should we show an explanation?
//                    if (ActivityCompat.shouldShowRequestPermissionRationale(ImageActivity.this,
//                            Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
//
//                        // Show an explanation to the user *asynchronously* -- don't block
//                        // this thread waiting for the user's response! After the user
//                        // sees the explanation, try again to request the permission.
//
//                    } else {

                    // No explanation needed, we can request the permission.

                    ActivityCompat.requestPermissions(ImageActivity.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            1);

                    ActivityCompat.requestPermissions(ImageActivity.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            2);

                    // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                    // app-defined int constant. The callback method gets the
                    // result of the request.
                    //}
                } else {
                    imageView.setDrawingCacheEnabled(true);

                    int drawableId;
                    if (ImageActivity.this.i == 1) {
                        drawableId = R.drawable.schedule_tech;
                    } else {
                        drawableId = R.drawable.schedule_non_tech;
                    }
                    Bitmap bitmap = BitmapFactory.decodeResource(ImageActivity.this.getResources(), drawableId);
                    File root = Environment.getExternalStorageDirectory();
                    File cachePath = new File(root.getAbsolutePath() + "/DCIM/Camera/image.jpg");
                    try {
                        cachePath.createNewFile();
                        FileOutputStream ostream = new FileOutputStream(cachePath);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, ostream);
                        ostream.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    Intent share = new Intent(Intent.ACTION_SEND);
                    share.setType("image/*");
                    share.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(cachePath));
                    startActivity(Intent.createChooser(share, "Share via"));
                }
            }

        });


    }


}
