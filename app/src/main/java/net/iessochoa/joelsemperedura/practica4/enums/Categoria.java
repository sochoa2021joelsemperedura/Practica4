package net.iessochoa.joelsemperedura.practica4.enums;

import android.widget.ImageView;

import net.iessochoa.joelsemperedura.practica4.R;

public enum Categoria {
    REPARACION(R.drawable.ic_repara),
    INSTALACION(R.drawable.ic_instala),
    MANTENIMIENTO(R.drawable.ic_mantiene),
    COMERCIAL(R.drawable.ic_comercia),
    OTROS(R.drawable.ic_otros);
private int ivMostrar;

    public int getIvMostrar() {
        return ivMostrar;
    }

    Categoria(int ivMostrar) {
        this.ivMostrar = ivMostrar;
    }
}


