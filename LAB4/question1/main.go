package main

import (
	"fmt"
	"sync"
	"time"
)

func square(ch chan string, wg *sync.WaitGroup) {
	defer wg.Done()

	fmt.Println("Square goroutine started")
	time.Sleep(300 * time.Millisecond) // simulate work
	result := 5 * 5
	ch <- fmt.Sprintf("Square of 5 = %d", result)
	fmt.Println("Square goroutine completed")
}

func cube(ch chan string, wg *sync.WaitGroup) {
	defer wg.Done()

	fmt.Println("Cube goroutine started")
	time.Sleep(200 * time.Millisecond) // simulate work
	result := 3 * 3 * 3
	ch <- fmt.Sprintf("Cube of 3 = %d", result)
	fmt.Println("Cube goroutine completed")
}

func fibonacci(ch chan string, wg *sync.WaitGroup) {
	defer wg.Done()

	fmt.Println("Fibonacci goroutine started")
	time.Sleep(100 * time.Millisecond) // simulate work

	a, b := 0, 1
	result := []int{a, b}
	for i := 2; i < 7; i++ {
		a, b = b, a+b
		result = append(result, b)
	}

	ch <- fmt.Sprintf("Fibonacci sequence = %v", result)
	fmt.Println("Fibonacci goroutine completed")
}

func main() {
	ch := make(chan string, 3) // channel of type string
	var wg sync.WaitGroup

	fmt.Println("Main function started")

	wg.Add(3)
	go square(ch, &wg)
	go cube(ch, &wg)
	go fibonacci(ch, &wg)

	// Close the channel after all goroutines finish
	go func() {
		wg.Wait()
		close(ch)
	}()

	// Collect and print results in main()
	for result := range ch {
		fmt.Println("Result:", result)
	}

	fmt.Println("Main function completed")
}
