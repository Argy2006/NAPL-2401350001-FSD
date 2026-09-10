package mathutil

// Create a custom Go package containing at least two string-manipulation functions (e.g., Factorial, Power).

func Factorial(n int) int {
	if n == 0 {
		return 1
	}
	return n * Factorial(n-1)
}

func Power(base, exponent int) int {
	power := 1
	for i := 1; i <= exponent; i++ {
		power *= base
	}
	return power
}
