package main

import "testing"

// Test adding an item
func TestAddItem(t *testing.T) {

	items = nil

	err := addItem(1, "Laptop", 5, 50000)

	if err != nil {
		t.Error("Item was not added")
	}

	if len(items) != 1 {
		t.Error("Item count should be 1")
	}
}

// Test updating an item
func TestUpdateItem(t *testing.T) {

	items = nil

	addItem(1, "Laptop", 5, 50000)

	err := updateItem(1, "Mouse", 10, 500)

	if err != nil {
		t.Error("Item was not updated")
	}

	if items[0].Name != "Mouse" {
		t.Error("Item name was not updated")
	}
}

// Test removing an item
func TestRemoveItem(t *testing.T) {

	items = nil

	addItem(1, "Laptop", 5, 50000)

	err := removeItem(1)

	if err != nil {
		t.Error("Item was not removed")
	}

	if len(items) != 0 {
		t.Error("Inventory should be empty")
	}
}
