package ws.domore.lanchonetedelicia;

import android.provider.BaseColumns;

/**
 * Created by mariojp on 27/11/2017.
 */

public class ProdutoContract {

    public static final String DB_NOME = "produto.db";
    public static final int DB_VERSAO = 1;
    public static final String SQL_CREATE_ENTRIES = "CREATE TABLE " +
            ProdutoEntry.TABELA_NOME + " (" +
            ProdutoEntry._ID + " INTEGER PRIMARY KEY," +
            ProdutoEntry.COLUNA_NOME_NOME + " TEXT," +
            ProdutoEntry.COLUNA_NOME_PRECO + " TEXT," +
            ProdutoEntry.COLUNA_NOME_DESCRICAO + " TEXT," +
            ProdutoEntry.COLUNA_NOME_IMAGEM + " TEXT)";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + ProdutoEntry.TABELA_NOME;

    public static class ProdutoEntry implements BaseColumns {

        public static final String TABELA_NOME = "produto";
        public static final String COLUNA_NOME_NOME = "nome";
        public static final String COLUNA_NOME_PRECO = "preco";
        public static final String COLUNA_NOME_DESCRICAO = "descricao";
        public static final String COLUNA_NOME_IMAGEM = "imagem";


    }
}
