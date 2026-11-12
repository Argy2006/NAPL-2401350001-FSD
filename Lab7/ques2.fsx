open System

let list1 = [1; 2; 3; 4; 5]
let list2 = [2; 4; 6; 8; 10]

let square1 = List.map (fun x -> x * x) list1
let square2 = List.map (fun x -> x * x) list2

printfn "List.map Results:"
printfn "%A" square1
printfn "%A" square2

let even1 = List.filter (fun x -> x % 2 = 0) list1
let even2 = List.filter (fun x -> x % 2 = 0) list2

printfn "\nList.filter Results:"
printfn "%A" even1
printfn "%A" even2

let sum1 = List.fold (fun acc x -> acc + x) 0 list1
let sum2 = List.fold (fun acc x -> acc + x) 0 list2

let product1 = List.fold (fun acc x -> acc * x) 1 list1
let product2 = List.fold (fun acc x -> acc * x) 1 list2

let max1 = List.fold (fun acc x -> if x > acc then x else acc) (List.head list1) list1
let max2 = List.fold (fun acc x -> if x > acc then x else acc) (List.head list2) list2

printfn "\nList.fold Results:"

printfn "Sum of first list = %d" sum1
printfn "Sum of second list = %d" sum2

printfn "Product of first list = %d" product1
printfn "Product of second list = %d" product2

printfn "Maximum of first list = %d" max1
printfn "Maximum of second list = %d" max2
