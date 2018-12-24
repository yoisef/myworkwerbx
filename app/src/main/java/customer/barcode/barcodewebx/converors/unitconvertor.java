package customer.barcode.barcodewebx.converors;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import customer.barcode.barcodewebx.productdatabasemodels.Image;
import customer.barcode.barcodewebx.productdatabasemodels.Unit;

public class unitconvertor {

    @TypeConverter
    public static Unit stringToSomeObjectList(String data) {
        Gson gson = new Gson();
        if (data == null) {
            return new Unit();
        }

        Type listType = new TypeToken<Unit>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String someObjectListToString(Unit someObjects) {
        Gson gson = new Gson();
        return gson.toJson(someObjects);
    }
}
