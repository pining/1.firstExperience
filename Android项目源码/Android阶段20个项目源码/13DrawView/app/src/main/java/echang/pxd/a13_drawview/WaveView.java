package echang.pxd.a13_drawview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

public class WaveView extends View {
    public WaveView(Context context) {
        super(context);
    }

    public WaveView(Context context, AttributeSet attrs) {
        super(context, attrs);

        setBackgroundColor(Color.GRAY);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //创建一个路径
        Path mPath = new Path();

        mPath.moveTo(50,500);
        //画贝塞尔曲线
        /*
        mPath.cubicTo(50,500,
                200,300,
                350,500);
        mPath.cubicTo(350,500,
                500,700,
                650,500);
                */

        mPath.quadTo(200,300,350,500);
        mPath.quadTo(500,700,650,500);

        //画笔
        Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStrokeWidth(10);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);

        //绘制
        canvas.drawPath(mPath,mPaint);
    }
}













