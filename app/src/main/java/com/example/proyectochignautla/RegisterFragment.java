package com.example.proyectochignautla;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigationrail.NavigationRailMenuView;

import org.json.JSONObject;

public class RegisterFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle saveInstansceState) {

        EditText nombreregistro = view.findViewById(R.id.etNombreRegister);
        EditText apellidopaternoregistro = view.findViewById(R.id.etApellidoPaternoRegister);
        EditText apellidomaternoregistro = view.findViewById(R.id.etApellidoMaternoRegister);
        EditText telefonoregistro = view.findViewById(R.id.etTelefonoRegister);
        EditText correoregistro = view.findViewById(R.id.etCorreoRegister);
        EditText usuarioregistro = view.findViewById(R.id.etUsuarioRegister);
        EditText contrasenaregistro = view.findViewById(R.id.etContrasenaRegister);
        CheckBox showPasswordregistro = view.findViewById(R.id.showPasswordRegister);
        Button guardarregistro = view.findViewById(R.id.btnGuardarRegister);

        showPasswordregistro.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (!showPasswordregistro.isChecked()){
                    contrasenaregistro.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    contrasenaregistro.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });


        RequestQueue queue = Volley.newRequestQueue(view.getContext());

        guardarregistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("¿Guardar Usuario?");
                builder.setMessage("Verifique los campos antes de guardar")
                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String enombreregistro = nombreregistro.getText().toString();
                                String eapellidopaternoregistro = apellidopaternoregistro.getText().toString();
                                String eapellidomaternoregistro = apellidomaternoregistro.getText().toString();
                                String etelefonoregistro = telefonoregistro.getText().toString();
                                String ecorreoregistro = correoregistro.getText().toString();
                                String eusuarioregistro = usuarioregistro.getText().toString();
                                String econtrasenaregistro = contrasenaregistro.getText().toString();

                                String url = "https://hostchignautla.000webhostapp.com/insertUser.php?name=" + enombreregistro + "&fatherlastname=" + eapellidopaternoregistro +
                                        "&motherlastname=" + eapellidomaternoregistro + "&phonenumber=" + etelefonoregistro + "&email=" + ecorreoregistro +
                                        "&user=" + eusuarioregistro + "&pass=" + econtrasenaregistro;

                                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                                        Request.Method.GET, url, null,
                                        new Response.Listener<JSONObject>() {

                                            @Override
                                            public void onResponse(JSONObject response) {
                                                Navigation.findNavController(view).navigate(R.id.action_registerFragment_to_mainFragment);
                                                Toast.makeText(view.getContext(), "Registro guardado con Éxito", Toast.LENGTH_LONG).show();
                                            }
                                        }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Toast.makeText(view.getContext(), "Datos Inválidos o Incorrectos", Toast.LENGTH_LONG).show();
                                    }
                                });
                                queue.add(jsonObjectRequest);
                            }

                        }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });
    }
}