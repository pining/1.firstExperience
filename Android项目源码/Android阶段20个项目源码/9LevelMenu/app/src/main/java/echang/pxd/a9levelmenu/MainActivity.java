package echang.pxd.a9levelmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //记录第三次菜单的状态
    private boolean isLevel3Open = true;
    private boolean isLevel2Open = true;

    private RelativeLayout level3;
    private RelativeLayout level2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //加载容器布局
        level3 = findViewById(R.id.rl_level3);
        level2 = findViewById(R.id.rl_level2);

        //menu按钮
        ImageButton menu = findViewById(R.id.ib_menu);
        ImageButton home = findViewById(R.id.ib_home);
        //添加点击事件
        menu.setOnClickListener(this);
        home.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //判断哪个按钮被点击
        switch (view.getId()){
            case R.id.ib_menu:
                if (isLevel3Open){
                    //关闭
                    close(level3,0);
                }else{
                    //打开
                    open(level3);
                }
                //改变状态
                isLevel3Open = !isLevel3Open;
                break;
            case R.id.ib_home:
                long delay = 0;
                if (isLevel3Open){
                    //关闭第三次菜单
                    close(level3,0);
                    delay = 200;
                    isLevel3Open = false;
                }

                if (isLevel2Open){
                    //关闭第二层菜单
                    close(level2,delay);
                }else{
                    //打开第二层菜单
                    open(level2);
                }
                isLevel2Open = !isLevel2Open;

                break;
            default:
                break;
        }
    }

    public void open(RelativeLayout rl){
        Animation in = AnimationUtils.loadAnimation(
                this,R.anim.rotate_in_anim);
        rl.startAnimation(in);
        //子控件可点击
        changeState(rl,true);
    }

    public void close(RelativeLayout rl,long delay){
        Animation out = AnimationUtils.loadAnimation(
                this,R.anim.rotate_out_anim);
        out.setStartOffset(delay);
        rl.startAnimation(out);
        //子控件不可点击
        changeState(rl,false);
    }

    public void changeState(RelativeLayout rl,boolean enabled){
        //1.获取容器子控件的个数
        int childCount = rl.getChildCount();
        //2.遍历容器的子控件
        for (int i = 0; i < childCount; i++) {
            //取出对应的子控件
            View v = rl.getChildAt(i);
            //设置子控件的状态
            v.setEnabled(enabled);
        }
    }
}
