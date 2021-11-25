package net.iessochoa.joelsemperedura.practica4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.Arrays;
import java.util.List;

public class TareaActivity extends AppCompatActivity {
    Spinner spnCategoria;
    Spinner spnPrioridad;
    Spinner spnEstado;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarea);

        iniciaViews();

        //adaptador para cada spinner y Obtener los array String de xml
        ArrayAdapter<CharSequence>adaptadorCategoria = ArrayAdapter.createFromResource(this,
                R.array.categoria, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence>adaptadorPrioridad = ArrayAdapter.createFromResource(this,
                R.array.prioridad, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence>adaptadorEstado = ArrayAdapter.createFromResource(this,
                R.array.estado, android.R.layout.simple_spinner_item);

        spnCategoria.setAdapter(adaptadorCategoria);
        spnPrioridad.setAdapter(adaptadorPrioridad);
        spnEstado.setAdapter(adaptadorEstado);



    }

    public void iniciaViews(){
        spnCategoria = findViewById(R.id.spnCategoria);
        spnPrioridad = findViewById(R.id.spnPrioridad);
        spnEstado = findViewById(R.id.spnEstado);
    }
}