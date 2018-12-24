package customer.barcode.barcodewebx.RoomDatabase;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import customer.barcode.barcodewebx.asyncresponse;
import customer.barcode.barcodewebx.productdatabasemodels.Product;

public class ProductRepository implements asyncresponse{

    private WordDao mWordDao;
    private LiveData<List<mytable>> mAllProd;
    private LiveData<List<historytable>> mAllhis;
    private LiveData<Product> mytable;
    private Product getrowdetails;



    ProductRepository(Application application) {
        ProductRoomDatabase db = ProductRoomDatabase.getDatabase(application);
        mWordDao = db.wordDao();
        mAllProd = mWordDao.getAllWords();
        mAllhis=mWordDao.getAllHis();
     //   mytable=mWordDao.getrowinfo(barcode);

    }

    LiveData<List<mytable>> getAllWords() {
        return mAllProd;
    }

    LiveData<List<historytable>> getAllHis() {
        return mAllhis;
    }






    @Override
    public void processFinish(Product output) {

        getrowdetails=output;

    }
    Product getrowdetails(long barcode){new getrowdata(mWordDao).execute(barcode);
        return getrowdetails;}



    public void insert (mytable table) {
        new insertAsyncTask(mWordDao).execute(table);
    }

    public void insert_rowin_productlist(Product product){new insertrowinproductlist(mWordDao).execute(product);}



    public void deleterow(mytable mtable){new deleteit(mWordDao).execute(mtable);}

    public void deletallhis(){new deleteallhis(mWordDao).execute();}

    public void updaterow(long newitems ,long barcode){new updateit(mWordDao).execute( newitems , barcode);}

    //histrorytable
    public void insert (historytable hist) {
        new insertAsyncTaskhis(mWordDao).execute(hist);
    }

    public void insertproducts(List<Product> mylist){new insertproducts(mWordDao).execute(mylist);}



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

    private static class getrowdata extends AsyncTask<Long, Void, Product> {

        private WordDao mAsyncTaskDao;
        public ProductRepository response=null;

        getrowdata(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Product doInBackground(final Long... params) {

            return mAsyncTaskDao.getrowinfo(params[0]);
        }

        @Override
        protected void onPostExecute(Product product) {
            response.processFinish(product);
        }
    }



    private static class insertrowinproductlist extends AsyncTask<Product, Void, Void> {

        private WordDao mAsyncTaskDao;

        insertrowinproductlist(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground( final Product... params) {
            mAsyncTaskDao.insertrowinproductlist(params[0]);
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

    private static class deleteallhis extends AsyncTask<Long, Void, Void> {

        private WordDao mAsyncTaskDao;

        deleteallhis(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Long... params) {
            mAsyncTaskDao.deleteAllHis();
            return null;
        }
    }

    private static class insertproducts extends AsyncTask< List<Product> , Void, Void> {

        private WordDao mAsyncTaskDao;

        insertproducts(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(List<Product>... params) {
            mAsyncTaskDao.insertproducts(params[0]);
            return null;
        }
    }



}
