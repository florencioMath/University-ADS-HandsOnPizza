package br.com.mfds.ads_handsonworkvi_handsonpizza.webservice;

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class RetornarEnderecoPeloCep extends AsyncTask<Void, Void, DadosEndereco> {

    private String cep;

    public RetornarEnderecoPeloCep (String cep) {
        this.cep = cep;
    }

    @Override
    protected DadosEndereco doInBackground(Void... voids) {
        StringBuilder resposta = new StringBuilder();

        // Defini a string da URL do serviço
        String urlString = "https://viacep.com.br/ws/" + this.cep + "/json/";

        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setConnectTimeout(5000);
            connection.connect();
            Scanner scanner = new Scanner(url.openStream());
            while (scanner.hasNext()) {
                resposta.append(scanner.nextLine());
            }
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Utiliza a biblioteca GSON (definida no arquivo build.gradle do módulo) para auxiliar na recuperação dos valores do JSON
        Gson gson = new Gson();

        return gson.fromJson(resposta.toString(), DadosEndereco.class);
    }
}