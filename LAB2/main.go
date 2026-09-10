package main

import (
	"MyProject/mathutil"
	"MyProject/strop"
	"fmt"
)

func main() {
	var str string
	fmt.Print("Enter a string: ")
	fmt.Scan(&str)
	reversed := strop.Reverse(str)
	fmt.Printf("The reversed string is: %s\n", reversed)
	var str2 string
	fmt.Print("Enter another string: ")
	fmt.Scan(&str2)
	vowelCount := strop.CountVowels(str2)
	fmt.Printf("The number of vowels in the string is: %d\n", vowelCount)
	var n int
	fmt.Print("Enter a number: ")
	fmt.Scan(&n)
	fact := mathutil.Factorial(n)
	fmt.Printf("The factorial of %d is: %d\n", n, fact)
	var base, exponent int
	fmt.Print("Enter base: ")
	fmt.Scan(&base)
	fmt.Print("Enter exponent: ")
	fmt.Scan(&exponent)
	power := mathutil.Power(base, exponent)
	fmt.Printf("%d raised to the power of %d is: %d\n", base, exponent, power)
}
