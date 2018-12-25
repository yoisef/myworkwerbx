package customer.barcode.barcodewebx;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import customer.barcode.barcodewebx.RoomDatabase.Sqlitetable;


public class productdatabase extends SQLiteOpenHelper {

    public static final String Databasename = "products.db";
    public static final String Tablename1 = "products_list";


    public static final String columna = "Pro_name";
    public static final String columnb = "Pro_bar";
    public static final String columnc = "Pro_price";
    public static final String columnd = "Pro_img";
    public static final String columne = "Pro_detail";


    public productdatabase(Context context) {
        super(context, Databasename, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String example = "CREATE TABLE " + Tablename1 + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,Pro_name TEXT ,Pro_bar TEXT ,Pro_price TEXT ,Pro_img TEXT ,Pro_detail TEXT);";


        db.execSQL(example);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + Tablename1);


        onCreate(db);

    }

    public Boolean insertdatalistproducts(String name, String bar, String price, String img, String detail) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(columna, name);
        contentValues.put(columnb, bar);
        contentValues.put(columnc, price);
        contentValues.put(columnd, img);
        contentValues.put(columne, detail);

        long result = db.insert(Tablename1, null, contentValues);

        if (result == -1) {
            return false;

        } else {
            return true;
        }
    }


    public Sqlitetable getdataforrowinproduct(String barsearch) {
        SQLiteDatabase db = this.getReadableDatabase();
        Sqlitetable mytable =null;

        // Filter results WHERE "title" = 'My Title'
        String myquery = "SELECT * FROM " + Tablename1 + " WHERE " + columnb + "=" + barsearch + ";";


        Cursor cursor = db.rawQuery(myquery, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int index = cursor.getColumnIndexOrThrow(columna);
            String nam = cursor.getString(index);
            int indexx = cursor.getColumnIndexOrThrow(columnb);
            String bar = cursor.getString(indexx);
            int indexxx = cursor.getColumnIndexOrThrow(columnc);
            String price = cursor.getString(indexxx);
            int indexxxx = cursor.getColumnIndexOrThrow(columnd);
            String img = cursor.getString(indexxxx);
            int indexxxxx = cursor.getColumnIndexOrThrow(columne);
            String detail = cursor.getString(indexxxxx);

            mytable = new Sqlitetable(nam, bar, price, img, detail);
            //add to list here
        }
        cursor.close();
        return mytable;
    }
}

        // How you want the results sorted in the resulting Cursor


            // The sort order
