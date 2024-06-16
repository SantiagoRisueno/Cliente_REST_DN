package ec.edu.monster.eurekabank_climovil_rest.models;

import java.util.Date;

public class Movimiento {
    private String chr_cuencodigo; // No nullable
    private Date dtt_movifecha;
    private double dec_moviimporte;

    public Movimiento(String chr_cuencodigo, Date dtt_movifecha, double dec_moviimporte) {
        this.chr_cuencodigo = chr_cuencodigo;
        this.dtt_movifecha = dtt_movifecha;
        this.dec_moviimporte = dec_moviimporte;
    }

    public Movimiento() {}

    public String getChr_cuencodigo() {
        return chr_cuencodigo;
    }

    public Date getDtt_movifecha() {
        return dtt_movifecha;
    }

    public double getDec_moviimporte() {
        return dec_moviimporte;
    }
}
