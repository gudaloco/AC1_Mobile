package com.example.aula_mobile;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivityAC1 extends AppCompatActivity {

    private EditText editTextTitulo, editTextAutor;
    private CheckBox checkBoxVisto;
    private Button btnAdicionar;
    private ListView listViewLivros;

    private ArrayList<String> listaLivros;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_ac1);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editTextTitulo = findViewById(R.id.editTextTitulo);
        editTextTitulo.setHint("Título do livro");

        editTextAutor = findViewById(R.id.editTextAutor);
        editTextAutor.setHint("Nome do Autor");

        checkBoxVisto = findViewById(R.id.checkVisto);
        btnAdicionar = findViewById(R.id.buttonAdicionar);
        listViewLivros = findViewById(R.id.listViewLivros);

        listaLivros = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaLivros);
        listViewLivros.setAdapter(adapter);

        btnAdicionar.setOnClickListener(v -> {
            String titulo = editTextTitulo.getText().toString().trim();
            String autor = editTextAutor.getText().toString().trim();
            boolean visto = checkBoxVisto.isChecked();

            if (titulo.isEmpty() || autor.isEmpty()) {
                Toast.makeText(MainActivityAC1.this, "Preencha título e autor!", Toast.LENGTH_SHORT).show();
                return;
            }

            String status = visto ? "Lido" : "Não lido";
            String textoLivro = titulo + " - " + autor + " (" + status + ")";

            listaLivros.add(textoLivro);
            adapter.notifyDataSetChanged();

            editTextTitulo.setText("");
            editTextAutor.setText("");
            checkBoxVisto.setChecked(false);

            Toast.makeText(MainActivityAC1.this, "Livro adicionado!", Toast.LENGTH_SHORT).show();
        });


    }
}