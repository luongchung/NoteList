package n7.svmc.models;

/**
 * Created by sev_user on 8/20/2018.
 */

public class Loai {
    private int id;
    private String tenLoai;


    public Loai(int id, String tenLoai) {
        this.id = id;
        this.tenLoai = tenLoai;
    }

    public Loai() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }
}
