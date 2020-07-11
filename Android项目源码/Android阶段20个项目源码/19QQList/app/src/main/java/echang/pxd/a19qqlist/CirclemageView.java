package echang.pxd.a19qqlist;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.appcompat.widget.AppCompatImageView;

public class CirclemageView extends AppCompatImageView {
    public CirclemageView(Context context) {
        super(context);
    }

    public CirclemageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void draw(Canvas canvas) {
        //1.创建需要的形状的路径Path
        Path path = new Path();
        path.addCircle(
                getPivotX(),
                getPivotY(),
                getWidth()/2,
                Path.Direction.CCW);

        //2.裁剪
        canvas.clipPath(path);
        super.draw(canvas);
    }
}
