package com.List.listimplementationsqlite.repository;

public class LocalDB {

    private static final String CLASS_NAME = "LocalDB";

    public static final String NAME = "isamm.db";

    public static final int VERSION = 1;

    public interface TablePersonne {

        String TABLE_NAME = "personne";

        /**
         * Primary key autoincrement
         */
        String COLUMN_ID = "_id";


        /**
         * Personne 's first name
         */
        String COLUMN_FIRST_NAME = "firstName";

        /**
         * Personne 's last name
         */
        String COLUMN_LAST_NAME = "lastName";

        /**
         * Personne 's email
         */
        String COLUMN_EMAIL = "email";

        /**
         * Personne 's age
         */
        String COLUMN_AGE = "age";

        /**
         * Personne 's phone number
         */
        String COLUMN_PHONE_NUMBER = "phoneNumber";

        /**
         * Personne 's photo uri
         */
        String COLUMN_PHOTO_uri = "photoUri";

    }


    public static final String create_table_Personne = "create table " + TablePersonne.TABLE_NAME + "("
            + TablePersonne.COLUMN_ID + " integer primary key autoincrement,"
            + TablePersonne.COLUMN_FIRST_NAME + " text not null,"
            + TablePersonne.COLUMN_LAST_NAME + " text not null,"
            + TablePersonne.COLUMN_EMAIL + " text not null,"
            + TablePersonne.COLUMN_PHONE_NUMBER + " text,"
            + TablePersonne.COLUMN_AGE + " text,"
            + TablePersonne.COLUMN_PHOTO_uri + " text not null"
            + ");";
}
