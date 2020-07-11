package echang.pxd.customattr;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        //代码创建自定义的控件
        PageController pc = new PageController(this);
        //设置每个点显示的样式
        pc.resourceID = R.drawable.dot_shape;
        //设置间距
        pc.padding = (int) (10 * getResources().getDisplayMetrics().density);
        //设置显示的页数
        pc.setNumberOfPages(5);
        //监听事件
        pc.addPageChangeListener(new PageController.PageChangeListener() {
            @Override
            public void pageDidChanged(int currentPage) {
                System.out.println("选中第"+currentPage+"页");
            }
        });

        pc.addPageChangeListener(page -> {
            System.out.println("选中第"+page+"页");
        });

        //创建这个控件在父控件的具体布局参数
        RelativeLayout.LayoutParams params = new
                RelativeLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置约束
        params.addRule(RelativeLayout.CENTER_IN_PARENT);

        //将控件添加到界面上->找到当前界面的容器
        RelativeLayout rl = findViewById(R.id.root);
        //添加子控件
        rl.addView(pc,params);
        */
    }
}
