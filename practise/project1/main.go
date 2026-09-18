// take a input from user for the marks using switch case and print first for above 70 and second for below 60 AND third for between 50 and 70
package main

import (
	"fmt"
)

func main() {
	var marks int
	fmt.Print("Enter your marks: ")
	fmt.Scan(&marks)

	switch {
	case marks >= 60:
		fmt.Println("First division")
	case marks >= 50:
		fmt.Println("Second division")
	case marks >= 40:
		fmt.Println("Third division")
	default:
		fmt.Println("fail")
	}
}
