package customer.barcode.barcodewebx.RoomDatabase;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class productViewmodel extends AndroidViewModel {
    private ProductRepository mRepository;
    private LiveData<List<mytable>> mAllpro;
    private LiveData<List<historytable>> mallhis;
    public productViewmodel (Application application) {
        super(application);
        mRepository = new ProductRepository(application);
        mAllpro = mRepository.getAllWords();
        mallhis=mRepository.getAllHis();
    }

   public LiveData<List<mytable>> getAllWords() { return mAllpro; }

    public void insert(mytable word) { mRepository.insert(word); }

    public void delterow(mytable num){mRepository.deleterow(num);}

    public void updateproduct(long newitems ,long barcode){mRepository.updaterow( newitems , barcode);}

    ///history section
    public LiveData<List<historytable>> getAllhistory() { return mallhis; }

    public void inserthis(historytable hist) { mRepository.insert(hist); }


}
