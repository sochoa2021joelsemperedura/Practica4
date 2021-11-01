package net.iessochoa.joelsemperedura.practica4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //*************************MENU****************************//
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
                getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    //Opciones de los items del menu.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_ordenar:
                //El ejercicio pide un toast (TODO hacer la funcion más adelante)
                Toast.makeText(this, R.string.stOrdenar,Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_acercade:
                //TODO cuadro de dialogo
                Toast.makeText(this, R.string.stAcercaDe,Toast.LENGTH_SHORT).show();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}