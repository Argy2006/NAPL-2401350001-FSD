open System

printfn "Enter marks of four subjects out of 100:"

let a = int(Console.ReadLine())
let b = int(Console.ReadLine())
let c = int(Console.ReadLine())
let d = int(Console.ReadLine())

let totalMarks = a + b + c + d

let percent = float(totalMarks) / 4.0

let division =
    if percent >= 60.0 then
        "First Division"
    elif percent >= 50.0 then
        "Second Division"
    elif percent >= 40.0 then
        "Third Division"
    else
        "Fail"

printfn "Total Marks = %d" totalMarks
printfn "Percentage = %.2f%%" percent
printfn "Division = %s" division