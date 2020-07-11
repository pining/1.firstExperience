package echang.pxd.a13_drawview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        /*
        MeterView mv = findViewById(R.id.meter);

        if (event.getAction() == MotionEvent.ACTION_DOWN){
            //更改进度值 ：在原有的进度上+0.05
            mv.setProgress((float) (mv.getProgress() + 0.05));
        }
        */
        return true;
    }
}
