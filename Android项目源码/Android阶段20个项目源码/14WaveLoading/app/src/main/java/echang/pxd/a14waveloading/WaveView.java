package echang.pxd.a14waveloading;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

public class WaveView extends View {
    private Paint mPaint;
    private Path mPath;

    float density = getResources().getDisplayMetrics().density;
    private int waveLength = (int) (100*density);//波长
    private int waveCrest = (int) (50*density);//波峰 波的高度
    private int speed;
    private int lineColor = Color.BLACK; //线条颜色
    private int lineSize = 10; //线条粗细

    private ValueAnimator va;

    public WaveView(Context context) {
        super(context);
        init();
    }

    public WaveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //从xml中获取自定义属性的值
        initAttr(context,attrs);
        //初始化画笔
        init();
    }
    private void initAttr(Context context, AttributeSet attrs){
        //读取所有自定义属性的值
        TypedArray array = context.obtainStyledAttributes(
                attrs, R.styleable.WaveView);

        //读取每一个属性的值
        waveLength = array.getInteger(R.styleable.WaveView_waveLength,
                (int) (100*density));
        waveCrest = array.getInteger(R.styleable.WaveView_waveCrest,
                (int) (50*density));
        lineColor = array.getColor(R.styleable.WaveView_lineColor,
                Color.BLACK);
        lineSize = array.getInteger(R.styleable.WaveView_lineSize,
                10);
    }

    private void init(){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(lineColor);
        mPaint.setStrokeWidth(lineSize);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    /**
     * 父容器会按照自己的规则给出一个方案
     * 子View通过MeasureSpec.getMode  .getSize获取对应
     * 的模式和具体尺寸
     * getMode:
     * Unspecified 无限制的 父容器没有对这个控件进行约束
     * At_Most 不能超过最大值 （wrap_content)
     * Exactly 确定的值  （200dp）
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec,
                             int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //取出宽度的模式和预估尺寸
        /*
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);

        switch (mode){
            case MeasureSpec.UNSPECIFIED:
                System.out.println("-----unspecified:"+width);
                break;
            case MeasureSpec.AT_MOST:
                System.out.println("-----at_most:"+width);
                break;
            case MeasureSpec.EXACTLY:
                System.out.println("-----exactly:"+width);
                break;
        }
        */
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //开始动画
        startWave();
    }

    //暂停动画
    public void stopWave(){
        if (va != null) {
            va.cancel();
        }
    }

    //开始动画
    public void startWave(){
        va = ValueAnimator.ofInt(0,waveLength);
        va.setDuration(300);
        va.setRepeatCount(ValueAnimator.INFINITE);
        va.setRepeatMode(ValueAnimator.RESTART);
        va.setInterpolator(new LinearInterpolator());
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                //获取当前值
                speed = (int) valueAnimator.getAnimatedValue();
                //刷新
                invalidate();
            }
        });
        va.start();
        //va.cancel();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        initPath();
        canvas.drawPath(mPath,mPaint);
    }

    private void initPath(){
        //创建曲线
        mPath = new Path();

        //计算有多少个周期（有几个完整的波）
        int count = getWidth()/waveLength;

        //设置起始点 距离x左边的一个波长
        mPath.moveTo(-waveLength+speed,getHeight()/2);

        //获取垂直中心的坐标
        int centerY = (int) getPivotY();

        //确定曲线的路径
        for(int start = -waveLength+speed; start < getWidth();
            start += waveLength){
            //画上半周期
            mPath.cubicTo(start,centerY,
                    start+waveLength/4,centerY-waveCrest,
                    start+waveLength/2,centerY);
            //画下半周期
            mPath.cubicTo(start+waveLength/2,centerY,
                    start+waveLength*3/4,centerY+waveCrest,
                    start+waveLength,centerY);
        }
    }

    public void setWaveLength(int waveLength) {
        this.waveLength = waveLength;
    }

    public void setWaveCrest(int waveCrest) {
        this.waveCrest = waveCrest;
    }

    public void setLineColor(int lineColor) {
        this.lineColor = lineColor;
        mPaint.setColor(lineColor);
    }

    public void setLineSize(int lineSize) {
        this.lineSize = lineSize;
        mPaint.setStrokeWidth(lineSize);
    }
}















