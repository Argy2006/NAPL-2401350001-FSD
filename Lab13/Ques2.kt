fun greetMember(name: String, role: String = "Developer", level: Int = 1) {
    println("Name: $name, Role: $role, Level: $level")
}

fun printNumbers(vararg numbers: Int) {
    for (n in numbers) {
        print("$n ")
    }
    println()
}

fun transformList(items: List<Int>, operation: (Int) -> Int): List<Int> {
    val result = mutableListOf<Int>()
    for (item in items) {
        result.add(operation(item))
    }
    return result
}

fun main() {
    greetMember("Aryan")
    greetMember("Aryan", "Full Stack Dev", 3)

    greetMember(level = 4, name = "Aryan", role = "Architect")

    printNumbers(10, 20, 30, 40, 50)

    val nums = listOf(1, 2, 3, 4, 5)
    val squared = transformList(nums) { x -> x * x }
    println("Transformed list: $squared")
}