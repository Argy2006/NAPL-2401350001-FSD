package strop

func Reverse(s string) string {
	arr := []rune(s)

	left := 0
	right := len(arr) - 1

	for left < right {
		temp := arr[left]
		arr[left] = arr[right]
		arr[right] = temp

		left++
		right--
	}

	return string(arr)
}

func CountVowels(s string) int {
	vowels := "aeiouAEIOU"
	count := 0
	for _, char := range s {
		for _, vowel := range vowels {
			if char == vowel {
				count++
				break
			}
		}
	}
	return count
}
