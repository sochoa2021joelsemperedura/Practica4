package net.iessochoa.joelsemperedura.practica4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class TareaActivity extends AppCompatActivity {
    Spinner spnCategoria;
    Spinner spnPrioridad;
    Spinner spnEstado;

    ImageView ivEstadoTarea;




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

        //Evento seleccion de Spinner cargar icono
        spnEstado.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent,
                                       android.view.View v, int position, long id) {

                //Capturo el Item
                String item=(String) parent.getItemAtPosition(position);
                //Si item coincide con alguno de estos casos, establece un nuevo imageView
                switch (item){
                    case "Abierta" :
                        ivEstadoTarea.setImageResource(R.drawable.ic_open);
                        break;
                    case "En curso" :
                        ivEstadoTarea.setImageResource(R.drawable.ic_run);
                        break;
                    case "Terminada" :
                        ivEstadoTarea.setImageResource(R.drawable.ic_close);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



    }

    public void iniciaViews(){
        spnCategoria = findViewById(R.id.spnCategoria);
        spnPrioridad = findViewById(R.id.spnPrioridad);
        spnEstado = findViewById(R.id.spnEstado);

        ivEstadoTarea = findViewById(R.id.ivEstadoTarea);
    }
}