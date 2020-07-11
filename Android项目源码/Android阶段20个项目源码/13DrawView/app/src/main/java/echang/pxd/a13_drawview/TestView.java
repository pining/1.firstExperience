package echang.pxd.a13_drawview;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 饼状图
 */
public class TestView extends View {
    int startAngle = 0;
    int angle; //每次增长之后的值
    int speed = 10; //增长速度

    public TestView(Context context) {
        super(context);
    }

    public TestView(Context context, AttributeSet attrs) {
        super(context, attrs);

        setBackgroundColor(Color.GRAY);
    }

    /**
     * 当继承于ViewGroup的时候
     * @param widthMeasureSpec 父控件预测的这个控件的最大宽度
     * @param heightMeasureSpec 父控件预测的这个控件的最大高度
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //固定的尺寸 - 自己设定宽高
        //setMeasuredDimension(100,100);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        /*
        //创建定时器 每个0.5s 画一次
        final Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                angle += speed;
                if (angle > 360){
                    t.cancel();//关闭定时器
                }
                //invalidate(); //通知系统调用ondraw
                postInvalidate();//子线程里面
            }
        },0,100);
        */

        //属性动画
        //ObjectAnimator
        //ValueAnimator 监听动画过程中某个值的改变过程
        //angle 0 - 360

        //创建ValueAnimator对象 设置范围0-360
        ValueAnimator va = ValueAnimator.ofInt(0,360);
        va.setDuration(1000);
        va.setRepeatCount(ValueAnimator.INFINITE);
        va.setRepeatMode(ValueAnimator.RESTART);

        //设置监听器 监听值的变化
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                //获取某一个的值
                angle = (int) valueAnimator.getAnimatedValue();
                //刷新
                invalidate();
            }
        });
        //启动
        va.start();
    }

    /**
     * 如果自定义的视图
     * 形状无规则
     * 用特定的方式显示内容
     * 完成特定功能
     * @param canvas 默认提供的一个画布
     * 将需要的内容画到画布上 统一 渲染到界面上GPU
     * 缺点: 如果频繁绘制 内存吃紧
     *
     * 能够使用系统的就不要自己绘制
     */
    @Override
    protected void onDraw(Canvas canvas) {


        //画一条线
        /*
        //1.准备画笔
        Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLACK);//画笔颜色
        mPaint.setStrokeWidth(20);//画笔的粗细
        mPaint.setAntiAlias(true); //抗锯齿
        mPaint.setStyle(Paint.Style.FILL);//设置空心
        */
        /*
        //2.画线
        canvas.drawLine(100,100,
                500,800,
                mPaint);
        */

        //3.画圆
        /*
        canvas.drawArc(50,100,550,600,
                0,90,
                true,mPaint);
                */

        drawPieChart(canvas,Color.MAGENTA, (float) (angle/360.0));
        //drawPieChart(canvas,Color.BLUE, (float) (1/4.0));
        //drawPieChart(canvas,Color.GREEN, (float) (1/4.0));
        //drawPieChart(canvas,Color.RED, (float) (1/4.0));

    }

    //画饼状图
    private void drawPieChart(Canvas canvas,int color,float rate){
        //画笔
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(color);

        //画扇形
        int endAngle = (int) (360*rate);
        canvas.drawArc(50,100,550,600,
                0,endAngle,
                true,paint);

        startAngle += endAngle;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            //点击屏幕 画一点
            angle += speed;

            //控制最大值
            if (angle > 360){
                angle = 360;
            }

            //告诉系统调用onDraw方法实现绘制
            invalidate();
        }
        return true;
    }
}

















