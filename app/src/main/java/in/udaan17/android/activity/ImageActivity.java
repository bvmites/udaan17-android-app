package in.udaan17.android.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;

import in.udaan17.android.R;
import in.udaan17.android.databinding.ActivityImageBinding;

public class ImageActivity extends AppCompatActivity {
  private ActivityImageBinding dataBinding;
  
  private int i;
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_image);
    
    this.setSupportActionBar(this.dataBinding.toolbar);
    ActionBar actionBar = this.getSupportActionBar();
    
    if (actionBar != null) {
      actionBar.setTitle("Tech Schedule");
      actionBar.setDisplayHomeAsUpEnabled(true);
    }
    i = this.getIntent().getExtras().getInt("Schedule");
    
    if (i == 1) {
      this.dataBinding
          .timeTable
          .setImageResource(R.drawable.schedule_tech);
    } else {
      this.dataBinding
          .timeTable
          .setImageResource(R.drawable.schedule_non_tech);
    }
    
    this.dataBinding
        .actionShare
        .setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            
            if (ContextCompat.checkSelfPermission(ImageActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(ImageActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
              
              ActivityCompat.requestPermissions(ImageActivity.this,
                  new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                  1);
              
              ActivityCompat.requestPermissions(ImageActivity.this,
                  new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                  2);
            } else {
              ImageActivity.this
                  .dataBinding
                  .timeTable
                  .setDrawingCacheEnabled(true);
              
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
