package br.metodista.hotelappmanager.activities;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.metodista.hotelappmanager.R;
import br.metodista.hotelappmanager.enumeration.CategoriaProduto;
import br.metodista.hotelappmanager.model.Produto;
import br.metodista.hotelappmanager.model.Usuario;
import br.metodista.hotelappmanager.webservice.UsuarioService;

public class NovoUsuario extends AppCompatActivity {
    private UsuarioService service = new UsuarioService();

    private Usuario usuario = new Usuario();

    private EditText txtUsuario;
    private TextView txtSenha;
    private Button btnSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_usuario);

        txtUsuario = (EditText) findViewById(R.id.txtNome);
        txtSenha = (TextView) findViewById(R.id.txtSenha);

        txtSenha.setText(sortearSenha());

        btnSalvar = (Button) findViewById(R.id.btnSalvar);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario.setLogin(txtUsuario.getText().toString());
                usuario.setSenha(txtSenha.getText().toString());

                List<Produto> lista = new ArrayList<Produto>();

                usuario.setListaProduto(lista);

                new SalvarUsuario().execute();

                txtUsuario.setText("");
                txtSenha.setText(sortearSenha());
            }
        });
    }

    private String sortearSenha() {
        char[] caracteresSenha =
                {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'w', 'x', 'y', 'z',
                        '1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};

        Random r = new Random();
        String sorteio = "";

        for(int contador = 0; contador <= 7; contador++) {
            sorteio += caracteresSenha[r.nextInt(34)];
        }

        return sorteio;
    }

    private class SalvarUsuario extends AsyncTask<String, Void, List<Usuario>> {
        private ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dialog = new ProgressDialog(NovoUsuario.this);
            dialog.setMessage("Salvando");
            dialog.show();
        }

        @Override
        protected List<Usuario> doInBackground(String... params) {
            service.save(usuario);

            return null;
        }

        @Override
        protected void onPostExecute(List<Usuario> usuarios) {
            super.onPostExecute(usuarios);

            dialog.dismiss();

            Toast.makeText(NovoUsuario.this, "Usuario Salvo com Sucesso!", Toast.LENGTH_SHORT).show();
        }
    }
}
