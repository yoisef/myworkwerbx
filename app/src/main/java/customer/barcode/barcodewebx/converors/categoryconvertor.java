package customer.barcode.barcodewebx.converors;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import customer.barcode.barcodewebx.productmodels.Category;


public class categoryconvertor {

    @TypeConverter
    public static Category stringToSomeObjectList(String data) {
        Gson gson = new Gson();
        if (data == null) {
            return new Category();
        }

        Type listType = new TypeToken<Category>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String someObjectListToString(Category someObjects) {
        Gson gson = new Gson();
        return gson.toJson(someObjects);
    }
}
