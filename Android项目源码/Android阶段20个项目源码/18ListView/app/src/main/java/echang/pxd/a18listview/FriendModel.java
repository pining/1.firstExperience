package echang.pxd.a18listview;

import android.graphics.Bitmap;

/**
 * ImageView:用于显示图片的控件
 * Bitmap:具体的某一张图片数据
 * R.drawable.bg:只是这个图片的对应id
 * OOM -> id   - > Bitmap
 */
public class FriendModel {
    public int icon;
    public String name;

    public FriendModel(int icon, String name) {
        this.icon = icon;
        this.name = name;
    }
}
