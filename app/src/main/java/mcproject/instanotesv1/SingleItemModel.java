package mcproject.instanotesv1;


public class SingleItemModel{


    private String topic;
    private String sem, url ,joinbtn;



    public SingleItemModel() {
    }

    public SingleItemModel(String topic, String url, String sem, String joinbtn) {
        this.sem = sem;
        this.url = url;
        this.joinbtn=joinbtn;
        this.topic=topic;
    }


    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTopic() {
        return topic;
    }

    public String getSem() {
        return sem;
    }

    public String getUrl() {
        return url;
    }

    public String getJoinbtn() {
        return joinbtn;
    }

    public void setSem(String sem) {
        this.sem = sem;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setJoinbtn(String joinbtn) {
        this.joinbtn = joinbtn;
    }
}
