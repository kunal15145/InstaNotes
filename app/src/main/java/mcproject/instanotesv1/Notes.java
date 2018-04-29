package mcproject.instanotesv1;

import android.net.Uri;

import java.util.ArrayList;

public class Notes {
    private String title,user,link;
    private int like,imgCount;
    private boolean fav,isLiked;
    private ArrayList<String> list;

    public Notes(String title, String user, int like, boolean fav, String link, int imgCount, boolean isLiked, ArrayList<String> list){
        this.title = title;
        this.user = user;
        this.like = like;
        this.fav = fav;
        this.link = link;
        this.imgCount = imgCount;
        this.isLiked = isLiked;
        this.list = list;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public boolean isFav() {
        return fav;
    }

    public void setFav(boolean fav) {
        this.fav = fav;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getImgCount() {
        return imgCount;
    }

    public void setImgCount(int imgCount) {
        this.imgCount = imgCount;
    }

    public boolean getisLiked() {
        return isLiked;
    }

    public void setisLiked(boolean liked) {
        isLiked = liked;
    }

    public ArrayList<String> getList() {
        return list;
    }

    public void setList(ArrayList<String> list) {
        this.list = list;
    }
}


