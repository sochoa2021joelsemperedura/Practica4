package net.iessochoa.joelsemperedura.practica4;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import net.iessochoa.joelsemperedura.practica4.adapters.TareasAdapter;
import net.iessochoa.joelsemperedura.practica4.model.Tarea;
import net.iessochoa.joelsemperedura.practica4.model.TareaViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    //TODO - implementar en añadir nueva nota startforresult
    //TODO - por el punto 17
    public static final int OPTION_REQUEST_NUEVA = 0;
    public static final int OPTION_REQUEST_MODIFICAR = 1;
    private RecyclerView rvLista;
    // nos permite mantener los datos cuando se reconstruye la actividad
    private TareaViewModel tareaViewModel;
    //Adaptador del recyclerView
    private TareasAdapter tareasAdapter;
    private int cuentaTareas = 1;
    //Boton Añadir
    FloatingActionButton fabAnyadir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //***Inicia las views***//
        iniciaViews();
        //***establece el layout del reclyclerView y le añade el adapter***//
        rvLista.setLayoutManager(new LinearLayoutManager(this));
        rvLista.setAdapter(tareasAdapter);
        //***Recuperacion o creacion del viewModel***//
        tareaViewModel = new ViewModelProvider(this).get(TareaViewModel.class);
        //Que se muestren automaticamente
        tareaViewModel.getTareaList().observe(this, new Observer<List<Tarea>>() {
            @Override
            public void onChanged(List<Tarea> tareas) {
                //actualizamos el recyclerView si hay cambios en la lista de tareas
                tareasAdapter.setTareas(tareas);
            }
        });

        //Nueva Tarea -
        //Al añadirla activara el observer anterior
        fabAnyadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,TareaActivity.class);
                startActivityForResult(intent,OPTION_REQUEST_NUEVA);

            }
        });
        //Borrar Tarea -
        //Creamos el listener que se activara cuando pulse el icono de borrar
        tareasAdapter.setOnClickBorrarListener(new TareasAdapter.OnItemClickBorrarListener() {
            @Override
            public void onItemBorrarClick(Tarea tarea) {
                borrarTarea(tarea);
            }
        });

        tareasAdapter.setOnClickElementoListener(new TareasAdapter.OnItemClickElementoListener() {
            @Override
            public void onItemClickElemento(Tarea tarea) {
                //todo ver tarea
                verTarea(tarea);
            }
        });

    }

    /**
     * Permite borrar la tarea, previamente muestra un dialogo para asegurar al usuario
     * que desea borrarla
     * @param tarea
     */
    private void borrarTarea(Tarea tarea) {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity.this);
        dialogo.setTitle("Aviso"); //titulo
        dialogo.setMessage("Esta seguro de que desea eliminar la tarea con id "+tarea.getId()); //mensaje

        //boton ok y evento
        dialogo.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // en caso de ok
                tareaViewModel.delTarea(tarea);
            }
        });
        dialogo.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            //en caso de cancel nada
            }
        });
        dialogo.show();
    }
    /**
     * Mostramos un dialogo con la tarea
     * @param tarea
     */
    private void verTarea(Tarea tarea) {
        /* ****** MOSTRAR LOS DATOS EN UN DIALOGO ******
        AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity.this);
        dialogo.setTitle("Tarea "+tarea.getId());// titulo y mensaje

        dialogo.setMessage(tarea.getDescripcion());
// agregamos botón Ok y su evento
        dialogo.setPositiveButton(android.R.string.ok
                , new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Qué hacemos en caso ok
                    }
                });
        dialogo.show();
         */
        //tareaViewModel.getTareaList()
    }


    private void iniciaViews() {
        rvLista = findViewById(R.id.rvTareas);
        tareasAdapter = new TareasAdapter();
        //probando
        fabAnyadir = findViewById(R.id.fabAnyadir);

       /* fabAnyadir.setOnClickListener(view->{
            Intent intent = new Intent(MainActivity.this,TareaActivity.class);
            startActivity(intent);
        });

         */
    }

    //*************************MENU****************************//
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
                getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    //*************************Opciones de los items del menu****************************//
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_ordenar:
                //El ejercicio pide un toast (TODO hacer la funcion más adelante -OPTATIVA-)
                Toast.makeText(this, R.string.stOrdenar,Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_acercade:
                //Inicializa el objeto que nos devuelve el dialogo
                FragmentManager fg = getSupportFragmentManager();
                DialogoAlerta dialogo = new DialogoAlerta();
                dialogo.show(fg,"tagAcercaDe");

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}