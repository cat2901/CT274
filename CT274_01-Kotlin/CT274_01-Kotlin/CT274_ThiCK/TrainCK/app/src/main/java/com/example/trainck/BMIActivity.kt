package com.example.trainck

import android.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.trainck.databinding.ActivityBmiactivityBinding

class BMIActivity : AppCompatActivity() {

    lateinit var binding: ActivityBmiactivityBinding

    var metricSelected: String? = null
    var genderSelected: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmiactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val metricOptions = arrayOf(Constants.METRIC_OPT1, Constants.METRIC_OPT2)
        val metricAdapter = ArrayAdapter(this@BMIActivity, R.layout.simple_spinner_item, metricOptions)
        metricAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.metricSpinner.adapter = metricAdapter
        binding.metricSpinner.onItemSelectedListener = object
            : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                metricSelected = metricOptions[p2]
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        val genderOptions = arrayOf(Constants.GENDER_OPT1, Constants.GENDER_OPT2, Constants.GENDER_OPT3)
        val genderAdapter = ArrayAdapter(this, R.layout.simple_spinner_item, genderOptions)
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.genderSpinner.adapter = genderAdapter
        binding.genderSpinner.onItemSelectedListener = object
            : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    genderSelected = genderOptions[p2]
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        binding.btnCalculate.setOnClickListener {
            calculateBMI()
        }
        binding.btnReset.setOnClickListener {
            resetView()
        }
        binding.btnBack.setOnClickListener {
            val intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }




    }

    private fun checkValid(): Boolean{
        var age: Int? = binding.etAge.text.toString().toIntOrNull()
        var height: Float? = binding.etHeight.text.toString().toFloatOrNull()
        var weight: Float? = binding.etWeight.text.toString().toFloatOrNull()
        if(age ==null || height == null || weight == null)
            return false
        return true
    }

    private fun createUser(): User{
        val name = intent.getStringExtra(Constants.NAME)
        val age = binding.etAge.text.toString().toInt()
        val gender = genderSelected
        val weight = binding.etWeight.text.toString().toFloat()
        val height = binding.etHeight.text.toString().toFloat()

        return User(name!!, age, gender!!, weight, height)
    }

    private fun calculateBMI() {
        if(!checkValid())
            Toast.makeText(this, "Invalid input !!!", Toast.LENGTH_SHORT).show()
        else run {
            val usr: User = createUser()
            var BMI: Float? = null

            BMI=if (metricSelected.equals(Constants.METRIC_OPT1)) {
                usr.height /= 100.0f
                usr.weight / (usr.height * usr.height)
            } else
                703*usr.weight / (usr.height * usr.height)

            var usrCategory: String? = null
            if (BMI < 16)
                usrCategory = "Severe Thinness"
            else if (BMI >= 16 && BMI < 17)
                usrCategory = "Moderate Thinness"
            else if (BMI >= 17 && BMI < 18.5)
                usrCategory = "Mild Thinness"
            else if (BMI >= 18.5 && BMI < 25)
                usrCategory = "Normal"
            else if (BMI >= 25 && BMI < 30)
                usrCategory = "Overweight"
            else if (BMI >= 30 && BMI < 35)
                usrCategory = "Obese Class I"
            else if (BMI >= 35 && BMI < 40)
                usrCategory = "Obese Class II"
            else
                usrCategory = "Obese Class III"

            binding.tvUserInfor.setText("Name: ${usr.name}, Age: ${usr.age}, Gender: ${usr.gender}")
            binding.tvUserBMI.setText("%.2f".format(BMI))
            binding.tvUserCategory.setText(usrCategory)
        }
    }

    private fun resetView(){
        binding.etAge.setText("")
        binding.etWeight.setText("")
        binding.etHeight.setText("")

        binding.tvUserInfor.setText("Empty")
        binding.tvUserBMI.setText("Unknown")
        binding.tvUserCategory.setText("Unknown")
    }

}