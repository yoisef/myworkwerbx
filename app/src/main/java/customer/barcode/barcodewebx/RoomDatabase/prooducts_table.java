package customer.barcode.barcodewebx.RoomDatabase;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.util.List;

import customer.barcode.barcodewebx.DataConvertor;
import customer.barcode.barcodewebx.productdatabasemodels.Product;

@Entity(tableName = "products")
public class prooducts_table {


    @PrimaryKey(autoGenerate = true)
    private int id;

    @TypeConverters(DataConvertor.class)
    public  List<Product> prodet;



    public void setProdet(List<Product> prodet) {
        this.prodet = prodet;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Product> getProdet() {
        return prodet;
    }

    public prooducts_table(List<Product> prodet)
    {

    }

}
