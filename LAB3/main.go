package main

import "fmt"

type Person struct {
	Name   string
	Age    int
	Job    string
	Salary float64
}

func (p Person) readData() Person {
	fmt.Print("Enter name: ")
	fmt.Scan(&p.Name)

	fmt.Print("Enter age: ")
	fmt.Scan(&p.Age)

	fmt.Print("Enter job: ")
	fmt.Scan(&p.Job)

	fmt.Print("Enter salary: ")
	fmt.Scan(&p.Salary)

	return p
}

func (p Person) printData() {
	fmt.Printf("Name: %s\n", p.Name)
	fmt.Printf("Age: %d\n", p.Age)
	fmt.Printf("Job: %s\n", p.Job)
	fmt.Printf("Salary: %.2f\n", p.Salary)
}

func main() {
	var p1, p2 Person

	fmt.Println("Enter details for Person 1:")
	p1 = p1.readData()

	fmt.Println("\nEnter details for Person 2:")
	p2 = p2.readData()

	fmt.Println("\nPerson 1:")
	p1.printData()

	fmt.Println("\nPerson 2:")
	p2.printData()
}
