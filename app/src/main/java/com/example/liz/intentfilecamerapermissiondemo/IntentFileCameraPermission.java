package com.example.liz.intentfilecamerapermissiondemo;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.liz.intentfilecamerapermissiondemo.adapter.RecyclerViewAdapter;

import java.io.File;
import java.util.List;

public class IntentFileCameraPermission extends AppCompatActivity {

    private static final int REQUEST_CODE = 1 ;
    private static final int SPAN_COUNT = 2 ;
    private List<String> mList;
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mRecyclerViewAdapter;
    @SuppressLint("InlinedApi")
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_file_camera_permission);


        //Get image directory
        File imageDirectory = Environment.getExternalStoragePublicDirectory(Environment
                .DIRECTORY_DCIM);
        String[] extensions = getResources().getStringArray(R.array.image_extension);
        if(imageDirectory != null) {
            mList = LoadFile.loadImageFileName(imageDirectory,
                    extensions);
        }

        //Check permission : Read External Storage
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                    Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                if (!ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.READ_EXTERNAL_STORAGE)) {
                } else {
                    ActivityCompat.requestPermissions(
                            this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            REQUEST_CODE);
                }
            }
        }

        //set data into recyclerview
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerViewAdapter = new RecyclerViewAdapter(mList,this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,SPAN_COUNT);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions
                                            , @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
