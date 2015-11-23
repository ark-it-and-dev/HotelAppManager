package br.metodista.hotelappmanager.helper;

import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.List;

import br.metodista.hotelappmanager.R;
import br.metodista.hotelappmanager.model.Produto;
import br.metodista.hotelappmanager.model.Usuario;

/**
 * Created by Gustavo Assalin on 21/11/2015.
 */
public class NovoPedidoHelper {
    private Activity activity;

    private Spinner spinnerUsuario;
    private Spinner spinnerProduto;

    public NovoPedidoHelper(Activity activity) {
        this.activity = activity;

        spinnerUsuario = (Spinner) activity.findViewById(R.id.spinnerUsuario);
        spinnerProduto = (Spinner) activity.findViewById(R.id.spinnerProduto);
    }

    public int getSelectedItemPositionUsuario() {
        return spinnerUsuario.getSelectedItemPosition();
    }

    public int getSelectedItemPositionProduto() {
        return spinnerProduto.getSelectedItemPosition();
    }

    public void setSpinners(List<String> listaNomeUsuario, List<String> listaNomeProduto) {
        if (listaNomeUsuario != null) {
            ArrayAdapter<String> spinnerArrayUsuarios = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_dropdown_item, listaNomeUsuario);
            spinnerArrayUsuarios.setDropDownViewResource(android.R.layout.simple_spinner_item);
            spinnerUsuario.setAdapter(spinnerArrayUsuarios);
        }

        if (listaNomeProduto != null) {
            ArrayAdapter<String> spinnerArrayProduto = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_dropdown_item, listaNomeProduto);
            spinnerArrayProduto.setDropDownViewResource(android.R.layout.simple_spinner_item);
            spinnerProduto.setAdapter(spinnerArrayProduto);
        }
    }
}