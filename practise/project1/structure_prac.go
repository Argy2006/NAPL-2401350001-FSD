package main

import "fmt"

type Student struct {
	Name  string
	Age   int
	Marks float64
}

func main() {
	// create instance
	s := Student{
		Name:  "Aryan",
		Age:   20,
		Marks: 85.5,
	}

	fmt.Println(s)
	fmt.Printf("Name of the student: %s\n", s.Name)
	fmt.Printf("Age of the student: %d\n", s.Age)
	fmt.Printf("Marks of the student: %.2f\n", s.Marks)
}
