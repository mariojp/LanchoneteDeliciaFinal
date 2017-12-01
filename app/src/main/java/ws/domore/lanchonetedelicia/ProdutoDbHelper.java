package ws.domore.lanchonetedelicia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mariojp on 01/12/2017.
 */

public class ProdutoDbHelper extends SQLiteOpenHelper {



    public ProdutoDbHelper(Context context){
        super(context,ProdutoContract.DB_NOME,null,ProdutoContract.DB_VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(ProdutoContract.SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(ProdutoContract.SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);
    }
}
