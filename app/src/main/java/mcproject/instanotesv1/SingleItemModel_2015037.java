package mcproject.instanotesv1;


public class SingleItemModel_2015037 {


    private String topic;
    private String sem, url ,joinbtn;

    //Default constructor
    public SingleItemModel_2015037(String topic, String url, String sem, String joinbtn) {
        this.sem = sem;
        this.url = url;
        this.joinbtn=joinbtn;
        this.topic=topic;
    }

    // Getter
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

}
