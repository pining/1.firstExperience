package echang.pxd.a14waveloading;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.ViewGroup;

public class WaveLoadingView extends ViewGroup {
    private float progress; //进度
    private CircleView cv; //进度 圆圈视图
    private WaveView wv; //波浪视图

    public WaveLoadingView(Context context) {
        super(context);
    }

    public WaveLoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * viewGroup中 通过测量左右子视图来确定当前容器的宽高
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    /**
     * 对所有子视图进行布局
     * b
     * i  == l left
     * i1 == t top
     * i2 == r right
     * i3 == b bottom
     */
    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        //创建CircleView
        cv = new CircleView(getContext());
        cv.setLineColor(Color.RED);
        cv.setLineSize(50);
        cv.setTextColor(Color.RED);
        cv.setCenterYSpace(-90);
        //对子视图进行布局
        cv.layout(0,0,getWidth(),getHeight());
        //将子视图添加到容器中
        addView(cv);

        //创建WaveView
        wv = new WaveView(getContext());
        wv.setLineColor(Color.RED);
        wv.setLineSize(5);
        wv.setWaveCrest(30);
        wv.setWaveLength(100);
        //布局
        wv.layout(getWidth()/4,getHeight()/2-30,
                getWidth()*3/4,getHeight()/2+30);
        //添加
        addView(wv);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

    }

    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
        //改变CircleView的进度值
        if (progress < 1.01) {
            cv.setProgress(progress);
        }

        if ((int)progress == 1){
            wv.stopWave();
        }
    }
}










