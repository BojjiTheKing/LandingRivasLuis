package com.alejandrorivas.conversor_rivasluis

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import com.alejandrorivas.conversor_rivasluis.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    enum class Mode(val label: String, val inUnit: String, val outUnit: String) {
        C_TO_F("Convertir °C a °F", "°C", "°F"),
        KG_TO_LB("Convertir kg a lb", "kg", "lb"),
        L_TO_OZ("Convertir L a fl oz", "L", "fl oz"),
        KM_TO_MI("Convertir km a millas", "km", "mi")
    }

    private var mode: Mode = Mode.C_TO_F

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        updateUI()

        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            mode = when (checkedId) {
                binding.rbTemp.id -> Mode.C_TO_F
                binding.rbPeso.id -> Mode.KG_TO_LB
                binding.rbVol.id -> Mode.L_TO_OZ
                binding.rbLong.id -> Mode.KM_TO_MI
                else -> Mode.C_TO_F
            }
            updateUI()
        }

        binding.btnConvertir.setOnClickListener { convert() }

        binding.inputValor.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                convert()
                true
            } else false
        }
    }

    private fun updateUI() {
        binding.labelConversion.text = mode.label
        binding.inputValor.hint = "Valor en ${mode.inUnit}"
        binding.txtResultado.text = ""
        binding.inputValor.error = null
    }

    private fun convert() {
        val txt = binding.inputValor.text.toString().trim()

        if (txt.isEmpty()) {
            binding.inputValor.error = "Ingresa un valor"
            return
        }

        val value = txt.toDoubleOrNull()
        if (value == null) {
            binding.inputValor.error = "Número no válido"
            return
        }

        val result = when (mode) {
            Mode.C_TO_F -> value * 9.0 / 5.0 + 32.0
            Mode.KG_TO_LB -> value * 2.20462262185
            Mode.L_TO_OZ -> value * 33.8140226
            Mode.KM_TO_MI -> value * 0.621371
        }

        binding.txtResultado.text = "Resultado: %.2f %s".format(result, mode.outUnit)
    }
}
