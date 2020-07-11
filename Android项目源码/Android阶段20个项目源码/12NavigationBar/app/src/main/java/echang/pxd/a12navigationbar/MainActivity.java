package echang.pxd.a12navigationbar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements PXDNavigationBar.OnButtonClickedListener {
    PXDNavigationBar bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        test();
    }

    private void test(){
        // 代码方式创建控件
        bar = new PXDNavigationBar(this);
        // 设置背景颜色
        bar.setPxd_background(Color.MAGENTA);
        // 设置需要返回按钮
        bar.setShow_back(true,"返回");
        // 设置显示的位置
        bar.setPosition(PXDNavigationBar.PXDPosition.LEFT);
        // 设置监听者
        bar.setListener(this);
        // 让当前这个控件作为activity的主视图
        setContentView(bar);

    }

    @Override
    public void backButtonCicked() {
        Toast.makeText(this,"点击了",Toast.LENGTH_SHORT).show();
        bar.setPosition(PXDNavigationBar.PXDPosition.RIGHT);
    }
}















