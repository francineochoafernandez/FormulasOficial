package com.francineochoa.formulasoficial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import com.francineochoa.formulasoficial.databinding.ActivityMainBinding
import com.francineochoa.formulasoficial.databinding.Diseno1Binding
import android.widget.ArrayAdapter
import android.widget.AdapterView

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

        //--------Creacion y funcionamiento del spiner------------
        val spinner:Spinner = binding.mySpinner
        // Create an ArrayAdapter using the string array and a default spinner layout.
        ArrayAdapter.createFromResource(
            this,
            R.array.formulas,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears.
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner.
            spinner.adapter = adapter
        }
        //-------------------------------------------------------

        //-------------Acciones al seleccionar algo del spinner------------------
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val opcionSeleccionada = parent.getItemAtPosition(position).toString()
                onSpinnerItemSelected(opcionSeleccionada) // Llama a la función con la opción seleccionada
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }
        //-------------------------------------------------------



    }

    fun onSpinnerItemSelected(selectedOption: String) {
        // lógica  cuando se selecciona un elemento

        val position_formula= binding.mySpinner.selectedItemPosition.toString()

        if (position_formula=="0")
        {
            binding.tvVar1.visibility=View.INVISIBLE
            binding.tvVar2.visibility=View.INVISIBLE
            binding.editTextNumber1.visibility=View.INVISIBLE
            binding.editTextNumber2.visibility=View.INVISIBLE
            binding.boton1Calcular.visibility=View.INVISIBLE
            binding.boton2Limpiar.visibility=View.INVISIBLE
        }
        else {
            binding.textViewResultado.text = position_formula

            binding.tvVar1.visibility=View.VISIBLE
            binding.tvVar2.visibility=View.VISIBLE
            binding.editTextNumber1.visibility=View.VISIBLE
            binding.editTextNumber2.visibility=View.VISIBLE
            binding.boton1Calcular.visibility=View.VISIBLE
            binding.boton2Limpiar.visibility=View.VISIBLE

            if (position_formula=="1")
            {
                binding.tvVar1.text="m"
                binding.tvVar2.text="v"
            }
            else if (position_formula=="2")
            {
                binding.tvVar1.text="a"
                binding.tvVar2.text="b"
            }
            else if (position_formula=="3")
            {
                binding.tvVar1.text="R"
                binding.tvVar2.text="I"
            }


        }
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

    fun clickLimpiar(view: View){
        //Quitar seleccion de formula
        //Quitar imagen de formula
        binding.editTextNumber1.text.clear()
        binding.editTextNumber2.text.clear()
        binding.textViewResultado.text=""
        binding.tvVar1.visibility=View.INVISIBLE
        binding.tvVar2.visibility=View.INVISIBLE
        binding.mySpinner.setSelection(0)

    }



}