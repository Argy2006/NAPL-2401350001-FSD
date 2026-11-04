open System
open System.Text.RegularExpressions

let checkPalindrome (str: string) =
    let reversed = String(Array.rev (str.ToCharArray()))

    printfn "Reversed String: %s" reversed

    if str.ToLower() = reversed.ToLower() then
        printfn "It is a Palindrome"
    else
        printfn "It is Not a Palindrome"


let transform (str: string) =
    if str.Length > 0 && (str |> Seq.forall Char.IsLetter) then
        str.ToUpper()
    elif str.Length > 0 && (str |> Seq.forall Char.IsDigit) then
        str + str
    else
        str


let emailCheck (mail: string) =
    Regex.IsMatch(mail, @"^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$")


printfn "----- STRING PROCESSING -----"

printf "\nEnter a string to check palindrome: "
let first = Console.ReadLine()

checkPalindrome first

printf "\nEnter a string to transform: "
let second = Console.ReadLine()

printfn "Transformed String: %s" (transform second)

printf "\nEnter an email address: "
let mail = Console.ReadLine()

if emailCheck mail then
    printfn "Valid Email Address"
else
    printfn "Invalid Email Address"