package net.iessochoa.joelsemperedura.practica4.adapters;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import net.iessochoa.joelsemperedura.practica4.R;
import net.iessochoa.joelsemperedura.practica4.TareaActivity;
import net.iessochoa.joelsemperedura.practica4.model.Tarea;
import java.util.List;

public class TareasAdapter extends RecyclerView.Adapter<TareasAdapter.TareaViewHolder> {
    //Lista con las tareas a mostrar
    private List<Tarea> listaTareas;

    @NonNull
    @Override
    //Metodo que es llamado cuando se necesite un nuevo ViewHolder
    //Metodo que infla un NUEVO objeto
    public TareaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Se le pasa el layout de item tarea que representa cada elemento
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_tarea,parent,false);
        return new TareaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TareaViewHolder holder, int position) {
       //Si hay tareas recuperamos la que queremos mostrar
        if (listaTareas != null){
            final Tarea tarea = listaTareas.get(position);
            //mostrar los datos
            holder.tvResumen.setText(tarea.getId()+"-"+tarea.getDescripcion());

            //Segun la nota,mostramos un color u otro
            switch (tarea.getPrioridad()){
                case "Baja" :
                    holder.lyItemTarea.setBackgroundColor(Color.parseColor("#FAFAFA"));//BLANCO
                    break;
                case "Media" :
                    holder.lyItemTarea.setBackgroundColor(Color.parseColor("#FBF898"));//AMARILLO
                    break;
                case "Alta" :
                    holder.lyItemTarea.setBackgroundColor(Color.parseColor("#FC8181"));//ROJO
                    break;
            }
            // Icono segun Estado
            switch (tarea.getEstado()){
                case "Abierta" :
                   holder.ivEstado.setImageResource(R.drawable.ic_open);
                    break;
                case "En curso" :
                    holder.ivEstado.setImageResource(R.drawable.ic_run);
                    break;
                case "Terminada" :
                    holder.ivEstado.setImageResource(R.drawable.ic_close);
                    break;

            }

        }
    }

    @Override
    public int getItemCount() {
        //si la lista no esta vacia devuelveme su tamaño, si lo esta devuelveme cero
        if (listaTareas != null){
            return listaTareas.size();
        }else{
            return 0;
        }
    }

    public void setTareas(List<Tarea> tareaList){
        listaTareas = tareaList;
        //notificar cambios y reconstruir lista
        notifyDataSetChanged();
    }

    public class TareaViewHolder extends RecyclerView.ViewHolder {
        //Las propiedades para guardar las referencias de los views de item_tarea
        private TextView tvResumen;
        private TextView tvTecnico;
        private ImageView ivEstado;
        private ImageView ivEditar;
        private ImageView ivBorrar;
        //Al contenedor se le cambiara el color
        private ConstraintLayout lyItemTarea;




        public TareaViewHolder(@NonNull View itemView) {
            super(itemView);
            iniciaViews();

        }

        private void iniciaViews() {
            tvResumen = itemView.findViewById(R.id.tvResumen);
            tvTecnico = itemView.findViewById(R.id.tvTecnico);
            ivEstado = itemView.findViewById(R.id.ivEstado);
            ivEditar = itemView.findViewById(R.id.ivEditar);
            ivBorrar = itemView.findViewById(R.id.ivBorrar);
            lyItemTarea = itemView.findViewById(R.id.lyItemTarea);
        }
    }
}
