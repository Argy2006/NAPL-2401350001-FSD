open System

let workerTask (taskId: int) (waitTime: int) : Async<string> =
    async {
        printfn "Starting Task %d" taskId

        do! Async.Sleep(waitTime)

        printfn "Completed Task %d" taskId

        return sprintf "Result of Task %d" taskId
    }

let main () =
    printfn "Starting concurrent tasks"

    let jobs = [
        workerTask 1 2000
        workerTask 2 3000
        workerTask 3 1000
    ]

    let results = Async.RunSynchronously (Async.Parallel jobs)

    results
    |> Array.iter (fun result ->
        printfn "Completed: %s" result
    )

main ()