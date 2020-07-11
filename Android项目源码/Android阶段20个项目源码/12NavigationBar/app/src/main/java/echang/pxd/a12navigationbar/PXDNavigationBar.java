package echang.pxd.a12navigationbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * 使用线性布局管理 内容
 */
public class PXDNavigationBar extends RelativeLayout {
    // 保存外部设置的背景颜色 默认灰色
    private int pxd_background = Color.DKGRAY;
    // 记录是否需要返回按钮
    private boolean show_back = false;
    // 返回按钮
    private Button back;
    // 返回按钮的布局属性
    LayoutParams params;
    // 记录监听按钮事件的对象
    OnButtonClickedListener listener;

    // 使用java代码创建控件
    public PXDNavigationBar(Context context) {
        /*super(context);
        init();*/
        this(context, null);
    }

    // 使用xml创建控件
    public PXDNavigationBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    // 初始化控件
    private void init(Context context,AttributeSet attrs){
        // 设置横向布局
        //setOrientation(LinearLayout.HORIZONTAL);
        // 设置背景
        setBackgroundColor(Color.GRAY);
        // 设置内容垂直居中
        setGravity(Gravity.CENTER_VERTICAL);

        // 判断是不是xml配置的
        if (attrs != null){
            // 从attrs里面提取 xml里面配置的所有属性
            TypedArray typedArray = context.obtainStyledAttributes(attrs,
                    R.styleable.PXDNavigationBar);

            // 提取自己需要的属性
            int color = typedArray.getColor(R.styleable.
                            PXDNavigationBar_pxd_background,
                    Color.MAGENTA);
            // 去是否需要返回按钮
            boolean show = typedArray.getBoolean(R.styleable.
                            PXDNavigationBar_show_back,
                    false);
            // 取返回按钮的标题
            String title = typedArray.getString(R.styleable.
                    PXDNavigationBar_back_title);
            // 取返回按钮的位置
            int position = typedArray.getInteger(R.styleable.
                            PXDNavigationBar_back_position,
                    0);

            // 使用数据
            setPxd_background(color);
            setShow_back(show,title);
            setPosition(position);
        }
    }

    public int getPxd_background() {
        return pxd_background;
    }

    public void setPxd_background(int pxd_background) {
        this.pxd_background = pxd_background;

        // 将外部传递过来的颜色 设置为背景颜色
        setBackgroundColor(pxd_background);
    }

    public boolean isShow_back() {
        return show_back;
    }

    public void setShow_back(boolean show_back,String title) {
        this.show_back = show_back;

        if (show_back == true){
            //创建返回按钮
            back = new Button(getContext());
            //设置按钮的布局属性
            params = new LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
            params.leftMargin = (int) (10 * getResources().getDisplayMetrics().density);
            //设置标题
            if (title != null) {
                back.setText(title);
            }else{
                back.setText("Back");
            }

            //添加控件
            addView(back,params);

            //给按钮添加点击事件
            back.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null){
                        listener.backButtonCicked();
                    }
                }
            });
        }
    }

    /**
     * 用于区别左右
     */
    public interface PXDPosition{
        int LEFT = 0;
        int RIGHT = 1;
    }

   /* public enum  PXDPosition {
        LEFT,
        RIGHT,
    }*/

    /**
     * 设置按钮显示的位置
     * @param position LEFT RIGHT
     */
    public void setPosition(int position){
        // 判断是否有back按钮
        if (back != null) {
            if (position == PXDPosition.LEFT) {
                params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            }else{
                params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            }
        }
    }

    /**
     * 定义接口 监听返回按钮被点击了
     */
    public interface OnButtonClickedListener{
        void backButtonCicked();
    }

    public void setListener(OnButtonClickedListener listener) {
        this.listener = listener;
    }
}
