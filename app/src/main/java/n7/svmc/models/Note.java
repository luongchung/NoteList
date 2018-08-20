package n7.svmc.models;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Date;

/**
 * Created by sev_user on 8/20/2018.
 */

public class Note implements Serializable {
    private int id;
    private String title;
    private String content;
    private String timeBegin;
    private String timeEnd;
    private String diaDiem;
    private String idLoai;
    private boolean bt;

    public String getDiaDiem() {
        return diaDiem;
    }

    public void setDiaDiem(String diaDiem) {
        this.diaDiem = diaDiem;
    }

    public Note(int id, String title, String content, String timeBegin, String timeEnd, String diaDiem, String idLoai,boolean bt) {
        this.bt=bt;
        this.id = id;
        this.title = title;
        this.content = content;
        this.timeBegin = timeBegin;
        this.timeEnd = timeEnd;
        this.diaDiem = diaDiem;
        this.idLoai = idLoai;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTimeBegin() {
        return timeBegin;
    }

    public void setTimeBegin(String timeBegin) {
        this.timeBegin = timeBegin;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getIdLoai() {
        return idLoai;
    }

    public void setIdLoai(String idLoai) {
        this.idLoai = idLoai;
    }

    public boolean isBt() {
        return bt;
    }

    public void setBt(boolean bt) {
        this.bt = bt;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", timeBegin='" + timeBegin + '\'' +
                ", timeEnd='" + timeEnd + '\'' +
                ", diaDiem='" + diaDiem + '\'' +
                ", idLoai='" + idLoai + '\'' +
                '}';
    }
}
