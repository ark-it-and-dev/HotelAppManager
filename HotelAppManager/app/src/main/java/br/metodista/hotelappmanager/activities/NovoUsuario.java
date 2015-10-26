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

import java.util.List;

import br.metodista.hotelappmanager.R;
import br.metodista.hotelappmanager.model.Usuario;
import br.metodista.hotelappmanager.webservice.UsuarioService;

public class NovoUsuario extends AppCompatActivity {
    private UsuarioService service = new UsuarioService();

    private Usuario usuario = Usuario.getInstance();

    private EditText txtUsuario;
    private TextView txtSenha;
    private Button btnSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_usuario);

        txtUsuario = (EditText) findViewById(R.id.txtNome);
        txtSenha = (TextView) findViewById(R.id.txtSenha);

        String login = txtUsuario.getText().toString();
        String senha = txtSenha.getText().toString();

        usuario.setLogin(login);
        usuario.setSenha(senha);

        btnSalvar = (Button) findViewById(R.id.btnSalvar);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SalvarUsuario().execute();
            }
        });
    }

    private class SalvarUsuario extends AsyncTask<String, Void, List<Usuario>> {
        private ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dialog = new ProgressDialog(NovoUsuario.this);
            dialog.show();
        }

        @Override
        protected List<Usuario> doInBackground(String... params) {
            if(service.save(usuario)) {
                Toast.makeText(NovoUsuario.this, "Usuario Salvo com Sucesso!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(NovoUsuario.this, "Falha ao Salvar o Usuário", Toast.LENGTH_SHORT).show();
            }

            return null;
        }

        @Override
        protected void onPostExecute(List<Usuario> usuarios) {
            super.onPostExecute(usuarios);

            dialog.dismiss();
        }
    }
}
