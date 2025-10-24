package com.example.countdownapp_marinadelrio

import android.os.Bundle
import android.widget.*

import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class CalculatorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        // Buscar los campos y botones a través de su id
        val dateEditText = findViewById<EditText>(R.id.editTextDate)
        val timeEditText = findViewById<EditText>(R.id.editTextTime)
        val calculateButton = findViewById<Button>(R.id.buttonCalculate)
        val resultTextView = findViewById<TextView>(R.id.textResult)
        val buttonBack = findViewById<ImageButton>(R.id.buttonBack)

        // Configurar la lógica del botón de calcular
        calculateButton.setOnClickListener {

            //Reiniciar el texto del campo de resultados cada vez que se hace click al botón
            resultTextView.text = ""

            val dateString = dateEditText.text.toString().trim()
            val timeString = timeEditText.text.toString().trim()


            // Comprobar si los campos están llenos.
            if (dateString.isNotEmpty() && timeString.isNotEmpty()) {

                // Si están llenos,intentar hacer el cálculo (try and catch)
                try {
                    val dateTimeString = "$dateString $timeString"
                    val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
                    val futureDate: Date = dateFormat.parse(dateTimeString)!!
                    val futureMillis = futureDate.time
                    val currentMillis = System.currentTimeMillis()

                    // Comprobar si la fecha es futura.
                    if (futureMillis >= currentMillis) {

                        val diffMillis = futureMillis - currentMillis

                        val days = diffMillis / (1000 * 60 * 60 * 24)
                        val hours = (diffMillis / (1000 * 60 * 60)) % 24
                        val minutes = (diffMillis / (1000 * 60)) % 60
                        val seconds = (diffMillis / 1000) % 60

                        val resultText = "$days d, $hours h, $minutes m, $seconds s"
                        resultTextView.text = resultText
                        Toast.makeText(this, resultText, Toast.LENGTH_LONG).show()

                    } else {
                        // ERROR: La fecha está en el pasado.
                        Toast.makeText(this, "Please enter a future date and time.", Toast.LENGTH_SHORT).show()
                    }

                } catch (e: Exception) {
                    // ERROR: El formato de la fecha o la hora es incorrecto.
                    Toast.makeText(this, "Incorrect format. Use dd/MM/yyyy and HH:mm.", Toast.LENGTH_LONG).show()
                }

            } else {
                // ERROR: Uno o ambos campos están vacíos.
                Toast.makeText(this, "Please enter date and time", Toast.LENGTH_SHORT).show()
            }
        }

        // Configurar la lógica del botón de volver
        buttonBack.setOnClickListener {
            finish()
        }
    }
}