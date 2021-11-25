package net.iessochoa.joelsemperedura.practica4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import net.iessochoa.joelsemperedura.practica4.adapters.TareasAdapter;
import net.iessochoa.joelsemperedura.practica4.model.Tarea;
import net.iessochoa.joelsemperedura.practica4.model.TareaViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
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
        tareaViewModel.getNotaList().observe(this, new Observer<List<Tarea>>() {
            @Override
            public void onChanged(List<Tarea> tareas) {
                //actualizamos el recyclerView si hay cambios en la lista de tareas
                tareasAdapter.setTareas(tareas);
            }
        });
    }

    private void iniciaViews() {
        rvLista = findViewById(R.id.rvTareas);
        tareasAdapter = new TareasAdapter();
        //Todo borrar esto luego
        fabAnyadir = findViewById(R.id.fabAnyadir);
        fabAnyadir.setOnClickListener(view->{
            Intent intent = new Intent(MainActivity.this,TareaActivity.class);
            startActivity(intent);
        });
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
                //El ejercicio pide un toast (TODO hacer la funcion más adelante)
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