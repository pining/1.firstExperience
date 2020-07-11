package echang.pxd.a15imageloading;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class ProgressView extends RelativeLayout {
    ImageView outer;

    public ProgressView(Context context) {
        super(context);
        init();
    }

    public ProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        ImageView inner = new ImageView(getContext());
        inner.setImageResource(R.drawable.github_loading_inner);
        addView(inner);

        outer = new ImageView(getContext());
        outer.setImageResource(R.drawable.github_loading_outer);
        addView(outer);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        /*
        RotateAnimation ra = new RotateAnimation(
                0,720,
                getPivotX(),getPivotY());
        ra.setDuration(1000);
        ra.setRepeatMode(Animation.RESTART);
        ra.setRepeatCount(Animation.INFINITE);
        outer.startAnimation(ra);
        */
        ObjectAnimator oa = ObjectAnimator.ofFloat(
                outer,"rotation",
                0,360);
        oa.setDuration(1000);
        oa.setRepeatCount(ObjectAnimator.INFINITE);
        oa.setRepeatMode(ObjectAnimator.RESTART);
        oa.setInterpolator(new LinearInterpolator());

        oa.start();
    }
}

1. 画板
2. 滑动解锁

