package main

import "errors"

// Structure for an item
type Item struct {
	ID       int
	Name     string
	Quantity int
	Price    float64
}

// Slice to store items
var items []Item

// Function to add an item
func addItem(id int, name string, quantity int, price float64) error {

	if name == "" {
		return errors.New("item name cannot be empty")
	}

	if quantity < 0 {
		return errors.New("quantity cannot be negative")
	}

	if price < 0 {
		return errors.New("price cannot be negative")
	}

	item := Item{id, name, quantity, price}

	items = append(items, item)

	return nil
}

// Function to display all items
func displayItems() {

	if len(items) == 0 {
		println("Inventory is empty")
		return
	}

	for _, item := range items {
		println("ID:", item.ID)
		println("Name:", item.Name)
		println("Quantity:", item.Quantity)
		println("Price:", item.Price)
	}
}

// Function to update an item
func updateItem(id int, name string, quantity int, price float64) error {

	if name == "" {
		return errors.New("item name cannot be empty")
	}

	if quantity < 0 {
		return errors.New("quantity cannot be negative")
	}

	if price < 0 {
		return errors.New("price cannot be negative")
	}

	for i := 0; i < len(items); i++ {

		if items[i].ID == id {
			items[i].Name = name
			items[i].Quantity = quantity
			items[i].Price = price

			return nil
		}
	}

	return errors.New("item not found")
}

// Function to remove an item
func removeItem(id int) error {

	newItems := []Item{}
	found := false

	for _, item := range items {

		if item.ID == id {
			found = true
		} else {
			newItems = append(newItems, item)
		}
	}

	if found == false {
		return errors.New("item not found")
	}

	items = newItems

	return nil
}

// Structure for inventory summary
type Summary struct {
	ItemCount  int
	TotalValue float64
}

// Function to calculate summary using goroutine and channel
func calculateSummary() <-chan Summary {

	ch := make(chan Summary)

	go func() {

		var summary Summary

		summary.ItemCount = len(items)

		for _, item := range items {
			summary.TotalValue += float64(item.Quantity) * item.Price
		}

		ch <- summary
	}()

	return ch
}
