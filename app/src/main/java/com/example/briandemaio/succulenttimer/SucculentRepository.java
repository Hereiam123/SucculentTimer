package com.example.briandemaio.succulenttimer;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class SucculentRepository {
    private SucculentDao mSucculentDao;

    private LiveData<List<Succulent>> mAllSucculents;
    SucculentRepository(Application application) {
        SucculentRoomDatabase db = SucculentRoomDatabase.getDatabase(application);
        mSucculentDao = db.succulentDao();
        mAllSucculents = mSucculentDao.getAllSucculents();
    }

    LiveData<List<Succulent>> getAllSucculents() {
        return mAllSucculents;
    }

    public void insert (Succulent succulent) {
        new insertAsyncTask(mSucculentDao).execute(succulent);
    }

    public void delete(Succulent succulent)  {
        new deleteAsyncTask(mSucculentDao).execute(succulent);
    }

    private static class insertAsyncTask extends AsyncTask<Succulent, Void, Void> {

        private SucculentDao mAsyncTaskDao;

        insertAsyncTask(SucculentDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Succulent... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Succulent, Void, Void> {
        private SucculentDao mAsyncTaskDao;

        deleteAsyncTask(SucculentDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Succulent... params) {
            mAsyncTaskDao.deleteSucculent(params[0]);
            return null;
        }
    }
}
