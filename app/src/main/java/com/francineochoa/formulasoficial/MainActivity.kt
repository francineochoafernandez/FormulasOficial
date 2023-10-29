package com.francineochoa.formulasoficial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.francineochoa.formulasoficial.databinding.ActivityMainBinding
import com.francineochoa.formulasoficial.databinding.Diseno1Binding

class MainActivity : AppCompatActivity() {

    //Promesa de inicializar la variable despues
    private lateinit var binding: Diseno1Binding //ActivityMainBinding

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

    fun calculaFormula(numero1:Int, numero2:Int):Int{
        val resultado=numero1+numero2
        return resultado
    }

    //Funcion que corre cuando se le da click al boton
    fun clickCalcular(view: View){

        if( binding.editTextNumber1.text.isNotEmpty() &&  binding.editTextNumber2.text.isNotEmpty())
        {
            val numero1 = binding.editTextNumber1.text.toString().toInt()
            val numero2 = binding.editTextNumber2.text.toString().toInt()

            val resultado=calculaFormula(numero1,numero2)

            binding.textViewResultado.text=getString(R.string.res, resultado)
        }
        else
        {
            if(binding.editTextNumber1.text.isEmpty())
                binding.editTextNumber1.error= getString(R.string.correccion)

            if(binding.editTextNumber2.text.isEmpty())
                binding.editTextNumber2.error= getString(R.string.correccion)

            Toast.makeText(this,getString(R.string.error1),Toast.LENGTH_LONG).show()
        }


    }

}