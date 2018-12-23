package customer.barcode.barcodewebx.converors;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

import customer.barcode.barcodewebx.productdatabasemodels.Image;
import customer.barcode.barcodewebx.productdatabasemodels.Product;

public class imageconvertor {



    @TypeConverter
    public static List<Image> stringToSomeObjectList(String data) {
        Gson gson = new Gson();
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<Image>>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String someObjectListToString(List<Image> someObjects) {
        Gson gson = new Gson();
        return gson.toJson(someObjects);
    }
}