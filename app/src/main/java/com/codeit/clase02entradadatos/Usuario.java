package com.codeit.clase02entradadatos;

import android.icu.text.UFormat;

import java.util.ArrayList;

public class Usuario {
    private ArrayList correos;
    private ArrayList nombres;
    private ArrayList apellidos;
    private ArrayList claves;
    private ArrayList niveles;

    public Usuario(){
        correos = new ArrayList();
        nombres = new ArrayList();
        apellidos = new ArrayList();
        claves = new ArrayList();
        niveles = new ArrayList();

        int indiceNivel = 1;
        for (int indice =1; indice<=100; indice++){
            correos.add("erick" + indice + "@mail.utec.edu.sv");
            nombres.add("Erick " + indice);
            apellidos.add("Cruz " + indice);
            claves.add("123" + indice);
            niveles.add(indiceNivel);
            indiceNivel++;
            if(indiceNivel==4){
                indiceNivel=1;
            }

        }
    }

    public void insertarUsuarios(String correos, String nombres, String apellidos, String claves, String niveles){
        this.correos.add(correos);
        this.nombres.add(nombres);
        this.apellidos.add(apellidos);
        this.claves.add(claves);
        this.niveles.add(niveles);
    }

    public int getCantidadElementos(){
        return correos.size();
    }

    public boolean buscarCorreo(String correoabuscar){
        return correos.contains(correoabuscar);
    }

    public int getIndiceCorreo(String correoabuscar){
        return correos.indexOf(correoabuscar);
    }

    public String getCorreo(int indice){
        return correos.get(indice).toString().trim();
    }

    public String getNombres(int indice){
        return nombres.get(indice).toString().trim();
    }

    public String getApellidos(int indice){
        return apellidos.get(indice).toString().trim();
    }

    public String getClaves(int indice){
        return claves.get(indice).toString().trim();
    }

    public String getNiveles(int indice){
        return niveles.get(indice).toString().trim();
    }


}
