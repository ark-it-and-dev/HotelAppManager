package br.metodista.hotelappmanager.activities;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.metodista.hotelappmanager.R;
import br.metodista.hotelappmanager.helper.NovoPedidoHelper;
import br.metodista.hotelappmanager.model.Produto;
import br.metodista.hotelappmanager.model.Usuario;
import br.metodista.hotelappmanager.webservice.ProdutoService;
import br.metodista.hotelappmanager.webservice.UsuarioService;

public class NovoPedido extends AppCompatActivity {
    private NovoPedidoHelper helper;

    private ProdutoService produtoService = new ProdutoService();
    private UsuarioService usuarioService = new UsuarioService();

    private Button btnGravarPedido;

    private List<Usuario> listaUsuario;
    private List<Produto> listaProduto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_pedido);

        helper = new NovoPedidoHelper(NovoPedido.this);

        btnGravarPedido = (Button) findViewById(R.id.btnGravarPedido);
        btnGravarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(listaUsuario == null) {
//                    Toast.makeText(NovoPedido.this, "lU null", Toast.LENGTH_SHORT).show();
//                }
                new SalvarPedido().execute();
                onResume();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        new BuscarDados<>().execute();
    }

    private class SalvarPedido extends AsyncTask<String, Void, List<Usuario>> {
        private ProgressDialog dialog;

        private boolean funcionou;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dialog = new ProgressDialog(NovoPedido.this);
            dialog.setMessage("Saving");
            dialog.show();
        }

        @Override
        protected List<Usuario> doInBackground(String... params) {
            Usuario usuario = listaUsuario.get(helper.getSelectedItemPositionUsuario());
            Produto produto = listaProduto.get(helper.getSelectedItemPositionProduto());

            if(usuarioService.delete(usuario.get_id())) {
//                Toast.makeText(NovoPedido.this, "Deletado", Toast.LENGTH_SHORT).show();
                Usuario usuarioAux = new Usuario();
                usuarioAux.setLogin(usuario.getLogin());
                usuarioAux.setSenha(usuario.getSenha());
                usuarioAux.setListaProduto(usuario.getListaProduto());

                usuarioAux.getListaProduto().add(produto);

                usuarioService.save(usuarioAux);

                funcionou = true;
            } else {
//                Toast.makeText(NovoPedido.this, "Nao deletado", Toast.LENGTH_SHORT).show();
                funcionou = false;
            }

//            usuario.set_id(null);

            return null;
        }

        @Override
        protected void onPostExecute(List<Usuario> produtos) {
            super.onPostExecute(produtos);

            dialog.dismiss();

            Toast.makeText(NovoPedido.this, "Salvo? " + funcionou, Toast.LENGTH_SHORT).show();
        }
    }

    private class BuscarDados<T> extends AsyncTask<String, Void, List<T>> {
        private ProgressDialog dialog;

        private List<String> listaNomeUsuario;
        private List<String> listaNomeProduto;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dialog = new ProgressDialog(NovoPedido.this);
            dialog.setMessage("Buscando Produtos");
            dialog.show();
        }

        @Override
        protected List<T> doInBackground(String... params) {
            listaProduto = produtoService.getAll();
            listaUsuario = usuarioService.getAll();

            listaNomeUsuario = new ArrayList<>();
            listaNomeProduto = new ArrayList<>();

            for(Usuario usuario : listaUsuario) {
                listaNomeUsuario.add(usuario.getLogin());
            }

            for(Produto produto : listaProduto) {
                listaNomeProduto.add(produto.getNome());
            }

            return null;
        }

        @Override
        protected void onPostExecute(List<T> produtos) {
            super.onPostExecute(produtos);

            helper.setSpinners(listaNomeUsuario, listaNomeProduto);

            dialog.dismiss();
        }
    }
}
