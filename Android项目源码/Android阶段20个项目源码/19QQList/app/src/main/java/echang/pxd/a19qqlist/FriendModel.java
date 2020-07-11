package echang.pxd.a19qqlist;

public class FriendModel {
    /**
     * 头像图片资源id
     */
    public int iconID;
    /**
     * 好友昵称
     */
    public String name;

    public FriendModel(int iconID, String name) {
        this.iconID = iconID;
        this.name = name;
    }
}
