package echang.pxd.a16drawboard;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class Slider extends View {
    private int lineSize = 6; //线条的粗细
    private int lineColor = Color.BLACK;//默认线条颜色
    private Paint linePaint;

    private Paint circlePaint; //小圆点的画笔
    private int thumbColor = Color.MAGENTA; //小圆点的颜色
    private int cx; //中心点x
    private int cy; //中心点y
    private int radius; //小圆点半径

    private int thumbScale = 4; //小圆点缩放尺寸基数
    private float position; //记录触摸点的坐标

    private Paint progressPaint; //进度条进度的画笔
    private int progressColor = Color.MAGENTA;//颜色

    public static int PROGRESS = 0;//进度条
    public static int SLIDER = 1;//滑动条
    private int style = PROGRESS; //样式

    public int max = 100; //最大值
    public float progress; //进度值

    //滑动改变的监听者
    private OnSliderChangeListener onSliderChangeListener;

    public Slider(Context context) {
        super(context);
    }

    public Slider(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        //背景线
        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint.setColor(lineColor);
        linePaint.setStrokeWidth(lineSize);

        //小圆点
        circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        circlePaint.setColor(thumbColor);
        circlePaint.setStyle(Paint.Style.FILL);

        //进图条
        progressPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        progressPaint.setColor(progressColor);
        progressPaint.setStrokeWidth(lineSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        if (getWidth() > getHeight()){
            //横着
            //画背景条
            canvas.drawLine(0,
                    getHeight()/2,
                    getWidth(),
                    getHeight()/2,
                    linePaint);

            if (position > 0) {
                //画进度条
                canvas.drawLine(0,
                        getHeight() / 2,
                        position,
                        getHeight() / 2,
                        progressPaint);
            }

            radius = getHeight()/thumbScale;
            cy = getHeight()/2;

            //确定cx的值
            if (position < radius){
                cx = radius;
            }else if (position > getWidth()-radius){
                cx = getWidth()-radius;
            }else{
                cx = (int) position;
            }

        }else{
            //竖着
            canvas.drawLine(getWidth()/2,
                    0,
                    getWidth()/2,
                    getHeight(),
                    linePaint);
            if (position > 0){
                canvas.drawLine(getWidth()/2,
                        0,
                        getWidth()/2,
                        position,
                        progressPaint);
            }

            radius = getWidth()/thumbScale;
            cx = getWidth()/2;

            //确定中心点cy的值
            if (position < radius){
                cy = radius;
            }else if(position > getHeight()-radius){
                cy = getHeight()-radius;
            }else{
                cy = (int) position;
            }
        }

        //画小圆点
        if (style == SLIDER) {
            canvas.drawCircle(cx, cy, radius, circlePaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                //小圆点放大
                thumbScale = 2;
                if (getWidth() > getHeight()){
                    //横向时 y不变 x改变
                    position = event.getX();
                }else{
                    //竖着时 x不变 y改变
                    position = event.getY();
                }
                callback();
                break;
            case MotionEvent.ACTION_MOVE:
                //获取当前触摸点的值 X Y
                if (getWidth() > getHeight()){
                    //横向时 y不变 x改变
                    position = event.getX();
                    if (position <0){
                        position = 0;
                    } else if (position > getWidth()){
                        position = getWidth();
                    }
                }else{
                    //竖着时 x不变 y改变
                    position = event.getY();
                    if (position <0){
                        position = 0;
                    } else if (position > getHeight()){
                        position = getHeight();
                    }
                }
                callback();
                break;
            case MotionEvent.ACTION_UP:
                thumbScale = 4;
                break;
        }
        if (style == SLIDER) {
            invalidate();
        }
        return true;
    }

    private void callback(){
        if (onSliderChangeListener != null){
            if (getWidth() > getHeight()) {
                progress = position / getWidth();
            }else{
                progress = position / getHeight();
            }

            onSliderChangeListener.
                    progressChanged(progress*max);
        }
    }

    public int getStyle() {
        return style;
    }

    public void setStyle(int style) {
        this.style = style;
    }

    public float getProgress() {
        return progress;
    }

    public void setProgress(int progress){
        //计算比例
        float rate = (float) (progress*1.0 / max);

        setProgress(rate);

        System.out.println("int progress");
    }

    public void setProgress(float progress) {
        this.progress = progress;

        if (progress < 1.001) {
            //将进度值 转化为控件中的尺寸位置
            //0.5   500   250
            if (getWidth() > getHeight()) {
                position = progress * getWidth();
            } else {
                position = progress * getHeight();
            }

            invalidate();
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (getWidth() > getHeight()) {
            position = progress * getWidth();
        } else {
            position = progress * getHeight();
        }
    }

    public void setMax(int max) {
        this.max = max;
    }

    public interface OnSliderChangeListener{
        void progressChanged(float progress);
    }

    public void setOnSliderChangeListener(OnSliderChangeListener onSliderChangeListener) {
        this.onSliderChangeListener = onSliderChangeListener;
    }
}











