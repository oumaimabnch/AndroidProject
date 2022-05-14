package com.List.listimplementationsqlite.repository.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.List.listimplementationsqlite.entity.Personne;
import com.List.listimplementationsqlite.entity.Personne;
import com.List.listimplementationsqlite.repository.LocalDB;

import java.util.ArrayList;
import java.util.List;


public class LocalPersonneDao extends BaseDao {

    private static final String CLASS_NAME = "LocalPersonneDao";

    private String[] allColumns = {LocalDB.TablePersonne.COLUMN_ID,
            LocalDB.TablePersonne.COLUMN_FIRST_NAME,
            LocalDB.TablePersonne.COLUMN_LAST_NAME,
            LocalDB.TablePersonne.COLUMN_EMAIL,
            LocalDB.TablePersonne.COLUMN_PHONE_NUMBER,
            LocalDB.TablePersonne.COLUMN_AGE,
            LocalDB.TablePersonne.COLUMN_PHOTO_uri};


    public long addPersonne(Context context, Personne personne) {
        ContentValues values = new ContentValues();
        values.put(LocalDB.TablePersonne.COLUMN_FIRST_NAME, personne.getmFirstName());
        values.put(LocalDB.TablePersonne.COLUMN_LAST_NAME, personne.getmLastName());
        values.put(LocalDB.TablePersonne.COLUMN_EMAIL, personne.getmEmail());
        values.put(LocalDB.TablePersonne.COLUMN_PHONE_NUMBER, personne.getmPhoneNumber());
        values.put(LocalDB.TablePersonne.COLUMN_AGE, personne.getmAge());
        values.put(LocalDB.TablePersonne.COLUMN_PHOTO_uri, "");


        long insertId = getWritableDatabase(context).insert(LocalDB.TablePersonne.TABLE_NAME, null,
                values);
        return insertId;
    }

    public List<Personne> getAllPersonnes(Context context) {
        List<Personne> personneList = new ArrayList<Personne>();

        Cursor cursor = getReadableDatabase(context).query(LocalDB.TablePersonne.TABLE_NAME,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Personne personne = changeCursorToPersonne(cursor);
            personneList.add(personne);
            cursor.moveToNext();
        }
        // assurez-vous de la fermeture du curseur
        cursor.close();
        return personneList;
    }

    private Personne changeCursorToPersonne(Cursor cursor) {
        Personne personne = new Personne();
        personne.setmFirstName(cursor.getString(cursor.getColumnIndexOrThrow(LocalDB.TablePersonne.COLUMN_FIRST_NAME)));
        personne.setmLastName(cursor.getString(cursor.getColumnIndexOrThrow(LocalDB.TablePersonne.COLUMN_LAST_NAME)));
        personne.setmEmail(cursor.getString(cursor.getColumnIndexOrThrow(LocalDB.TablePersonne.COLUMN_EMAIL)));
        personne.setmPhoneNumber(cursor.getString(cursor.getColumnIndexOrThrow(LocalDB.TablePersonne.COLUMN_PHONE_NUMBER)));
        personne.setmAge(Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(LocalDB.TablePersonne.COLUMN_AGE))));
        return personne;
    }

}
