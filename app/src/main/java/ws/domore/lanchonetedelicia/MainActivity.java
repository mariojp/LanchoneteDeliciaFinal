package ws.domore.lanchonetedelicia;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.*;
import cz.msebera.android.httpclient.Header;

import ws.domore.lanchonetedelicia.ProdutoContract.ProdutoEntry;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ProdutoCursorAdapter adapter;
    Produto[] produtos;
    private ProdutoDbHelper produtoDbHelper = new ProdutoDbHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = (TextView)this.findViewById(R.id.text_view_title);
        textView.setText(R.string.products_title);

//        final ArrayList<String> produtoList = new ArrayList<String>();
//
//        produtoList.add("Suco Onda Tropical");
//        produtoList.add("Vitamina Planetaria");
//        produtoList.add("Hamburguer Exagerado");
//        produtoList.add("Pastel Super");
//        produtoList.add("Empada Olho Grande");
//        produtoList.add("Boliviado Quente");
//        produtoList.add("Quibe POP");
//        produtoList.add("Esfirra do Sabor");
//        produtoList.add("Crepioca Saborosa");
//        produtoList.add("Pao de Nuvem");
//        produtoList.add("Bruschetta Integral");
//        produtoList.add("Banana chips");
//        produtoList.add("Sopa Funcional");
//        produtoList.add("Sanduche Natureba");
//        produtoList.add("Salada  Surpresa");



        SQLiteDatabase db = produtoDbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM produto", null);
        adapter = new ProdutoCursorAdapter(this, cursor);



        ListView listView = (ListView)this.findViewById(R.id.list_view_produtos);

        listView.setAdapter(adapter);

        Context context = this;
        String text = "Olá Toast!";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent detalheActivity = new Intent(MainActivity.this,DetalheActivity.class);
                detalheActivity.putExtra("posicao", i);
                startActivity(detalheActivity);
            }
        });
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("https://patra-backend.appspot.com/produtos",
                new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        Log.d("AsyncHttpClient", "onFailure response = " + responseString);
                        Log.d("AsyncHttpClient",throwable.toString());
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String responseString) {
                        Log.d("AsyncHttpClient", "onSuccess response = " + responseString);
                        Gson gson = new GsonBuilder().create();
                        produtos = gson.fromJson(responseString, Produto[].class);

                        addProdutoToDB(produtos);

                        SQLiteDatabase db = produtoDbHelper.getWritableDatabase();
                        Cursor cursor = db.rawQuery("SELECT * FROM produto", null);
                        adapter.changeCursor(cursor);
                    }
                });
    }

    private void addProdutoToDB(Produto[] produtos) {
        SQLiteDatabase db = produtoDbHelper.getWritableDatabase();
        for(Produto p : produtos){
            ContentValues values = new ContentValues();
            values.put(ProdutoEntry.COLUNA_NOME_NOME, p.getNome());
            values.put(ProdutoEntry.COLUNA_NOME_PRECO, p.getPreco());
            values.put(ProdutoEntry.COLUNA_NOME_DESCRICAO, p.getDescricao());
            values.put(ProdutoEntry.COLUNA_NOME_IMAGEM, p.getImagem());
            db.insert(ProdutoEntry.TABELA_NOME, null, values);

        }
    }


}
