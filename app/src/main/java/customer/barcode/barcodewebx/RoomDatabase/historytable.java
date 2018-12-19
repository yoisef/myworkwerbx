package customer.barcode.barcodewebx.RoomDatabase;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "history")
public class historytable {


    @PrimaryKey(autoGenerate = true)
    private int id;


    @ColumnInfo(name = "ornum")
    private Integer ornum;
    @ColumnInfo(name = "ordata")
    private String ordata;



    @ColumnInfo(name = "oramount")
    private Integer oramount;
    @ColumnInfo(name = "orunits")
    private String orunits;

    public historytable(Integer ornum,String ordata,Integer oramount ,String orunits)
    {
        this.oramount=oramount;
        this.ordata=ordata;
        this.ornum=ornum;
        this.orunits=orunits;

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getOrnum() {
        return ornum;
    }

    public void setOrnum(Integer ornum) {
        this.ornum = ornum;
    }

    public String getOrdata() {
        return ordata;
    }

    public void setOrdata(String ordata) {
        this.ordata = ordata;
    }

    public Integer getOramount() {
        return oramount;
    }

    public void setOramount(Integer oramount) {
        this.oramount = oramount;
    }

    public String getOrunits() {
        return orunits;
    }

    public void setOrunits(String orunits) {
        this.orunits = orunits;
    }


}