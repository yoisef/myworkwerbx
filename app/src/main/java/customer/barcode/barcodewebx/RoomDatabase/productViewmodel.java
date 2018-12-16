package customer.barcode.barcodewebx.RoomDatabase;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class productViewmodel extends AndroidViewModel {
    private ProductRepository mRepository;
    private LiveData<List<mytable>> mAllpro;
    public productViewmodel (Application application) {
        super(application);
        mRepository = new ProductRepository(application);
        mAllpro = mRepository.getAllWords();
    }

   public LiveData<List<mytable>> getAllWords() { return mAllpro; }

    public void insert(mytable word) { mRepository.insert(word); }

    public void delterow(mytable num){mRepository.deleterow(num);}

}
