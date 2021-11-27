package net.iessochoa.joelsemperedura.practica4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import net.iessochoa.joelsemperedura.practica4.model.Tarea;

import java.util.Arrays;
import java.util.List;

public class TareaActivity extends AppCompatActivity {

    //Tarea usada para comparar si existe
    public static String EXTRA_TAREA = "TareaActivity.tarea"; // el que llega
    Tarea tarea;
    Intent iBack;

    Spinner spnCategoria;
    Spinner spnPrioridad;
    Spinner spnEstado;

    ImageView ivEstadoTarea;
    EditText etDescripcionGrande;
    TextInputEditText tilDescripcion;
    TextInputEditText tilTecnico;

    FloatingActionButton fabGuardar;




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

        //verifica que es una nueva tarea
        iBack = getIntent();
        if (iBack.getParcelableExtra(EXTRA_TAREA) == null){
            //titulo nuevo
            this.setTitle(getString(R.string.stNuevaTarea));
            this.tarea = null;
        }
        else {
            //tarea es igual a la tarea del item
            this.tarea = iBack.getParcelableExtra(EXTRA_TAREA);
            this.setTitle(getString(R.string.stTarea)+" "+tarea.getId());

            spnPrioridad.setSelection(adaptadorPrioridad.getPosition(tarea.getPrioridad()));
            spnCategoria.setSelection(adaptadorCategoria.getPosition(tarea.getCategoria()));
            spnEstado.setSelection(adaptadorEstado.getPosition(tarea.getEstado()));

            tilTecnico.setText(tarea.getTecnico());
            tilDescripcion.setText(tarea.getDescripcion());
            etDescripcionGrande.setText(tarea.getResumen());

        }

        //Si clicas el boton guardar
        fabGuardar.setOnClickListener( e->{
            if(tilTecnico.getText().toString().equals("") || tilDescripcion.getText().equals("") ||
            etDescripcionGrande.getText().equals("")){
                Toast.makeText(this,getString(R.string.stAvisoGuardar),Toast.LENGTH_SHORT).show();
            }else{

                if (tarea == null){
                    //guardar nueva
                    iBack.putExtra(EXTRA_TAREA,new Tarea(spnPrioridad.getSelectedItem().toString(),
                            spnCategoria.getSelectedItem().toString(),
                            spnEstado.getSelectedItem().toString(),
                            tilTecnico.getText().toString(),etDescripcionGrande.getText().toString(),tilDescripcion.getText().toString()));
                } else {
                    tarea.setCategoria(spnCategoria.getSelectedItem().toString());
                    tarea.setPrioridad(spnPrioridad.getSelectedItem().toString());
                    tarea.setEstado(spnEstado.getSelectedItem().toString());
                    tarea.setTecnico(tilTecnico.getText().toString());
                    tarea.setResumen(etDescripcionGrande.getText().toString());
                    tarea.setDescripcion(tilDescripcion.getText().toString());
                    iBack.putExtra(EXTRA_TAREA,tarea);
                }
                setResult(RESULT_OK,iBack);
                finish();

            }

        });

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

    /*
    Que hacer segun el resultado devuelto
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //TODO
    }

    public void iniciaViews(){
        spnCategoria = findViewById(R.id.spnCategoria);
        spnPrioridad = findViewById(R.id.spnPrioridad);
        spnEstado = findViewById(R.id.spnEstado);

        ivEstadoTarea = findViewById(R.id.ivEstadoTarea);

        tilDescripcion = findViewById(R.id.tilDescripcion);
        tilTecnico = findViewById(R.id.tilTecnico);
        etDescripcionGrande = findViewById(R.id.etDescripcionGrande);

        fabGuardar = findViewById(R.id.fabGuardar);
    }
}