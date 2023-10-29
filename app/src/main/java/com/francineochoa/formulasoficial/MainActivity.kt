package com.francineochoa.formulasoficial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.francineochoa.formulasoficial.databinding.Diseno1Binding

class MainActivity : AppCompatActivity() {

    //Promesa de inicializar la variable despues
    private lateinit var binding: Diseno1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //---------------------------
        //Metodo mas largo que se tendria que hacer para cada elemento del xlm
        //setContentView(R.layout.diseno1)
        //val tvTitulo= findViewById<TextView>(R.id.tvTitulo)
        //.tvTitulo.text ="Tests es el titulo"
        //---------------------------

        //Inicializando variable binding prometida
        //Con esto ya todas los elementos del xml estan en binding
        binding = Diseno1Binding.inflate(layoutInflater)
        setContentView(binding.root)
        //binding.tvTitulo.text ="¡Calcula tus Fórmulas!"

        //Una forma de hacer acciones cuando se le da click al boton
        //binding.boton1Empezar.setOnClickListener{
        //    binding.tvTitulo.text ="¡Vamos!"
        //}


    }

    //Funcion que corre cuando se le da click al boton
    fun clickEmpecemos(view: View){
        binding.tvTitulo.text ="¡Vamos!"

    }

}