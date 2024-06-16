package ec.edu.monster.eurekabank_climovil_rest.controller;

import android.util.Log;

import ec.edu.monster.eurekabank_climovil_rest.RetrofitClient;
import ec.edu.monster.eurekabank_climovil_rest.models.Movimiento;
import ec.edu.monster.eurekabank_climovil_rest.models.MovimientoDto;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.Collections;
import java.util.List;

public class MovimientoController {
    private static final String BASE_URL = "http://192.168.100.107:28655/api/";

    public interface UserCallback {
        void onSuccess(List<Movimiento> movimientos);
        void onFailure(String errorMessage);
    }

    public void fetchMovimientoByCode(String codigoInt, final UserCallback callback) {
        IMovimiento movimientoService = RetrofitClient.getMovimiento(BASE_URL).create(IMovimiento.class);
        Call<List<Movimiento>> call = movimientoService.getMovimientoByCode(codigoInt);

        call.enqueue(new Callback<List<Movimiento>>() {
            @Override
            public void onResponse(Call<List<Movimiento>> call, Response<List<Movimiento>> response) {
                Log.d("MovimientoController", "onResponse: " + response.toString());
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure("Request failed with code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Movimiento>> call, Throwable t) {
                Log.e("MovimientoController", "onFailure: " + t.getMessage());
                callback.onFailure("Request failed: " + t.getMessage());
            }
        });
    }


    public void registrarDeposito(String account, double amount, final UserCallback callback) {
        MovimientoDto movimientoDto = new MovimientoDto(account, amount);
        Call<Movimiento> call = RetrofitClient.getMovimiento(BASE_URL).create(IMovimiento.class).regDeposito(movimientoDto);

        call.enqueue(new Callback<Movimiento>() {
            @Override
            public void onResponse(Call<Movimiento> call, Response<Movimiento> response) {
                Log.d("MovimientoController", "onResponse: " + response.toString());
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(Collections.singletonList(response.body()));
                } else {
                    callback.onFailure("Request failed with code: " + response.code());
                    Log.e("MovimientoController", "Request failed with code: " + response.code() + ", message: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Movimiento> call, Throwable t) {
                Log.e("MovimientoController", "onFailure: " + t.getMessage());
                callback.onFailure("Request failed: " + t.getMessage());
            }
        });
    }
}
