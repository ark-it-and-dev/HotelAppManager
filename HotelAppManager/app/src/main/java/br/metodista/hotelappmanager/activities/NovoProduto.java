package br.metodista.hotelappmanager.activities;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import br.metodista.hotelappmanager.R;
import br.metodista.hotelappmanager.enumeration.CategoriaProduto;
import br.metodista.hotelappmanager.model.Produto;
import br.metodista.hotelappmanager.webservice.ProdutoService;

public class NovoProduto extends AppCompatActivity {

    private ProdutoService service = new ProdutoService();

    private EditText txtNome;
    private EditText txtDescricao;
    private Spinner spinnerCategoria;
    private EditText txtPreco;
    private Button btnSalvar;

    private Produto produto = Produto.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_produto);

        txtNome = (EditText) findViewById(R.id.txtNome);
        txtDescricao = (EditText) findViewById(R.id.txtDescricao);
        spinnerCategoria = (Spinner) findViewById(R.id.spinner);
        txtPreco = (EditText) findViewById(R.id.txtPreco);

        String nome = txtNome.getText().toString();
        String descricao = txtDescricao.getText().toString();
        int categoriaSelecionada = spinnerCategoria.getSelectedItemPosition();
        double preco = Double.valueOf(txtPreco.getText().toString());

        produto.setNome(nome);
        produto.setDescricao(descricao);
        switch (categoriaSelecionada) {
            case 0:
                produto.setCategoria(CategoriaProduto.PRATO);
                break;

            case 1:
                produto.setCategoria(CategoriaProduto.BEBIDA);
                break;
        }
        produto.setPreco(preco);

        btnSalvar = (Button) findViewById(R.id.btnSalvar);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SalvarProduto().execute();
            }
        });
    }

    private class SalvarProduto extends AsyncTask<String, Void, List<Produto>> {
        private ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dialog = new ProgressDialog(NovoProduto.this);
            dialog.setMessage("Saving");
            dialog.show();
        }

        @Override
        protected List<Produto> doInBackground(String... params) {
            if (service.save(produto)) {
                Toast.makeText(NovoProduto.this, "Produto Salvo com Sucesso!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(NovoProduto.this, "Falha ao Salvar!", Toast.LENGTH_SHORT).show();
            }

            return null;
        }

        @Override
        protected void onPostExecute(List<Produto> produtos) {
            super.onPostExecute(produtos);

            dialog.dismiss();
        }
    }
}
