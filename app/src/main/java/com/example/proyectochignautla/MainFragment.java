package com.example.proyectochignautla;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainFragment extends Fragment {

    //EditText TXusuario, TXpassword;
    EditText edtUsuario,edtPassword;
    String u, p;
    ProgressDialog progressDialog;
    RequestQueue requestQueue;
    String HttpURI = "https://hostchignautla.000webhostapp.com/getUser.php";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        EditText edtP = view.findViewById(R.id.edtPassword);
        Button BotonIS = view.findViewById(R.id.btnMainHome);
        CheckBox checkBox = view.findViewById(R.id.showPassword);
        Button btnMainRegister = view.findViewById(R.id.btnMainRegister);

        requestQueue = Volley.newRequestQueue(getContext());
        progressDialog = new ProgressDialog(getContext());




        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (!checkBox.isChecked()) {
                    edtP.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    edtP.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });

        btnMainRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_registerFragment);
            }
        });

        BotonIS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarUsuario("https://hostchignautla.000webhostapp.com/login.php");
            }
        });
    }

    private void validarUsuario(String URL) {
        progressDialog.setMessage("Verificando...");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                if (!response.isEmpty()) {
                    Navigation.findNavController(getView()).navigate(R.id.action_mainFragment_to_homeFragment);
                } else {
                    Toast.makeText(getContext(), "Usuario o Contraseña incorrecta", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                EditText edtUsuario = (EditText) getView().findViewById(R.id.edtUsuario);
                EditText edtPassword = (EditText) getView().findViewById(R.id.edtPassword);
                String Usuario = edtUsuario.getText().toString();
                String Contrasena = edtPassword.getText().toString();
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("user", Usuario);
                parametros.put("pass", Contrasena);
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);


    }

}