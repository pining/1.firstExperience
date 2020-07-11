package echang.pxd.a14waveloading;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class CircleView extends View {
    Paint cirlePaint;
    Paint textPaint;
    private int centerYSpace; //和中心线y的距离
    private int lineSize = 10; //线条粗细
    private int lineColor = Color.BLACK;//线条颜色
    private int textColor = Color.BLACK;//文本颜色
    private int textSize = 50;//文本大小

    private float progress; //进度

    public CircleView(Context context) {
        super(context);
        init();
    }

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        cirlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        cirlePaint.setColor(lineColor);
        cirlePaint.setStyle(Paint.Style.STROKE);
        cirlePaint.setStrokeWidth(lineSize);

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(textColor);
        textPaint.setTextSize(textSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //确定半径
        int radius = Math.min(getWidth(),getHeight())/2-lineSize;
        //画圆
        canvas.drawCircle(getPivotX(),getPivotY(),
                radius,cirlePaint);

        //文本
        String text = (int)(progress*100)+"%";
        //计算文本宽度
        int width = (int) textPaint.measureText(text);
        //获取文字fontMetrics
        Paint.FontMetricsInt fm = textPaint.getFontMetricsInt();

        canvas.drawText(text,getPivotX()-width/2,
                getPivotY()+(-fm.ascent)/2+centerYSpace,textPaint);
    }

    public void setLineSize(int lineSize) {
        this.lineSize = lineSize;
        cirlePaint.setStrokeWidth(lineSize);
    }

    public void setLineColor(int lineColor) {
        this.lineColor = lineColor;
        cirlePaint.setColor(lineColor);
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
        textPaint.setColor(textColor);
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
        textPaint.setTextSize(textSize);
    }

    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
        //刷新
        invalidate();
    }

    public void setCenterYSpace(int centerYSpace) {
        this.centerYSpace = centerYSpace;
    }
}
