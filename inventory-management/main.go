package main

import "fmt"

func main() {

	// Add items
	err := addItem(1, "Laptop", 5, 50000)

	if err != nil {
		fmt.Println("Error:", err)
	} else {
		fmt.Println("Laptop added successfully")
	}

	err = addItem(2, "Mouse", 10, 500)

	if err != nil {
		fmt.Println("Error:", err)
	} else {
		fmt.Println("Mouse added successfully")
	}

	// Display items
	fmt.Println("\nInventory:")
	displayItems()

	// Update an item
	fmt.Println("\nUpdating Laptop:")

	err = updateItem(1, "Gaming Laptop", 3, 60000)

	if err != nil {
		fmt.Println("Error:", err)
	} else {
		fmt.Println("Item updated successfully")
	}

	// Remove an item
	fmt.Println("\nRemoving Mouse:")

	err = removeItem(2)

	if err != nil {
		fmt.Println("Error:", err)
	} else {
		fmt.Println("Item removed successfully")
	}

	// Display remaining items
	fmt.Println("\nUpdated Inventory:")
	displayItems()

	// Calculate summary using a goroutine and channel
	fmt.Println("\nInventory Summary:")

	summary := <-calculateSummary()

	fmt.Println("Total Item Types:", summary.ItemCount)
	fmt.Println("Total Inventory Value:", summary.TotalValue)
}
