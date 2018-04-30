package mcproject.instanotesv1;


import java.util.ArrayList;

public class Notes {
    private String title,user,link, userid;
    private int like,imgCount,dislike;
    private boolean fav,isLiked,isDisliked;
    private ArrayList<String> list;
    private String picuri;

    public Notes(String title, String user, int like, int dislike, boolean fav, String link, int imgCount, boolean isLiked, boolean isDisliked, ArrayList<String> list,String s, String userid){
        this.title = title;
        this.user = user;
        this.like = like;
        this.fav = fav;
        this.link = link;
        this.imgCount = imgCount;
        this.isLiked = isLiked;
        this.isDisliked=isDisliked;
        this.list = list;
        this.picuri = s;
        this.userid=userid;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
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
    public int getDislike(){return dislike;}

    public void setLike(int like) {
        this.like = like;
    }

    public void setDislike(int dislike)
    {
        this.dislike=dislike;
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
    public boolean getisDisliked() {
        return isDisliked;
    }

    public void setisLiked(boolean liked) {
        isLiked = liked;
    }
    public void setisDisliked(boolean disliked) {
        isDisliked = disliked;
    }

    public ArrayList<String> getList() {
        return list;
    }

    public void setList(ArrayList<String> list) {
        this.list = list;
    }

    public String getPicuri() {
        return picuri;
    }

    public void setPicuri(String picuri) {
        this.picuri = picuri;
    }
}


