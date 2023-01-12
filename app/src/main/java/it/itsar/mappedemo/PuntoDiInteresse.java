package it.itsar.mappedemo;

import com.google.android.gms.maps.model.LatLng;

public class PuntoDiInteresse {

    String nome;
    String descrizione;
    Double latitudine;
    Double longitudine;

    public PuntoDiInteresse(String nome, String descrizione, Double latitudine, Double longitudine) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.latitudine = latitudine;
        this.longitudine = longitudine;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Double getLatitudine() {
        return latitudine;
    }

    public void setLatitudine(Double latitudine) {
        this.latitudine = latitudine;
    }

    public Double getLongitudine() {
        return longitudine;
    }

    public void setLongitudine(Double longitudine) {
        this.longitudine = longitudine;
    }

    public LatLng getLatLng(){
        return new LatLng(latitudine, longitudine);
    }

    @Override
    public String toString() {
        return nome;
    }
}
