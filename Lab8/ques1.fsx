open System

[<AbstractClass>]
type Shape() =
    abstract member Area: unit -> float

type Circle(r: float) =
    inherit Shape()

    override this.Area() =
        3.14 * r * r

type Rectangle(l: float, b: float) =
    inherit Shape()

    override this.Area() =
        l * b

let c = Circle(5.0)
let r = Rectangle(4.0, 6.0)

printfn "Circle Area: %f" (c.Area())
printfn "Rectangle Area: %f" (r.Area())

let shapes: Shape list = [c :> Shape; r :> Shape]

printfn "\nPolymorphism:"

for s in shapes do
    printfn "Area: %f" (s.Area())