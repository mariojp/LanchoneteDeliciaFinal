package ws.domore.lanchonetedelicia;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import ws.domore.lanchonetedelicia.ProdutoContract.ProdutoEntry;

import com.squareup.picasso.Picasso;

public class DetalheActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);

        Intent intent = DetalheActivity.this.getIntent();
        if (intent.hasExtra("posicao" )){
            int posicao = intent.getIntExtra("posicao",0);

            ProdutoDbHelper produtoDbHelper = new ProdutoDbHelper(this);
            SQLiteDatabase db = produtoDbHelper.getWritableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM produto", null);
            cursor.moveToPosition(posicao);

            String produtoNome  = cursor.getString(cursor.getColumnIndexOrThrow(
                    ProdutoEntry.COLUNA_NOME_NOME
            ));
            String produtoPreco = cursor.getString(cursor.getColumnIndexOrThrow(
                    ProdutoEntry.COLUNA_NOME_PRECO));
            String produtoImagem = cursor.getString(cursor.getColumnIndexOrThrow(
                    ProdutoEntry.COLUNA_NOME_IMAGEM));
            String produtoDescricao  = cursor.getString(cursor.getColumnIndexOrThrow(
                    ProdutoEntry.COLUNA_NOME_DESCRICAO));



            ((TextView) DetalheActivity.this.findViewById(R.id.text_view_name)).setText(produtoNome);
            ((TextView) DetalheActivity.this.findViewById(R.id.text_view_price))
                    .setText(produtoPreco);
            ((TextView) DetalheActivity.this.findViewById(R.id.text_view_desc))
                    .setText(produtoDescricao);
            Picasso.with(DetalheActivity.this).load(produtoImagem).into(
                    (ImageView) DetalheActivity.this.findViewById(R.id.image_view_produto));

        }

    }
}
