package customer.barcode.barcodewebx;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import customer.barcode.barcodewebx.RoomDatabase.productViewmodel;

public class factoryclass implements ViewModelProvider.Factory {
    private Application mApplication;
    private long mParam;


    public factoryclass(Application application, long param) {
        mApplication = application;
        mParam = param;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new productViewmodel(mApplication, mParam);
    }
}