package echang.pxd.a13_drawview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

/**
 * 仪表盘
 */
public class MeterView extends View {
    private Paint bgPaint; //背景画笔
    private Paint progressPaint; //进度画笔
    private Paint textPaint; //文字画笔
    private float progress; //进度

    public MeterView(Context context) {
        super(context);
    }

    public MeterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        //背景画笔
        bgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        bgPaint.setColor(Color.BLACK);
        bgPaint.setStyle(Paint.Style.STROKE);
        bgPaint.setStrokeWidth(40);
        bgPaint.setStrokeCap(Paint.Cap.ROUND);//设置两端的类型

        progressPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        progressPaint.setColor(Color.MAGENTA);
        progressPaint.setStyle(Paint.Style.STROKE);
        progressPaint.setStrokeWidth(40);
        progressPaint.setStrokeCap(Paint.Cap.ROUND);//设置两端的类型

        textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(100);
    }
    /**
     * 固定不变的 就不要放在onDraw方法里面
     * 这个方法可能会被调用多次
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        //画初始圆
        //确定矩形区域
        RectF frame = new RectF(50,100,
                getWidth()-50,getWidth()-50);

        //画一个弧
        canvas.drawArc(frame,120,300,
                false,bgPaint);

        //计算进度对应的角度
        int angle = (int) (progress*300);
        canvas.drawArc(frame,120,angle,
                false,progressPaint);

        //文本内容
        String text = (int)(progress*100)+"%";

        //计算文字的宽度
        int width = (int) textPaint.measureText(text);

        //计算文字的矩阵 FontMetrics
        Paint.FontMetricsInt fo = textPaint.getFontMetricsInt();

        //文字的高度
        int height = fo.bottom - fo.top;

        //计算向下移动的距离  Ascent/2 注意Ascent为负数
        int space = -fo.ascent /2 ;

        //画文字
        canvas.drawText(text,getWidth()/2-width/2,
                getWidth()/2+space,textPaint);
    }

    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;

        //刷新 重绘
        if (progress < 1.0001) {
            invalidate();
        }
    }
}
