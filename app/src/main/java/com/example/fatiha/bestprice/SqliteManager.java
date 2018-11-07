package com.example.fatiha.bestprice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SqliteManager extends SQLiteOpenHelper {


    // Logcat tag
    private static final String LOG = "DatabaseHelper";


    // Database Version
    private static final int DATABASE_VERSION = 1;


    // Database Name
    public static final String DATABASE_NAME = "BestPriceFinder_DataBase";



    // LISTE Table - column name
    private static final String TABLE_LISTES = "Listes";

    private static final String LISTE_NOM = "nom";
    private static final String LISTE_DESCRIPTION = "description";
    private static final String LISTE_DATE_CREATION = "date_creation";



    // ARTICLES Table - column name
    private static final String TABLE_ARTICLES = "Articles";
    private static final String ARTICLE_ID_LISTE  = "_idListeAchat";
    private static final String ARTICLE_UPC  = "UPC";
    private static final String ARTICLE_NOM = "nom";
    private static final String ARTICLE_DESCRIPTION = "description";
    private static final String ARTICLE_PRIX  = "prix";
    private static final String ARTICLE_EN_SPECIAL_PRIX  = "en_Special_Prix";
    private static final String ARTICLE_EN_SPECIAL_DETAILS  = "en_Special_Details";
    private static final String ARTICLE_EN_SPECIAL_DATE_DEBUT  = "en_Special_Date_Debut";
    private static final String ARTICLE_EN_SPECIAL_DATE_FIN  = "en_Special_Date_Fin";
    private static final String ARTICLE_FORMAT  = "format";
    private static final String ARTICLE_ORIGINE  = "origine";
    private static final String ARTICLE_MAGASIN  = "magasin";
    private static final String ARTICLE_STATUS  = "status";
    private static final String ARTICLE_FAVORITE  = "favorite";
    private static final String ARTICLE_PHOTO  = "photo";
    private static final String ARTICLE_DATE_CREATION = "date_creation";




    public SqliteManager(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*
         * Creation de la base de données SQLite, la table 'settings si n'existe pas
         * alors le fichier SQlite sera créé
         */
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_LISTES + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                LISTE_NOM + " TEXT, " +
                LISTE_DESCRIPTION + " TEXT, " +
                LISTE_DATE_CREATION + " TEXT)";
        db.execSQL(sql);



        String sqlSpeedLimitCache = "CREATE TABLE IF NOT EXISTS " + TABLE_ARTICLES + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ARTICLE_ID_LISTE + " INTEGER, " +
                ARTICLE_UPC + " TEXT, " +
                ARTICLE_NOM + " TEXT, " +
                ARTICLE_DESCRIPTION + " TEXT, " +
                ARTICLE_PRIX + " TEXT, " +
                ARTICLE_EN_SPECIAL_PRIX + " TEXT, " +
                ARTICLE_EN_SPECIAL_DETAILS + " TEXT, " +
                ARTICLE_EN_SPECIAL_DATE_DEBUT + " TEXT, " +
                ARTICLE_EN_SPECIAL_DATE_FIN + " TEXT, " +
                ARTICLE_FORMAT + " TEXT, " +
                ARTICLE_ORIGINE + " TEXT, " +
                ARTICLE_MAGASIN + " TEXT, " +
                ARTICLE_STATUS + " TEXT, " +
                ARTICLE_FAVORITE + " TEXT, " +
                ARTICLE_PHOTO + " TEXT, " +
                ARTICLE_DATE_CREATION + " TEXT)";
        db.execSQL(sqlSpeedLimitCache);


    }

    //************************************************************************
    //Insertion d'une LISTE D'ACHAT
    //************************************************************************
    public boolean insertListeAchat(String input_nom, String input_description, String input_date_creation) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(LISTE_NOM, input_nom);
        contentValues.put(LISTE_DESCRIPTION, input_description);
        contentValues.put(LISTE_DATE_CREATION, input_date_creation);

        long result = db.insert(TABLE_LISTES, null, contentValues);

        //si la requete sait bien terminé avec succès retour vrai.
        if (result == -1)
            return false;
        else
            return true;
    }


    //************************************************************************
    //Insertion d'un ARTICLE DANS UNE LISTE
    //************************************************************************
    public boolean insertArticle(String input_id_liste, String input_UPC, String input_nom, String input_description,String input_prix, String input_special_prix,
                                 String input_special_details, String input_special_date_debut, String input_special_fin, String input_format,
                                 String input_origine, String input_magasin, String input_status, String input_favorite, String input_photo,
                                 String input_date_creation) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ARTICLE_ID_LISTE, input_id_liste);
        contentValues.put(ARTICLE_UPC, input_UPC);
        contentValues.put(ARTICLE_NOM, input_nom);
        contentValues.put(ARTICLE_DESCRIPTION, input_description);
        contentValues.put(ARTICLE_PRIX, input_prix);
        contentValues.put(ARTICLE_EN_SPECIAL_PRIX, input_special_prix);
        contentValues.put(ARTICLE_EN_SPECIAL_DETAILS, input_special_details);
        contentValues.put(ARTICLE_EN_SPECIAL_DATE_DEBUT, input_special_date_debut);
        contentValues.put(ARTICLE_EN_SPECIAL_DATE_FIN, input_special_fin);
        contentValues.put(ARTICLE_FORMAT, input_format);
        contentValues.put(ARTICLE_ORIGINE, input_origine);
        contentValues.put(ARTICLE_MAGASIN, input_magasin);
        contentValues.put(ARTICLE_STATUS, input_status);
        contentValues.put(ARTICLE_FAVORITE, input_favorite);
        contentValues.put(ARTICLE_PHOTO, input_photo);
        contentValues.put(ARTICLE_DATE_CREATION, input_date_creation);

        long result = db.insert(TABLE_ARTICLES, null, contentValues);

        //si la requete sait bien terminé avec succès retour vrai.
        if (result == -1)
            return false;
        else
            return true;
    }



    //************************************************************************
    //Obtenir la liste des ARTICLES d'une LISTE d'ACHAT(par ID)
    //************************************************************************
    public Cursor ObtenirArticleByListe(int liste_achat_id) {
        SQLiteDatabase bd = this.getWritableDatabase();

        String requette = "select * from " + TABLE_ARTICLES +
                " where" + ARTICLE_ID_LISTE +  "id ='" + liste_achat_id + "'";


        Cursor results = bd.rawQuery(requette, null);
        //   Log.v("resultat**************", "" + results);
        return results;
    }





    //************************************************************************
    //Obtenir la totalité des LISTES d'ACHATS
    //************************************************************************
    public Cursor ListeAchatsGetAllData() {
        SQLiteDatabase bd = this.getWritableDatabase();
        String requete = "select * from " + TABLE_LISTES;
        Cursor results = bd.rawQuery(requete, null);
        return results;
    }



    //************************************************************************
    //Obtenir la totalité des ARTICLES
    //************************************************************************
    public Cursor ArticlesGetAllData() {
        SQLiteDatabase bd = this.getWritableDatabase();
        String requete = "select * from " + TABLE_ARTICLES;
        Cursor results = bd.rawQuery(requete, null);
        return results;

    }




    //************************************************************************
    //Verifier si la table LISTE d'ACHATS est vide
    //************************************************************************
    public boolean ListeAchats_isEmpty(){


        SQLiteDatabase db = this.getWritableDatabase();
        boolean empty = true;
        Cursor cur = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_LISTES, null);
        if (cur != null && cur.moveToFirst()) {
            empty = (cur.getInt (0) == 0);
        }
        cur.close();

        return empty;
    }




    //************************************************************************
    //Verifier si la table ARTICLES est vide
    //************************************************************************
    public boolean Articles_isEmpty(){


        SQLiteDatabase db = this.getWritableDatabase();
        boolean empty = true;
        Cursor cur = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_ARTICLES, null);
        if (cur != null && cur.moveToFirst()) {
            empty = (cur.getInt (0) == 0);
        }
        cur.close();

        return empty;
    }






    /*public boolean updateDataSpeedLimitCachingSettings(String id, String input_ProviderName, int input_CahingDurationMinutes) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        //contentValues.put("_id",id);
        contentValues.put("ProviderName", input_ProviderName);
        contentValues.put("CahingDurationMinutes", input_CahingDurationMinutes);
        long result = db.update("SpeedLimitCachingSettings", contentValues, "_id = ?",new String[] {id});
        if(result == -1)
            return false;
        else
            return true;
    }
*/





/*

    public boolean updateData(String id,String input_speedLimitsProvider, int input_speedIntervalAccuracy, int input_speedIntervalSeconds, String input_notificationType) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        //contentValues.put("_id",id);
        contentValues.put(COL_1,input_speedLimitsProvider);
        contentValues.put(COL_2, input_speedIntervalAccuracy);
        contentValues.put(COL_3,input_speedIntervalSeconds);
        contentValues.put(COL_4, input_notificationType);
        long result = db.update(TABLE_NAME, contentValues, "_id = ?",new String[] {id});
        if(result == -1)
            return false;
        else
            return true;
    }*/






/*

    //***********************************************************************
    //Obtenir tout la liste des remembers avec une date nom passée et avec Repeat=YES
    //************************************************************************
    public Cursor ActiveRemembersOnly() {
        SQLiteDatabase bd = this.getWritableDatabase();

        String requette = "select * from " + TABLE_NAME +
                " where datetime >= Datetime('now', '-4 hours') or repeat = 'YES'";


        Cursor results = bd.rawQuery(requette, null);
        //   Log.v("resultat**************", "" + results);
        return results;
    }*/


 /*   //***********************************************************************
    //Obtenir tout la liste des remembers avec une date nom passée et avec Repeat=YES
    //************************************************************************
    public Cursor ObtenirDatetimeByID(int id) {
        SQLiteDatabase bd = this.getWritableDatabase();

        String requette = "select * from " + TABLE_NAME +
                " where _id ='" + id + "'";


        Cursor results = bd.rawQuery(requette, null);
        //   Log.v("resultat**************", "" + results);
        return results;
    }*/



   /* final public Integer deleteDataSpeedLimitCachingSettings(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("SpeedLimitCachingSettings", "_id = ?", new String[]{id});
    }
*/


    /*final public Integer deleteDataSpeedLimitCache(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("SpeedLimitCache", "_id = ?", new String[]{id});
    }*/




    /*final public Integer deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "_id = ?", new String[]{id});
    }*/

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS employees");
        onCreate(db);
    }

}