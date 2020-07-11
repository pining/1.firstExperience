package echang.pxd.tween;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

/**
 * 关键帧动画：配置了动画的每一帧
 *  res-drawable-xxx.xml
 *
 * 补间动画：只关心开始和结束两个状态 中间的动画由系统自动补全
 * 平移：translate
 * 缩放：scale
 * 旋转：rotate
 * 透明：alpha
 *
 * 1.xml配置动画
 *   res -> anim -> xxx.xml
 * 2.代码
 *
 * 注意：动画只是一个效果 没有真正地改变属性的值
 * 属性动画才是真正地改变了控件的属性的值
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            //1.找到需要动画的控件
            View v = findViewById(R.id.v_anim);

            //2.创建动画
            /*
            TranslateAnimation;
            AlphaAnimation;
            ScaleAnimation;
            RotateAnimation;
            */
            /*
            AlphaAnimation alpha = new AlphaAnimation(0,1);
            alpha.setDuration(1000);
            alpha.setFillAfter(true);
            */

            RotateAnimation rotate = new RotateAnimation(0,360,
                    (float)(0.5*v.getWidth()),
                    (float)(0.5*v.getHeight()));
            rotate.setDuration(1000);

            //3.开启动画
            v.startAnimation(rotate);


            //2.加载xml动画文件 -> 得到动画
            //Animation translate = AnimationUtils.loadAnimation(
                    //this, R.anim.translate);
            //translate.setInterpolator(new BounceInterpolator());
            //translate.setRepeatCount(Animation.INFINITE);
            //translate.setRepeatMode(Animation.REVERSE);
            //3.将这个动画作用到这个控件上
            //v.startAnimation(translate);
        }
        return true;
    }
}

/**
 * ListView - UITableView
 */
