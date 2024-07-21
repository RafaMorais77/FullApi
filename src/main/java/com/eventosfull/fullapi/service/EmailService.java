package com.eventosfull.fullapi.service;

import org.springframework.stereotype.Service;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.DataOutputStream;

@Service
public class EmailService {

    public void enviarEmail(String destinatario, String assunto, String mensagem) {
        try {
            // URL da API
            URL url = new URL("https://natividade.xyz/api/send-email");

            // Abrir uma conexão HTTP
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            // Configurar o método da requisição e os headers
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);

            
            String jsonInputString = "{\"to\": \"" + destinatario + "\", \"subject\": \"" + assunto + "\", \"message\": \"" + mensagem + "\"}";
            byte[] input = jsonInputString.getBytes("utf-8");
            con.setFixedLengthStreamingMode(input.length);

            try (DataOutputStream out = new DataOutputStream(con.getOutputStream())) {
                out.write(input);
                out.flush();
            }

            int status = con.getResponseCode();
            if (status == HttpURLConnection.HTTP_OK) {
                System.out.println("Email enviado com sucesso para: " + destinatario);
            } else {
                System.out.println("Falha ao enviar email para: " + destinatario + ". Código de resposta: " + status);
            }

            con.disconnect();

        } catch (Exception e) {
            System.err.println("Erro ao enviar email para " + destinatario + ": " + e.getMessage());
        }
    }
}
