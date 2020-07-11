package echang.pxd.a3touchevent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    View redView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //通过id找到对应的控件
        redView = findViewById(R.id.view);
    }

    //重写触摸回调的事件onTouch
    //返回值表示这个事件是否已经被处理
    //true：表示已经消费了 不会继续传递
    //false: 表示自己不消费 事件还要继续往下传
    //系统自动将事件包装为MotionEvent类
    //用户可以获取事件的行为：getAction
    //  ACTION_DOWN、ACTION_MOVE、ACTION_UP、ACTION_CANCEL
    //获取触摸点的坐标：getX  getY
    /*
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //获取事件对应的类型
        int action = event.getAction();

        //获取屏幕的大小
        Point p = new Point();
        getWindowManager().getDefaultDisplay().getSize(p);
        //System.out.println("屏幕宽度:"+p.x);
        //System.out.println("屏幕高度:"+p.y);

        //获取容器本身的宽高
        RelativeLayout rl = findViewById(R.id.root);
        //System.out.println("界面宽度:"+rl.getWidth());
        //System.out.println("界面高度:"+rl.getHeight());

        //计算界面和屏幕之间的间距
        //计算状态栏和标题栏的高度
        float padding = p.y - rl.getHeight();

        if (action == MotionEvent.ACTION_DOWN){
            //按下
            //获取触摸点的x坐标和y坐标
            float x = event.getX() ;
            float y = event.getY() - padding;

            //改变控件的位置
            redView.setX(x - (float)(redView.getWidth()*0.5));
            redView.setY(y- (float)(redView.getHeight()*0.5));

        } else if (action == MotionEvent.ACTION_MOVE){
            //滑动
            //按下
            //获取触摸点的x坐标和y坐标
            float x = event.getX() ;
            float y = event.getY() - padding;

            //改变控件的位置
            redView.setX(x - (float)(redView.getWidth()*0.5));
            redView.setY(y- (float)(redView.getHeight()*0.5));
        } else if (action == MotionEvent.ACTION_UP){
            //离开屏幕
        } else{
            //被打断了
        }
        return true;
    }
    */

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //更改控件的颜色
        //redView.setBackgroundColor(Color.BLACK);

        //找到父容器
        RelativeLayout rl = findViewById(R.id.root);
        //通过tag查找父容器下面的子控件
        View iv = rl.findViewWithTag("1");
        if (iv == null){
            System.out.println("空");
        }else {
            iv.setBackgroundColor(Color.GREEN);
        }

        return true;
    }
}
