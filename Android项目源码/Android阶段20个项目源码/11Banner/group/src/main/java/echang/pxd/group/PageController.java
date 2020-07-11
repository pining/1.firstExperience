package echang.pxd.group;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * 自己定义的一个类/控件 -> 封装指示器的功能
 * 1. 最外层是线性布局 所以继承于LinearLayout
 * 2. 重写构造方法
 * 3. 更改构造方法 依次 访问参数多的那个，初始化代码在最后一个写
 * 4. 实现功能 -> 功能在哪里写
 *              -创建控件 就默认有了（构造方法）
 *              -用户设置 (对应的set方法)
 *              -数据源  (接口里面)
 * 自定义控件容易犯的错误
 *  -(时机不对)调用属性的时候 属性还没有值 值是后面再赋值的
 * 解决方式
 *  - 如果创建控件时就能确定的值 放在构造方法里面
 *  - 自定义属性 在xml里面配置
 */
public class PageController extends LinearLayout {
    private int numberOfPages; //记录多少个
    private float padding; //间距

    public int normalResource;//正常状态的资源
    public int selectedResource;//选中状态的资源

    //当使用java代码创建控件时 用这个构造方法
    public PageController(Context context,
                          int normalRes,
                          int selectRes,
                          float padding) {
        super(context,null);

        //保存外部传递过来的数据
        normalResource = normalRes;
        selectedResource = selectRes;
        this.padding = padding;
    }

    //xml里面配置
    public PageController(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    //xml里面还配置了样式的
    public PageController(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(LinearLayout.HORIZONTAL);
    }

    /**
     * numberOfPages 的setter/getter方法
     */
    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;

        //依次创建每一个指示点
        for (int i = 0; i < numberOfPages; i++) {
            //创建控件
            ImageView dotView = new ImageView(getContext());

            //创建布局参数
            LayoutParams params = new LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);

            //设置资源
            if (i == 0){
                dotView.setImageResource(selectedResource);
            }else{
                dotView.setImageResource(normalResource);
                //设置间距
                params.leftMargin = (int) padding;
            }

            //垂直居中
            params.gravity = Gravity.CENTER_VERTICAL;

            //添加控件
            addView(dotView, params);
        }
    }

    /**
     * padding的setter/getter方法
     */
    public float getPadding() {
        return padding;
    }

    public void setPadding(float padding) {
        this.padding = padding;
    }

    /**
     * 定义一个接口 在接口里面定义常量 -> 状态
     */
    public interface DotState{
        int NORMAL = 0;
        int SELECTED = 1;
    }

}
