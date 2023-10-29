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
import android.widget.ImageView
import kotlin.math.pow
import kotlin.math.sqrt
import androidx.core.content.ContextCompat

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
            binding.textViewResultado.visibility=View.INVISIBLE
            binding.imageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.inicio))

        }
        else {

            binding.tvVar1.visibility=View.VISIBLE
            binding.tvVar2.visibility=View.VISIBLE
            binding.editTextNumber1.visibility=View.VISIBLE
            binding.editTextNumber2.visibility=View.VISIBLE
            binding.boton1Calcular.visibility=View.VISIBLE
            binding.boton2Limpiar.visibility=View.VISIBLE
            binding.textViewResultado.visibility=View.VISIBLE

            if (position_formula=="1")//Energía Cinética
            {
                binding.textViewResultado.text=""
                binding.tvVar1.text="m"
                binding.tvVar2.text="v"
                binding.imageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.cinetica))
            }
            else if (position_formula=="2")//Teorema de Pitágoras
            {
                binding.textViewResultado.text=""
                binding.tvVar1.text="a"
                binding.tvVar2.text="b"
                binding.imageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.pitagoras))
            }
            else if (position_formula=="3")//Ley de Ohm
            {
                binding.textViewResultado.text=""
                binding.tvVar1.text="I"
                binding.tvVar2.text="R"
                binding.imageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ohm))
            }


        }
    }

    fun calculaFormula(numero1:Int, numero2:Int):Double{

        val position_formula= binding.mySpinner.selectedItemPosition.toString()

        val num1d=numero1.toDouble()
        val num2d=numero2.toDouble()
        var resultado=0.0
        val unidad="[None]"

        if (position_formula=="1")//Energía Cinética
        {
            //KE = (1/2) * m * v^2
            resultado = (0.5) * num1d * num2d.pow(2.0)

        }
        else if (position_formula=="2")//Teorema de Pitágoras
        {
            //c=(a^2+b^2)^(1/2)
            resultado= sqrt( num1d.pow(2.0) + num2d.pow(2.0) )
        }
        else if(position_formula=="3")//Ley de Ohm
        {
            //V=RI
            resultado= num1d * num2d
        }

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

        binding.editTextNumber1.text.clear()
        binding.editTextNumber2.text.clear()
        binding.textViewResultado.text=""
    }



}

