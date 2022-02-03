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
    Usuario usuario;

    //Variables para texto que tienen los EditText
    String correo;
    String nombre;
    String apellidos;
    String pass;
    String pass2;
    String nivel;

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

        usuario= new Usuario();

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String elcorreoabuscar=txtCorreo.getText().toString().trim();
                boolean estado = usuario.buscarCorreo(elcorreoabuscar);

                if (estado){
                    int indicecorreoEncontrado= usuario.getIndiceCorreo(elcorreoabuscar);
                    txtNombre.setText(usuario.getNombres(indicecorreoEncontrado));
                    txtApellido.setText(usuario.getApellidos(indicecorreoEncontrado));
                    txtClave.setText(usuario.getClaves(indicecorreoEncontrado));
                    txtClave2.setText(usuario.getClaves(indicecorreoEncontrado));

                    String elnivelencontrado=usuario.getNiveles(indicecorreoEncontrado);
                    if(elnivelencontrado.equals("1")){
                        rbUsuario.setChecked(true);
                    }else if(elnivelencontrado.equals("2")){
                        rbAsistente.setChecked(true);
                    }else{
                        rbAdministrador.setChecked(true);
                    }

                }else{
                    Toast.makeText(getApplicationContext(),"NO EXISTE", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public void guardar(View view){
        boolean estado = capturardatos();
        if(estado){
            usuario.insertarUsuarios(correo,nombre,apellidos,pass,nivel);
            int cantidadUsuarios=usuario.getCantidadElementos();
            Toast.makeText(getApplicationContext(),"Cantidad Usuarios: "+cantidadUsuarios, Toast.LENGTH_LONG).show();
            Toast.makeText(getApplicationContext(),"INFORMACION GUARDADA", Toast.LENGTH_LONG).show();
            limpiarPantalla();
        }
    }

    public boolean capturardatos(){
        //variable para indicar si se puede almacenar los datos
        boolean estado = false;

        //Asignar en variables el texto que tienen los EditText
        correo = txtCorreo.getText().toString().trim();
        nombre = txtNombre.getText().toString().trim();
        apellidos = txtApellido.getText().toString().trim();
        pass = txtClave.getText().toString().trim();
        pass2 = txtClave2.getText().toString().trim();
        nivel = "-1";

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
                nivel="1";
            }else if (rbAsistente.isChecked()){
                nivel="2";
            }else if(rbAdministrador.isChecked()){
                nivel="3";
            }else{
                Toast.makeText(getApplicationContext(),"Seleccione el nivel de Rol", Toast.LENGTH_LONG).show();
            }

            if(nivel != "-1"){
               // Toast.makeText(getApplicationContext(),"GUARDANDO INFORMACION", Toast.LENGTH_LONG).show();
                estado=true;
            }

        }
        return estado;
    }

    public void limpiarPantalla(){
        correo = "";
        nombre = "";
        apellidos = "";
        pass = "";
        pass2 = "";
        nivel = "";

        txtCorreo.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtClave.setText("");
        txtClave2.setText("");
        rbAdministrador.setChecked(false);
        rbAsistente.setChecked(false);
        rbUsuario.setChecked(false);
    }


}