package com.briandemaio.succulenttimer;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class SucculentViewModel extends AndroidViewModel {
    private SucculentRepository mRepository;

    private LiveData<List<Succulent>> mAllSucculents;

    public SucculentViewModel (Application application) {
        super(application);
        mRepository = new SucculentRepository(application);
        mAllSucculents = mRepository.getAllSucculents();
    }

    LiveData<List<Succulent>> getAllSucculents() { return mAllSucculents; }

    public void insert(Succulent succulent) { mRepository.insert(succulent); }

    public void delete(Succulent succulent) { mRepository.delete(succulent); }

    public void update(Succulent succulent) { mRepository.update(succulent); }
}
