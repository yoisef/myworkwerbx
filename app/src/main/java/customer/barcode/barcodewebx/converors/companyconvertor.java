package customer.barcode.barcodewebx.converors;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

 import customer.barcode.barcodewebx.productmodels.Company;

public class companyconvertor {


    @TypeConverter
    public static Company stringToSomeObjectList(String data) {
        Gson gson = new Gson();
        if (data == null) {
            return new Company();
        }

        Type listType = new TypeToken<Company>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String someObjectListToString(Company someObjects) {
        Gson gson = new Gson();
        return gson.toJson(someObjects);
    }
}