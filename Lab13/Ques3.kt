import java.util.Scanner

class Student(
    rollNo: Int,
    name: String,
    course: String,
    percentage: Double
) {
    var rollNo: Int = rollNo
        private set

    var name: String = name
        private set

    var course: String = course
        private set

    var percentage: Double = percentage
        private set

    fun updateDetails(scanner: Scanner) {
        print("Enter Roll No: ")
        val newRoll = scanner.nextInt()

        scanner.nextLine()
        print("Enter Name: ")
        val newName = scanner.nextLine().trim()

        print("Enter Course: ")
        val newCourse = scanner.nextLine().trim()

        print("Enter Percentage: ")
        val newPercentage = scanner.nextDouble()

        rollNo = newRoll
        name = newName
        course = newCourse
        percentage = newPercentage
    }

    fun displayDetails() {
        println("------------------------------")
        println("Roll No    : $rollNo")
        println("Name       : $name")
        println("Course     : $course")
        println("Percentage : ${"%.2f".format(percentage)}%")
        println("------------------------------")
    }
}

fun main() {
    val scanner = Scanner(System.`in`)

    val student1 = Student(101, "Default Student 1", "B.Tech", 0.0)
    val student2 = Student(102, "Default Student 2", "B.Tech", 0.0)

    println("Enter details for Student 1:")
    student1.updateDetails(scanner)
    student1.displayDetails()

    println("Enter details for Student 2:")
    student2.updateDetails(scanner)
    student2.displayDetails()
}