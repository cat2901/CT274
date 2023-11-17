// B2004810 - Tran Thanh Thien
fun sayHello(name: String){
    println("Hello "+name)
}

// Function with returns value
fun sum(x: Double, y: Double): Double{
    return x + y
}
fun showColor(red: Int, green: Int, blue: Int, alpha: Float){
    println("color: $red - $green - $blue - $alpha")
}

// Function with Variadic Arguments - vararg
fun like(vararg fruits: String){
    for (fruits in fruits){
        println("I like $fruits")
    }
}

// Infix functions
// Can be called without using the period and brackets
infix fun Int.plus(x: Int): Int{
    return this + x
}
infix fun Int.times(x: Int): Int = this * x // one-line function
infix fun String.loves(name: String) = "$this loves $name"
