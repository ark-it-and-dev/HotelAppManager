package br.metodista.hotelappmanager.webservice;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.metodista.hotelappmanager.model.Produto;

/**
 * Created by Gustavo Assalin on 26/10/2015.
 */
public class ProdutoService {
    private static final String URL = "https://openws.herokuapp.com/hotelappprodutos";
    private static final String API_KEY = "b4111c92d8a0f3d61f3cfd87e9a4eb75";

    private HttpURLConnection urlConnection = null;

    public List<Produto> getAll() {
        List<Produto> produtos = new ArrayList<>();

        try {
            java.net.URL url = new URL(URL + API_KEY);
            urlConnection = (HttpURLConnection) url.openConnection();

            InputStream in =  new BufferedInputStream(urlConnection.getInputStream());
            Scanner s = new Scanner(in);
            String conteudo = s.useDelimiter("\\A").next();

            Gson gson = new Gson();
            produtos = gson.fromJson(conteudo, new TypeToken<List<Produto>>(){}.getType());
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            urlConnection.disconnect();
        }

        return produtos;
    }

    public boolean save(Object produto) {
        boolean retorno = false;

        try {
            URL url = new URL(URL + API_KEY);

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("Accept", "application/json");

            OutputStream out = new BufferedOutputStream(urlConnection.getOutputStream());

            Writer w = new BufferedWriter(new OutputStreamWriter(out));
            Gson gson = new Gson();
            String json = gson.toJson(produto);
            w.write(json);
            w.close();

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());

            Scanner s = new Scanner(in);
            String conteudo = s.nextLine();

            in.close();
            retorno = true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            urlConnection.disconnect();
        }

        return retorno;
    }

    public void delete(String id) {
        HttpURLConnection urlConnection = null;

        try {
            URL url = new URL(URL + "/" + id + API_KEY);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("DELETE");
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            urlConnection.disconnect();
        }
    }
}
