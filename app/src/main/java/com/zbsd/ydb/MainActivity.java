package com.zbsd.ydb;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.lib.imageCompress.CompressBuilder;
import com.lib.imageCompress.PictureCompress;
import com.lib.imageCompress.listenter.OnCompressListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnCompressListener {

    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        checkPermission();

        // Example of a call to a native method
        TextView tv = (TextView) findViewById(R.id.sample_text);
        tv.setText("我很好");

        AppCompatButton btn = (AppCompatButton) findViewById(R.id.btn_compress);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //compressTest();
                compressTest01();
            }
        });
    }


    private void compressTest() {
        // String filePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath()
        //       + "/bili/boxing/1500533383019.jpg";

        String filePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath()
                + "/Camera/IMG_20170908_163325.jpg";

        File file = new File(filePath);

        String hfsavePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + "/hfresult.jpg";
        String savePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + "/result.jpg";

        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
        Log.d("TAG", "---------hf-----消耗时间开始--------");
       // NativeCompress.nativeCompressBitmapWithWidthAndHeight(bitmap, 1440, 2280, 20, hfsavePath, true);
        Log.d("TAG", "---------hf-----消耗时间结束--------");

        try {
            Log.d("TAG", "---------正常-----消耗时间开始--------");
            bitmap.compress(Bitmap.CompressFormat.JPEG, 20, new FileOutputStream(savePath));
            Log.d("TAG", "---------正常-----消耗时间开始--------");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }


    private void compressTest01() {
        String filePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath()
                + "/bili/boxing/1500533383019.jpg";

        String filePath01 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath()
                + "/Camera/20160602_061812.jpg";

        String filePath02 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath()
                + "/Camera/20160602_130025.jpg";

        String filePath03 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath()
                + "/Camera/20160603_141433.jpg";

        String path01 = "/storage/emulated/0/libtagnetwork.log";

        String path02 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath()
                + "/bili/boxing/1505209428894.jpg";


        List<String> paths = new ArrayList<>();
        paths.add(filePath);
        paths.add(filePath01);
        paths.add(filePath02);
        paths.add(filePath03);

      CompressBuilder compressBuilder = new CompressBuilder(this)
                .setCompressListener(this)
                .load(filePath);
        new PictureCompress(compressBuilder).launch();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    /**
     * 6.0 权限申请
     */
    private void checkPermission() {
        if (checkCallingOrSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission
                    .WRITE_EXTERNAL_STORAGE}, 100);
        } else {

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 100) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            }
        }
    }


    @Override
    public void onCompressStart() {
        Log.e(TAG, "onCompressStart: 开始加载");
    }

    @Override
    public void onCompressSuccess(String filePath) {
        Log.d(TAG, "onCompressSuccess: 压缩成功；；；地址"+filePath);
    }

    @Override
    public void onCompressError(Throwable e) {
        Log.e(TAG, "onError: 错误");
    }
}
