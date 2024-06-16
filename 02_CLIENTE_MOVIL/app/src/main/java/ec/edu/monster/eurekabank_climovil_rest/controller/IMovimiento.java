package ec.edu.monster.eurekabank_climovil_rest.controller;

import ec.edu.monster.eurekabank_climovil_rest.models.Movimiento;
import ec.edu.monster.eurekabank_climovil_rest.models.MovimientoDto;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import java.util.List;

public interface IMovimiento {
    @GET("movimientos/{codigo}")
    Call<List<Movimiento>> getMovimientoByCode(@Path("codigo") String codigo);

    @POST("movimientos/importe")
    Call<Movimiento> regDeposito(@Body MovimientoDto movimientoDto);
}
