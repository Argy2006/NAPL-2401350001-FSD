import java.util.Scanner
import kotlin.math.abs

fun main() {
    println("Hello Kotlin!!!")

    val scanner = Scanner(System.`in`)

    print("Enter salary: ")
    val salary = scanner.nextDouble()

    print("Enter department code (IT, HR, FIN): ")
    val deptCode = scanner.next().uppercase()

    print("Enter years of experience: ")
    val experience = scanner.nextInt()

    val bonusRate = when {
        deptCode == "IT" && experience > 5 -> 0.20
        deptCode == "HR" && experience > 3 -> 0.15
        deptCode == "FIN" && experience > 4 -> 0.18
        else -> 0.10
    }

    val bonus = salary * bonusRate
    println("Bonus Rate: ${(bonusRate * 100).toInt()}%")
    println("Computed Bonus: $bonus")

    print("Enter an integer: ")
    val num = scanner.nextLong()

    var temp = abs(num)
    if (temp == 0L) {
        println("Average of digits: 0.0")
        return
    }

    var sum = 0L
    var count = 0

    while (temp > 0L) {
        sum += temp % 10L
        count++
        temp /= 10L
    }

    val average = sum.toDouble() / count
    println("Average of digits: $average")
}