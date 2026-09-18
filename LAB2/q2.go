package main

import (
	"fmt"
	"slices"
)

func main() {
	var n int

	fmt.Print("Enter number of elements: ")
	fmt.Scan(&n)

	a := make([]int, n)

	fmt.Println("Enter elements:")
	for i := 0; i < n; i++ {
		fmt.Scan(&a[i])
	}

	fmt.Println("Initial slice:", a)

	var x int
	fmt.Print("Enter element to add: ")
	fmt.Scan(&x)

	a = append(a, x)
	fmt.Println("After adding:", a)

	var index int
	fmt.Print("Enter index to remove: ")
	fmt.Scan(&index)

	a = slices.Delete(a, index, index+1)
	fmt.Println("After removing:", a)

	fmt.Print("Enter index to update: ")
	fmt.Scan(&index)

	fmt.Print("Enter new value: ")
	fmt.Scan(&x)

	a[index] = x
	fmt.Println("After updating:", a)

	m := make(map[string]int)

	var subject string
	var marks int

	fmt.Print("Enter subject: ")
	fmt.Scan(&subject)

	fmt.Print("Enter marks: ")
	fmt.Scan(&marks)

	m[subject] = marks
	fmt.Println("After inserting:", m)

	fmt.Print("Enter subject to lookup: ")
	fmt.Scan(&subject)

	fmt.Println("Marks:", m[subject])

	fmt.Print("Enter subject to delete: ")
	fmt.Scan(&subject)

	delete(m, subject)
	fmt.Println("After deleting:", m)
}
