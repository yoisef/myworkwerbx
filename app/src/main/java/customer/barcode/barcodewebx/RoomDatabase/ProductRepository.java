package customer.barcode.barcodewebx.RoomDatabase;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class ProductRepository {

    private WordDao mWordDao;
    private LiveData<List<mytable>> mAllProd;

    ProductRepository(Application application) {
        ProductRoomDatabase db = ProductRoomDatabase.getDatabase(application);
        mWordDao = db.wordDao();
        mAllProd = mWordDao.getAllWords();
    }

    LiveData<List<mytable>> getAllWords() {
        return mAllProd;
    }


    public void insert (mytable table) {
        new insertAsyncTask(mWordDao).execute(table);
    }

    public void deleterow(mytable mtable){new deleteit(mWordDao).execute(mtable);}



    private static class insertAsyncTask extends AsyncTask<mytable, Void, Void> {

        private WordDao mAsyncTaskDao;

        insertAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final mytable... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
    private static class deleterowAsyntask extends AsyncTask<Integer, Void, Void> {

        private WordDao mAsyncTaskDao;

        deleterowAsyntask(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Integer... params) {
            mAsyncTaskDao.deleterow(params[0]);
            return null;
        }
    }
    private static class deleteit extends AsyncTask<mytable, Void, Void> {

        private WordDao mAsyncTaskDao;

        deleteit(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(mytable... params) {
            mAsyncTaskDao.deleteit(params[0]);
            return null;
        }
    }
}
