package com.List.listimplementationsqlite.repository.provider;

import android.content.Context;

import com.List.listimplementationsqlite.entity.Personne;
import com.List.listimplementationsqlite.entity.Personne;
import com.List.listimplementationsqlite.repository.dao.LocalPersonneDao;

import java.util.List;


public class PersonneDataProvider {

    private static final String CLASS_NAME = "PersonneDataProvider";

    /**
     * Singleton instance
     */
    private static PersonneDataProvider mInstance;

    private LocalPersonneDao mLocalPersonneDao;

    private PersonneDataProvider() {
        mLocalPersonneDao = new LocalPersonneDao();
    }

    public static PersonneDataProvider getInstance() {
        synchronized (PersonneDataProvider.class) {
            if (mInstance == null) {
                mInstance = new PersonneDataProvider();
            }
        }

        return mInstance;
    }

    public List<Personne> getAllPersonnes(Context context) {
        return mLocalPersonneDao.getAllPersonnes(context);
    }

    public long addPersonne(Context context, Personne personne) {
        return mLocalPersonneDao.addPersonne(context, personne);
    }

}