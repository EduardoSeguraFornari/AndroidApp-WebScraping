package com.fornari.anonymousru;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Thread thread = new Thread(new Thread(new Runnable() {
            public void run() {
                String siteUrl = "http://www.restaurantesaborfamilia.com.br/cardapio-ru-pucrs/";
                try {
                    Connection connection = Jsoup.connect(siteUrl);
                    Document document = connection.get();
                    Elements cardapio = document.select("p");
                    Elements comidas = cardapio.select("p");
                    ArrayList<String> lista = new ArrayList<String>();
                    for (int i = 0; i < comidas.size(); i++) {

                        if (i == comidas.size() - 3) {
                            break;
                        } else {
                            lista.add(comidas.get(i).text().toString());
                        }
                    }
                    Intent intent = new Intent(MainActivity.this, CardapioActivity.class);
                    intent.putExtra("CONTEUDO", lista);
                    intent.putExtra("CONEXAO", true);
                    startActivityForResult(intent, 0);
                    finish();
                } catch (Exception e) {
                    Intent intent = new Intent(MainActivity.this, CardapioActivity.class);
                    intent.putExtra("CONEXAO", false);
                    startActivityForResult(intent, 0);
                    finish();
                }
            }
        }));
        thread.start();
    }
}
