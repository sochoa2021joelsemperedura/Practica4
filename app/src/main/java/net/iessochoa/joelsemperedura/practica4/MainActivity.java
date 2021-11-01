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
    //Opciones de los items del menu. TODO las acciones reales, actualmente solo muestran un "print"
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                Toast.makeText(this, R.string.stAnyadir,Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_del:
                Toast.makeText(this, R.string.stBorrar,Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_acercade:
                Toast.makeText(this, R.string.stAcercaDe,Toast.LENGTH_SHORT).show();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}