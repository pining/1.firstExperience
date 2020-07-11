package echang.pxd.group;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //使用自定义的控件
        /*
        PageController pc = new PageController(this);
        pc.setPadding(10);
        pc.normalResource = R.drawable.dot_gray_shape;
        pc.selectedResource = R.drawable.dot_red_shape;
        pc.setNumberOfPages(5);
        */

        PageController pc = new PageController(this,
                R.drawable.dot_gray_shape,
                R.drawable.dot_red_shape,
                10);
        pc.setNumberOfPages(5);


        //找到当前界面的容器
        RelativeLayout rl = findViewById(R.id.root);

        //添加控件
        rl.addView(pc);
    }
}
