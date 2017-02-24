package com.fornari.anonymousru;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class CardapioActivity extends AppCompatActivity {

    private ListView listViewCardapio;
    private ArrayAdapter<String> arrayAdapterCardapio;
    private TextView textViewData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardapio);

        int layoutAdapterCardapio = android.R.layout.simple_list_item_1;
        arrayAdapterCardapio = new ArrayAdapter<String>(this, layoutAdapterCardapio);

        listViewCardapio = (ListView)findViewById(R.id.listViewCardapio);
        textViewData = (TextView)findViewById(R.id.textViewData);

        Bundle bundle = getIntent().getExtras();

        if (bundle!=null && bundle.containsKey("CONEXAO")){
            boolean conexao = (boolean)bundle.getSerializable("CONEXAO");
            if(conexao){
                if(bundle!=null && bundle.containsKey("CONTEUDO")){
                    ArrayList<String> lista = (ArrayList<String>)bundle.getSerializable("CONTEUDO");
                    for (String s: lista){
                        if(lista.indexOf(s)==0){
                            textViewData.setText(s);
                        }
                        else{
                            arrayAdapterCardapio.add(s);
                        }
                    }
                    listViewCardapio.setAdapter(arrayAdapterCardapio);
                }
            }
            else{
                textViewData.setText("Não foi possivel carregar o cardápio");
            }
        }
    }
}
