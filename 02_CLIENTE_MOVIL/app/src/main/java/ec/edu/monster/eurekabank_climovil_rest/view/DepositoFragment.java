package ec.edu.monster.eurekabank_climovil_rest.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

import ec.edu.monster.eurekabank_climovil_rest.R;
import ec.edu.monster.eurekabank_climovil_rest.controller.MovimientoController;
import ec.edu.monster.eurekabank_climovil_rest.models.Movimiento;

public class DepositoFragment extends Fragment {
    private EditText txtAccount, txtAmount;
    private Button btnDepositar;
    private MovimientoController movimientoController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_deposito, container, false);

        txtAccount = view.findViewById(R.id.txtAccount);
        txtAmount = view.findViewById(R.id.txtAmount);
        btnDepositar = view.findViewById(R.id.btnDepositar);

        movimientoController = new MovimientoController();

        btnDepositar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = txtAccount.getText().toString();
                String amountStr = txtAmount.getText().toString();
                if (!account.isEmpty() && !amountStr.isEmpty()) {
                    double amount = Double.parseDouble(amountStr);
                    movimientoController.registrarDeposito(account, amount, new MovimientoController.UserCallback() {
                        @Override
                        public void onSuccess(List<Movimiento> movimientos) {
                            Toast.makeText(getContext(), "Depósito realizado con éxito", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(String errorMessage) {
                            Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(getContext(), "Ingrese cuenta y monto", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}
