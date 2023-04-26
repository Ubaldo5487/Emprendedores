package com.example.proyectochignautla;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;


public class CreateFragment extends Fragment {//implements DialogInterface.OnCancelListener, DialogInterface.OnDismissListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle saveInstansceState) {

        EditText edName = view.findViewById(R.id.etNombre);
        EditText edFl = view.findViewById(R.id.etApellidoPaterno);
        EditText edMl = view.findViewById(R.id.etApellidoMaterno);
        EditText edPhone = view.findViewById(R.id.etTelefono);
        EditText edAge = view.findViewById(R.id.etEdad);
        EditText edColony = view.findViewById(R.id.etColonia);
        EditText edStreet1 = view.findViewById(R.id.etCalle1);
        EditText edStreet2 = view.findViewById(R.id.etCalle2);
        EditText edDescrip = view.findViewById(R.id.etDescripcion);
        Button guardar = view.findViewById(R.id.btnGuardar);

        Spinner edArea = view.findViewById(R.id.spinnerArea);
        Spinner edProblem = view.findViewById(R.id.spinnerProblem);

        String[] Areas = {"Agua", "Alumbrado Público", "Limpieza", "Mercados", "Panteones", "Calles", "Seguridad Pública"};
        edArea.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, Areas));

        String[] pAgua = {"Agua Potable", "Drenaje", "Alcantarillado"};
        String[] pAlumbrado = {"Alumbrado"};
        String[] pLimpieza = {"Limpia", "Recolección", "Traslado", "Tratamiento", "Disposición Final De Residuos"};
        String[] pMercados = {"Mercados"};
        String[] pPanteones = {"Panteones"};
        String[] pCalles = {"Calles", "Parques y Jardines", "Equipamiento"};
        String[] pSeguridad = {"Policia Preventiva", "Tránsito"};

        edArea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    edProblem.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, pAgua));
                }
                if (i == 1) {
                    edProblem.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, pAlumbrado));
                }
                if (i == 2) {
                    edProblem.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, pLimpieza));
                }
                if (i == 3) {
                    edProblem.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, pMercados));
                }
                if (i == 4) {
                    edProblem.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, pPanteones));
                }
                if (i == 5) {
                    edProblem.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, pCalles));
                }
                if (i == 6) {
                    edProblem.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, pSeguridad));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        RequestQueue queue = Volley.newRequestQueue(view.getContext());

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("¿Guardar Registro?");
                builder.setMessage("Verifique los campos antes de guardar")
                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                String eName = edName.getText().toString();
                                String eFl = edFl.getText().toString();
                                String eMl = edMl.getText().toString();
                                String ePhone = edPhone.getText().toString();
                                String eAge = edAge.getText().toString();
                                String eColony = edColony.getText().toString();
                                String eStreet1 = edStreet1.getText().toString();
                                String eStreet2 = edStreet2.getText().toString();
                                String eArea = edArea.getSelectedItem().toString();
                                String eProblem = edProblem.getSelectedItem().toString();
                                String eDescrip = edDescrip.getText().toString();

                                /*
                                IdIncidencia
                                Incidencia
                                TipoIncidencia
                                FechaIncidencia
                                Frecuencia
                                NivelUrgencia
                                tiporiesgo
                                tipomentenimineto
                                lugarinciencia
                                areaincidencia
                                nombrecolaborador
                                puetoscolaborador
                                areaefectada
                                numpersonasafectadas





                                 */

                                String url = "https://hostchignautla.000webhostapp.com/insertReport.php?name=" + eName + "&fatherlastname=" + eFl + "&motherlastname=" + eMl + "&phonenumber=" + ePhone + "&age=" + eAge + "&colony=" + eColony + "&street1=" + eStreet1 + "&street2=" + eStreet2 + "&area=" + eArea + "&problem=" + eProblem + "&description=" + eDescrip;

                                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                                        Request.Method.GET, url, null,
                                        new Response.Listener<JSONObject>() {

                                            @Override
                                            public void onResponse(JSONObject response) {
                                                Navigation.findNavController(view).navigate(R.id.action_createFragment_to_homeFragment);
                                                Toast.makeText(view.getContext(), "Reporte guardado con Éxito", Toast.LENGTH_LONG).show();
                                            }
                                        }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Toast.makeText(view.getContext(), "Datos Inválidos o Incorrectos ", Toast.LENGTH_LONG).show();
                                    }
                                });

                                queue.add(jsonObjectRequest);
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                            }
                        });

                AlertDialog dialog = builder.create();
                dialog.show();
            }

        });

    }
}