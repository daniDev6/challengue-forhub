package com.forohub.principal.service;

import org.springframework.stereotype.Service;

@Service
public class ServicioGenerales {


    public boolean controlarEstado(String nuevoString, String viejoString){
        if(nuevoString.isEmpty()){
            return false;
        }else{
            return !nuevoString.equals(viejoString);
        }
    }
    public boolean controlarEstado(int nuevoNumero, int viejoNumero){
        return nuevoNumero!=viejoNumero;
    }
    public boolean controlarEstado(boolean nuevoNumero, boolean viejoNumero){
        return nuevoNumero!=viejoNumero;
    }

}
