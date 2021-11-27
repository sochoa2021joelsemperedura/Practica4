package net.iessochoa.joelsemperedura.practica4.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

/*
View model nos permite mantener los datos aunque android detecte un cambio de configuracion y los destruya.
en el oncreate recuperamos el viewmodel

los datos que creamos aqui se mantienen en memoria.
los datos de tipo livedata nos permiten mantener observadores en la activity para detectar cuando hay cambios en los datos.
En esta clase se mantendra el CRUD sobra la lista tareas y al actualizar el livedata se
llamara al observer creado en la activity para mostrar los datos en pantalla
 */
public class TareaViewModel extends AndroidViewModel {
    //si queremos que la actividad reciba un aviso cuando se modifican los datos
    private MutableLiveData<List<Tarea>> listaTareasLiveData;
    //lista que se mantendra durante toda la vida de la activity
    private List<Tarea> listaTareas;

    public TareaViewModel(@NonNull Application application) {
        super(application);
        listaTareasLiveData = new MutableLiveData<List<Tarea>>();
        //Datos de ejemplo
        crearDatos();
        //Avisamos de la modificacion con el liveData
        listaTareasLiveData.setValue(listaTareas);
    }

    /*
    Recuperar el LiveData para asignar el listener al Observador en la activity
     */
    public LiveData<List<Tarea>> getTareaList(){
        return listaTareasLiveData;
    }

    //avisamos al livedata para que active el observer y la actividad muestre los cambios
    private void avisarAlObserver() {
        listaTareasLiveData.setValue(listaTareas);
    }

    //*************AÑADIR NOTA NUEVA A LA LISTA********************//
    public void addTarea(Tarea nuevaTarea){
        //Si existe con mismo id la sustituimos, si no, añadimos.
        int i=listaTareas.indexOf(nuevaTarea);
        if (i < 0) {
            listaTareas.add(nuevaTarea);
        }else{
            listaTareas.remove(i);
            listaTareas.add(i,nuevaTarea);
        }
        avisarAlObserver();
    }

    //*******************ELIMINAMOS TAREA POR ID********************//
    public void delTarea(Tarea tarea){
        if (listaTareas.size()>0){
            listaTareas.remove(tarea);

            avisarAlObserver();
        }
    }

    //*****************DATOS DE PRUEBA************************//
    private void crearDatos(){
        listaTareas = new ArrayList<Tarea>();
        //1
        Tarea tarea = new Tarea("Alta","Mantenimiento","Abierta","Joel Sempere","Actualización de antivirus","\"Lorem ipsum dolor sit amet,\n" +
                "consectetur adipiscing elit. Mauris laoreet aliquam sapien, quis mattis\n" +
                "diam pretium vel. Integer nec tincidunt turpis. Vestibulum interdum\n" +
                "accumsan massa, sed blandit ex fringilla at. Vivamus non sem vitae nisl\n" +
                "viverra pharetra. Pellentesque pulvinar vestibulum risus sit amet tempor.\n" +
                "Sed blandit arcu sed risus interdum fermentum. Integer ornare lorem urna,\n" +
                "eget consequat ante lacinia et. Phasellus ut diam et diam euismod convallis");
        listaTareas.add(tarea);
        //2
        tarea=new Tarea("Baja","Reparación","En curso","Viviana Castillo","Sustitución de ratones","Lorem ipsum dolor sit amet, consectetur\n" +
                "adipiscing elit. Mauris laoreet aliquam sapien, quis mattis diam pretium\n" +
                "vel. Integer nec tincidunt turpis. Vestibulum interdum accumsan massa, sed\n" +
                "blandit ex fringilla at. Vivamus non sem vitae nisl viverra pharetra.\n" +
                "Pellentesque pulvinar vestibulum risus sit amet tempor. Sed blandit arcu\n" +
                "sed risus interdum fermentum. Integer ornare lorem urna, eget consequat\n" +
                "ante lacinia et. Phasellus ut diam et diam euismod convallis");
        listaTareas.add(tarea);
        //3
        tarea=new Tarea("Media","Mantenimiento","Terminada","Gabriella Sempere","Actualización de S.O. Linux","Lorem ipsum dolor sit amet, consectetur\n" +
                "adipiscing elit. Mauris laoreet aliquam sapien, quis mattis diam pretium\n" +
                "vel. Integer nec tincidunt turpis. Vestibulum interdum accumsan massa, sed\n" +
                "blandit ex fringilla at. Vivamus non sem vitae nisl viverra pharetra.\n" +
                "Pellentesque pulvinar vestibulum risus sit amet tempor. Sed blandit arcu\n" +
                "sed risus interdum fermentum. Integer ornare lorem urna, eget consequat\n" +
                "ante lacinia et. Phasellus ut diam et diam euismod convallis");
        listaTareas.add(tarea);
        //4
        tarea=new Tarea("Media","Comercial","Abierta","Lidia Sanchez","Presentar Presupuesto Web","Lorem ipsum dolor sit amet, consectetur\n" +
                "adipiscing elit. Mauris laoreet aliquam sapien, quis mattis diam pretium\n" +
                "vel. Integer nec tincidunt turpis. Vestibulum interdum accumsan massa, sed\n" +
                "blandit ex fringilla at. Vivamus non sem vitae nisl viverra pharetra.\n" +
                "Pellentesque pulvinar vestibulum risus sit amet tempor. Sed blandit arcu\n" +
                "sed risus interdum fermentum. Integer ornare lorem urna, eget consequat\n" +
                "ante lacinia et. Phasellus ut diam et diam euismod convallis");
        listaTareas.add(tarea);
        //5
        tarea=new Tarea("Alta","Comercial","En curso","Pepe Botella","Compra de Criptomonedas","Lorem ipsum dolor sit amet, consectetur\n" +
                "adipiscing elit. Mauris laoreet aliquam sapien, quis mattis diam pretium\n" +
                "vel. Integer nec tincidunt turpis. Vestibulum interdum accumsan massa, sed\n" +
                "blandit ex fringilla at. Vivamus non sem vitae nisl viverra pharetra.\n" +
                "Pellentesque pulvinar vestibulum risus sit amet tempor. Sed blandit arcu\n" +
                "sed risus interdum fermentum. Integer ornare lorem urna, eget consequat\n" +
                "ante lacinia et. Phasellus ut diam et diam euismod convallis");
        listaTareas.add(tarea);
    }
}
