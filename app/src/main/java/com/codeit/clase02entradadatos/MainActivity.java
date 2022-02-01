package com.codeit.clase02entradadatos;

import android.os.PatternMatcher;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText txtCorreo, txtNombre, txtApellido, txtClave, txtClave2;
    RadioButton rbUsuario, rbAsistente, rbAdministrador;
    Button btnGuardar, btnBuscar, btnEliminar, btnSalir;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        txtCorreo = findViewById(R.id.txtCorreo);
        txtNombre = findViewById(R.id.txtNombre);
        txtApellido = findViewById(R.id.txtApellido);
        txtClave = findViewById(R.id.txtClave);
        txtClave2 = findViewById(R.id.txtClave2);
        rbUsuario = findViewById(R.id.rbUsuario);
        rbAsistente = findViewById(R.id.rbAsistente);
        rbAdministrador = findViewById(R.id.rbAdministrador);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnBuscar = findViewById(R.id.btnBuscar);
        btnEliminar = findViewById(R.id.btnEliminar);
        btnSalir = findViewById(R.id.btnSalir);
    }

    public void guardar(View view){
        capturardatos();
    }

    public void capturardatos(){
        //Asignar en variables el texto que tienen los EditText
        String correo = txtCorreo.getText().toString().trim();
        String nombre = txtNombre.getText().toString().trim();
        String apellidos = txtApellido.getText().toString().trim();
        String pass = txtClave.getText().toString().trim();
        String pass2 = txtClave2.getText().toString().trim();
        int nivel = 0;

        //Evaluar que NO esten vacios los campos
        if(TextUtils.isEmpty(correo)){
            txtCorreo.setError("Valor requerido");
            txtCorreo.requestFocus();
        }else if(!Patterns.EMAIL_ADDRESS.matcher(correo).matches()){
            txtCorreo.setError("Formato incorrecto");
            txtCorreo.requestFocus();
        }else if(TextUtils.isEmpty(nombre)){
            txtNombre.setError("Valor requerido");
            txtNombre.requestFocus();
        }else if(TextUtils.isEmpty(apellidos)){
            txtApellido.setError("Valor requerido");
            txtApellido.requestFocus();
        }else if(TextUtils.isEmpty(pass)){
            txtClave.setError("Valor requerido");
            txtClave.requestFocus();
        }else if(TextUtils.isEmpty(pass2)){
            txtClave2.setError("Valor requerido");
            txtClave2.requestFocus();
        }else{

            //evaluar el radio button
            if(rbUsuario.isChecked()){
                nivel=1;
            }else if (rbAsistente.isChecked()){
                nivel=2;
            }else if(rbAdministrador.isChecked()){
                nivel=3;
            }else{
                Toast.makeText(getApplicationContext(),"Seleccione el nivel de Rol", Toast.LENGTH_LONG).show();
            }

            if(nivel != 0){
                Toast.makeText(getApplicationContext(),"GUARDANDO INFORMACION", Toast.LENGTH_LONG).show();
            }

        }

    }
}