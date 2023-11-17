//// B2004810 - Tran Thanh Thien
//fun main(args: Array<String>) {
//    println("Hello World!")
//
//    // Variable
//    var x = 100
//    println("x is: $x")
//
//    // String Template (noi ki tu)
//    var a:Int = 200
//    println("x is: $x ; a is: $a")
//
//    // Change value of a variable
//    x = 50*100
//    println("Now x is: $x")
//
//    // Value (Const)
//    val const = 777 // Val = cannot be assigned
//    // const = 500
//    println("const: $const")
//
//    // Function
////    fun sayHello(name: String){
////        println("Hello "+name)
////    }
//
//    sayHello("Thanh Thien")
//
//    println("sum 2.5 and 4.5 is: ${sum(2.5, 4.5)}") // rcm
//    // or:
//    println("sum 2.5 and 5.5 is: ${sum(x = 2.5, y = 5.5)}")
//
//    showColor(255, 0, 0, 0.7f)
//
//    like("Apple", "Orange", "Kiwi", "Cherry")
//
//    val pl = 12 plus 5
//    println("pl = $pl")
//    println("5 + 2 is: ${5 plus 2}")
//    val t = 12 times 5
//    println("t = $t")
//    println("5 * 2 is: ${5 times  2}")
//    println("Romeo" loves "Juliet")
//    var mess:String = "Juliet" loves "Romeo"
//    println("$mess")
//
//}

// B2004810 - Tran Thanh Thien
// Cau 1
fun isPrimeNumber(a: Int): Boolean{
    var check:Boolean = true
    for(i in 2..(a-1)){
        if(a%i==0){
            check = false
            break
        }
    }
    if(a<=1)
        check = false
    return check
}

// Cau 2
fun selectionSort(nums: IntArray) {
    val lastIndex: Int = nums.size - 1

    for (i in 0..(lastIndex - 1)) {
        var minIndex = i

        for (j in (i+1)..lastIndex) {
            if(nums[minIndex] > nums[j]) {
                minIndex = j
            }
        }

        val temp = nums[minIndex]
        nums[minIndex] = nums[i]
        nums[i] = temp
    }
}
fun tripletsEqualZero(nums: IntArray){
    for(k in 0..(nums.size-2)){
        var left: Int = k+1
        var right: Int = nums.size-1
        while(left<right){
            var S: Int = nums[k]+nums[left]+nums[right]
            if(S==0){
                println("[${nums[k]}, ${nums[left]}, ${nums[right]}]")
                break
            }
            else if(S<0){
                left++
            }
            else{
                right--
            }
        }
    }
}

// --- BUOI 3 ---
open class Animal(var height: Double = 0.0, var weight: Double = 0.0) {
    var name: String = ""
    init {
        name = name.toUpperCase()
        if (height < 0) height = 0.0
        if (weight < 0) weight = 0.0
    }

    open fun move() {
        println("moving")
    }
}

class Dog(height: Double = 0.0, weight: Double = 0.0, name: String = "") : Animal(height, weight) {
    init {
        this.name = name.toUpperCase()
    }

    constructor(name: String) : this(0.0, 0.0, name)

    override fun move() {
        println("running")
    }
}

class Bird(height: Double = 0.0, weight: Double = 0.0, name: String = "") : Animal(height, weight) {
    init {
        this.name = name.toUpperCase()
    }

    constructor(name: String) : this(0.0, 0.0, name)

    override fun move() {
        println("flying")
    }
}

class Person(var firstName: String = "", var lastName: String = "") {
    val fullName: String
        get() = "$firstName $lastName"

    private val pets = mutableListOf<Animal>()

    fun addPet(pet: Animal) {
        pets.add(pet)
    }

    fun printPets() {
        for (i in 0 until pets.size) {
            println("Pet $i:")
            println("Name: ${pets[i].name}")
            println("Height: ${pets[i].height}")
            println("Weight: ${pets[i].weight}")
        }
    }
}


fun main() {
    // --- BUOI 2 ---
    // Cau 1
//    println("--- Cau 1 ---")
//    val x=-6
//    val y=30
//
//    for(i in x..y){
//        if(isPrimeNumber(i))
//            print("$i ")
//    }
//
//    // Cau 2
//    println("\n\n--- Cau 2 ---")
//    val nums: IntArray = intArrayOf(-1, 0, 1, 2, -1, -4)
//    selectionSort(nums)
//    tripletsEqualZero(nums)

    // --- BUOI 3 ---
//    var list = mutableListOf(1, 2, 3, 4 ,5)
//    list.add(6)
//    println(list)
//    println(list.first())
//    println(list.last())
//    println(list.min())
//    println(list.max())
//    var list2 = list.filter { it % 2 == 0 }
//    println(list2)

//    println(list.any{it==3})
//    println(list.all { it % 2 == 0 })
//    println(list.count { it % 2 == 0 })
//    println(list.sumBy { it % 2 })
//    var lst=list.map { it + 1 }
//    lst.forEach { println(it) }

    // Bai Tap
//    var l = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
//    l.forEach {
//        var cnt=0
//        for (i in 1.. it){
//            if(it%i==0)
//                cnt++
//        }
//        if(cnt==3)
//            println(it)
//    }
//    val judy = Dog(height = 30.0, weight = 20.0, name = "Judy")
//    val pinky = Dog(height = 25.0, weight = 15.0, name = "Pinky")
//    val max = Bird(height = 15.0, weight = 10.0, name = "Max")
//    val helios = Bird(height = 20.0, weight = 5.0, name = "Helios")
//
//    val person1 = Person(firstName = "John", lastName = "Nguyen")
//    person1.addPet(judy)
//    person1.addPet(max)
//
//    val person2 = Person(firstName = "Ryze", lastName = "Tran")
//    person2.addPet(pinky)
//    person2.addPet(helios)
//
//    person1.printPets()
//    person2.printPets()

}








