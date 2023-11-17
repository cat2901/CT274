package com.example.ttcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import org.mariuszgromada.math.mxparser.Expression

class MainActivity : AppCompatActivity() {

    var tvResult:TextView? = null

    var lastNum:Boolean = false
    var lastDot:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       tvResult = findViewById(R.id.tvResult)

        val btnClear:Button = findViewById(R.id.btnClear)
        btnClear.setOnClickListener {
            "".also { tvResult?.text = it }
            lastNum = false
            lastDot = false
            tvResult?.setTextColor(ContextCompat.getColor(this, R.color.white))
        }

        val btnDel:Button = findViewById(R.id.btnDel)
        btnDel.setOnClickListener {
            tvResult?.text = tvResult?.text!!.substring(0, tvResult?.text!!.length-1)


        }

        val btnDot:Button = findViewById(R.id.btnDot)
        btnDot.setOnClickListener {
            if(lastNum and !lastDot){
                tvResult?.append(".")
                lastDot = true
            }

        }

        val btnEqual:Button = findViewById(R.id.btnEqual)
        btnEqual.setOnClickListener {
            onResult()
        }
    }

    fun onDigital(view: View) {
        tvResult?.append((view as Button).text)
        lastNum = true
    }

    fun onOperator(view: View) {

        if (lastNum){
            tvResult?.append((view as Button).text)
            lastDot = false
            lastNum = false
        }
    }

    private fun removeZero(num:String):String{
        if (num.endsWith(".0")){
            return num.substring(0, num.length-2)
        }
        return num
    }

    private fun onResult(){
        val exp = Expression(tvResult?.text!!.toString()
            .replace("×","*")
            .replace("÷", "/")
        )
        val _rs = exp.calculate()
        if(_rs.isNaN()){
            tvResult?.text = "Error"
            tvResult?.setTextColor(ContextCompat.getColor(this, R.color.red))
        }
        else{
            tvResult?.text = removeZero(_rs.toString())
            tvResult?.setTextColor(ContextCompat.getColor(this, R.color.white))
        }
    }
}