package ec.edu.monster.eurekabank_climovil_rest.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import ec.edu.monster.eurekabank_climovil_rest.R;
import ec.edu.monster.eurekabank_climovil_rest.controller.MovimientoController;
import ec.edu.monster.eurekabank_climovil_rest.models.Movimiento;

public class MovimientoFragment extends Fragment {
    private EditText txtButton;
    private Button btnConsultar;
    private TableLayout tableLayout;
    private MovimientoController movimientoController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movimientos, container, false);

        txtButton = view.findViewById(R.id.txtButton);
        btnConsultar = view.findViewById(R.id.btnConsultar);
        tableLayout = view.findViewById(R.id.tableLayout);

        movimientoController = new MovimientoController();

        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codigo = txtButton.getText().toString();
                if (!codigo.isEmpty()) {
                    movimientoController.fetchMovimientoByCode(codigo, new MovimientoController.UserCallback() {
                        @Override
                        public void onSuccess(List<Movimiento> movimientos) {
                            getActivity().runOnUiThread(() -> populateTable(movimientos));
                        }

                        @Override
                        public void onFailure(String errorMessage) {
                            getActivity().runOnUiThread(() -> {
                                Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
                            });
                        }
                    });
                } else {
                    Toast.makeText(getContext(), "Ingrese un código", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    private void populateTable(List<Movimiento> movimientos) {
        int childCount = tableLayout.getChildCount();
        if (childCount > 1) {
            tableLayout.removeViews(1, childCount - 1);
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());

        for (Movimiento movimiento : movimientos) {
            TableRow row = new TableRow(getContext());

            TextView txtCodigo = new TextView(getContext());
            txtCodigo.setText(movimiento.getChr_cuencodigo());
            row.addView(txtCodigo);

            TextView txtFecha = new TextView(getContext());
            txtFecha.setText(dateFormat.format(movimiento.getDtt_movifecha()));
            row.addView(txtFecha);

            TextView txtImporte = new TextView(getContext());
            txtImporte.setText(String.valueOf(movimiento.getDec_moviimporte()));
            row.addView(txtImporte);

            tableLayout.addView(row);
        }
    }
}
