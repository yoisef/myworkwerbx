package customer.barcode.barcodewebx.RoomDatabase;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface WordDao {

    @Insert
    void insert(mytable table);

    @Insert
    void inserthis(historytable table);

    @Query("DELETE FROM product")
    void deleteAll();

    @Query("DELETE FROM product WHERE ID = :id")
    abstract void deleterow(long id);

    @Delete
    void deleteit(mytable model);

    @Query("SELECT * from product ")
    LiveData<List<mytable>> getAllWords();

    @Query("SELECT * from history ")
    LiveData<List<historytable>> getAllHis();


}