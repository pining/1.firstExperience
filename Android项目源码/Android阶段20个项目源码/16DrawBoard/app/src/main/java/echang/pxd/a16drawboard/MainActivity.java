package echang.pxd.a16drawboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private DrawBoardView boardView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取画板视图对象
        boardView = findViewById(R.id.board);

        //获取滑动条视图对象
        final Slider  slider = findViewById(R.id.slider);
        slider.setMax(30);
        slider.setStyle(Slider.SLIDER);
        slider.setOnSliderChangeListener(new Slider.OnSliderChangeListener() {
            @Override
            public void progressChanged(float progress) {
                //滑动条的进度值 设置为 线条的宽度
                boardView.setLineSize((int) progress);
            }
        });


        slider.setProgress(boardView.getLineSize());
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //设置横竖屏
        setRequestedOrientation(
                ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
    }

    //选择颜色
    public void choiceColor(View view) {
        // 获取按钮上面的背景颜色
        ColorDrawable drawable = (ColorDrawable)view.getBackground();

        //获取颜色
        boardView.setLineColor(drawable.getColor());
    }

    //撤销
    public void goBack(View view) {
        boardView.removeLast();
    }

    //清空
    public void clear(View view) {
        boardView.removeAll();
    }

    //橡皮擦
    public void eraser(View view) {
        //获取画板的drawble
        ColorDrawable drawable = (ColorDrawable) boardView.getBackground();
        //设置线条的颜色和背景色相同
        if (drawable != null) {
            boardView.setLineColor(drawable.getColor());
        }else{
            boardView.setLineColor(Color.WHITE);
        }
    }

    //保存
    public void save(View view) {
    }

    //还原 上一步 ctrl+z
    public void lastStep(View view) {
        boardView.returnTolastStep();
    }
}

