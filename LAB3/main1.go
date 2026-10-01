package main

import "fmt"

type Student struct {
	Name  string
	Age   int
	Marks float64
}

func modifyValue(x *int) {
	*x = 100
}

func modifystructure(s *Student) {
	fmt.Print("Enter name: ")
	fmt.Scan(&s.Name)
	fmt.Print("Enter age: ")
	fmt.Scan(&s.Age)
	fmt.Print("Enter marks: ")
	fmt.Scan(&s.Marks)
}

func main() {

	// part1
	num := 50

	fmt.Println("Value:", num)
	fmt.Println("Address:", &num)
	fmt.Println("Value using pointer:", *(&num))

	// part2
	value := 20

	fmt.Println("Before modification:", value)
	modifyValue(&value)
	fmt.Println("After modification:", value)

	// part3
	s1 := new(Student)

	fmt.Println("default values before modification ")

	fmt.Println("Name: ", s1.Name)
	fmt.Println("Age: ", s1.Age)
	fmt.Println("Marks: ", s1.Marks)

	fmt.Println("\nEnter Student Details:")
	modifystructure(s1)

	fmt.Println("\nStudent Details:")
	fmt.Println("Name:", s1.Name)
	fmt.Println("Age:", s1.Age)
	fmt.Println("Marks:", s1.Marks)
}
