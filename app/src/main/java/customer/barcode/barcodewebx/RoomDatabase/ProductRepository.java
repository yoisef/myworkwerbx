package customer.barcode.barcodewebx.RoomDatabase;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class ProductRepository {

    private WordDao mWordDao;
    private LiveData<List<mytable>> mAllProd;
    private LiveData<List<historytable>> mAllhis;

    ProductRepository(Application application) {
        ProductRoomDatabase db = ProductRoomDatabase.getDatabase(application);
        mWordDao = db.wordDao();
        mAllProd = mWordDao.getAllWords();
        mAllhis=mWordDao.getAllHis();
    }

    LiveData<List<mytable>> getAllWords() {
        return mAllProd;
    }

    LiveData<List<historytable>> getAllHis() {
        return mAllhis;
    }



    public void insert (mytable table) {
        new insertAsyncTask(mWordDao).execute(table);
    }

    public void deleterow(mytable mtable){new deleteit(mWordDao).execute(mtable);}

    public void updaterow(long newitems ,long barcode){new updateit(mWordDao).execute( newitems , barcode);}

    //histrorytable
    public void insert (historytable hist) {
        new insertAsyncTaskhis(mWordDao).execute(hist);
    }
    private static class insertAsyncTaskhis extends AsyncTask<historytable, Void, Void> {

        private WordDao mAsyncTaskDao;

        insertAsyncTaskhis(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final historytable... params) {
            mAsyncTaskDao.inserthis(params[0]);
            return null;
        }
    }





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
    private static class updateit extends AsyncTask<Long, Void, Void> {

        private WordDao mAsyncTaskDao;

        updateit(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Long... params) {
            mAsyncTaskDao.updateproduct(params[0],params[1]);
            return null;
        }
    }

}
