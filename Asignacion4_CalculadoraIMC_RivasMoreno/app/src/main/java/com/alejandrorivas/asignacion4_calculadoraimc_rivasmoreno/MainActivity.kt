package com.alejandrorivas.asignacion4_calculadoraimc_rivasmoreno

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val weight : EditText = findViewById(R.id.etWeight) as EditText
        val height : EditText = findViewById(R.id.etHeight) as EditText
        val btnResultado = findViewById<Button>(R.id.botonResultado)
        val tvRange = findViewById<TextView>(R.id.tvRange)
        val tvImc = findViewById<TextView>(R.id.tvImc)

        btnResultado.setOnClickListener{
            val weightString = weight.text.toString()
            val inputWeight: Double = weightString.toDouble()

            val heightString = height.text.toString()
            val inputHeight: Double =heightString.toDouble()

            val resultadoImc = imc(weightString, heightString)

            if (resultadoImc <18.5) {
                tvImc.setText("IMC: "+ resultadoImc)
                tvRange.setText("Bajo Peso")
                tvRange.setBackgroundColor(ContextCompat.getColor(this, R.color.colorGreenish))


            } else if (resultadoImc >= 18.5 && resultadoImc < 25.0) {
                tvImc.setText("IMC: "+ resultadoImc)
                tvRange.setText("Saludable")
                tvRange.setBackgroundColor(ContextCompat.getColor(this, R.color.colorGreen))


            } else if (resultadoImc >= 25.0  && resultadoImc < 30.0) {
                tvImc.setText("IMC: "+ resultadoImc)
                tvRange.setText("Sobrepeso")
                tvRange.setBackgroundColor(ContextCompat.getColor(this, R.color.colorYellow))

            } else if (resultadoImc >= 30.0  && resultadoImc < 35.0){
                tvImc.setText("IMC: "+ resultadoImc)
                tvRange.setText("Obesidad grado 1")
                tvRange.setBackgroundColor(ContextCompat.getColor(this, R.color.colorOrange))



            }else if (resultadoImc >= 35.0  && resultadoImc < 39.0){
                tvImc.setText("IMC: "+ resultadoImc)
                tvRange.setText("Obesidad grado 2")
                tvRange.setBackgroundColor(ContextCompat.getColor(this, R.color.colorRed))


            }else if (resultadoImc >= 40){

                tvImc.setText("IMC: "+ resultadoImc)
                tvRange.setText("Obesidad grado 3")
                tvRange.setBackgroundColor(ContextCompat.getColor(this, R.color.colorBrown))

            }

        }

    }
    fun imc(weight: String, height: String) : Double
    {
        val height2: Double = height.toDouble()*height.toDouble()
        return weight.toDouble()/height2
    }
}
