package com.example.appcamara

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private val CAMERA_REQUEST_CODE = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btCamara = findViewById<Button>(R.id.btCamara)

        btCamara.setOnClickListener {
            checkCameraPermission()
        }
    }

    private fun checkCameraPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
        != PackageManager.PERMISSION_GRANTED){
            //permiso rechazado
            requestCameraPermission()
            //Toast.makeText(this, "No cuenta con permiso de la camara", Toast.LENGTH_LONG).show()
        }
        else{
            Toast.makeText(this, "Ya se cuenta con permiso de la camara", Toast.LENGTH_LONG).show()
        }
    }

    private fun requestCameraPermission() {
        //solicito el permiso
        //1er caso, q yo haya rechazado antes
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)){
            Toast.makeText(this, "El usuario rechazó el permiso. Habilítelo MANUALMENTE!", Toast.LENGTH_LONG).show()
        }
        else{
            //2do caso, que nunca haya pedido permiso
            Toast.makeText(this, "Debe aceptar permiso de la camara", Toast.LENGTH_LONG).show()
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), CAMERA_REQUEST_CODE)
        }
    }

    //Vamos a sobreescribir la funcion onRequestPermissionResult
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode){
            CAMERA_REQUEST_CODE -> {
                if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "Se autorizó acceso a la camara", Toast.LENGTH_LONG).show()
                    //Aqui se añade toda la funcionalidad
                }
                else{
                    Toast.makeText(this, "Permiso a la camara NEGADO", Toast.LENGTH_LONG).show()
                }
                return
            }
        }
    }
}