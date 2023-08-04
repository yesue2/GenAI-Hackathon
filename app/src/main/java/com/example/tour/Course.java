package com.example.tour;

import java.io.Serializable;
import java.util.List;

public class Course {

    private int id;
    private String image;
    private String name;
    private String hashtag;
    private List<Information> infomation;

    protected static class Information implements Serializable {
        private String day;
        private String morning;
        private String afternoon;

        public String getDay() {
            return day;
        }
        public String getMorning() {
            return morning;
        }

        public String getAfternoon() {
            return afternoon;
        }
    }

    public int getId() {return id;}

    public String getName() {return name;}

    public String getImage() {return image;}

    public String getHashtag() {
        return hashtag;
    }

    public List<Information> getInfomation() {
        return infomation;
    }
}
