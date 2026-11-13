module StudentApp

open System
open Microsoft.Data.Sqlite
open System.Threading.Tasks

type Student = {
    Id: int
    Name: string
    Age: int
    Grade: string
}

let searchStudent (searchTerm: string) (students: Student list) : Student list =
    students 
    |> List.filter (fun s -> s.Name.ToLower().Contains(searchTerm.ToLower()))

let getTopStudentsNames (students: Student list) : string list =
    students
    |> List.filter (fun s -> s.Grade = "A")
    |> List.map (fun s -> s.Name)

let getGradeMessage (student: Student) : string =
    match student.Grade with
    | "A" -> sprintf "%s is doing fantastic with an A!" student.Name
    | "B" -> sprintf "%s is doing well with a B." student.Name
    | "C" -> sprintf "%s passed with a C." student.Name
    | _   -> sprintf "%s needs some extra help." student.Name

let connectionString = "Data Source=students.db"

let setupDatabaseAsync () = async {
    use connection = new SqliteConnection(connectionString)
    do! connection.OpenAsync() |> Async.AwaitTask
    
    let sql = """
        CREATE TABLE IF NOT EXISTS Students (
            Id INTEGER PRIMARY KEY AUTOINCREMENT,
            Name TEXT NOT NULL,
            Age INTEGER NOT NULL,
            Grade TEXT NOT NULL
        );
    """
    use command = new SqliteCommand(sql, connection)
    do! command.ExecuteNonQueryAsync() |> Async.AwaitTask |> Async.Ignore
}

let addStudentAsync (name: string) (age: int) (grade: string) = async {
    use connection = new SqliteConnection(connectionString)
    do! connection.OpenAsync() |> Async.AwaitTask

    let sql = "INSERT INTO Students (Name, Age, Grade) VALUES (@name, @age, @grade);"
    use command = new SqliteCommand(sql, connection)
    
    command.Parameters.AddWithValue("@name", name) |> ignore
    command.Parameters.AddWithValue("@age", age) |> ignore
    command.Parameters.AddWithValue("@grade", grade) |> ignore

    do! command.ExecuteNonQueryAsync() |> Async.AwaitTask |> Async.Ignore
    printfn "Added student: %s" name
}

let getAllStudentsAsync () = async {
    use connection = new SqliteConnection(connectionString)
    do! connection.OpenAsync() |> Async.AwaitTask

    let sql = "SELECT Id, Name, Age, Grade FROM Students;"
    use command = new SqliteCommand(sql, connection)
    use! reader = command.ExecuteReaderAsync() |> Async.AwaitTask

    let mutable list = []
    while reader.Read() do
        let student = {
            Id = reader.GetInt32(0)
            Name = reader.GetString(1)
            Age = reader.GetInt32(2)
            Grade = reader.GetString(3)
        }
        list <- student :: list

    return List.rev list
}

let updateStudentGradeAsync (id: int) (newGrade: string) = async {
    use connection = new SqliteConnection(connectionString)
    do! connection.OpenAsync() |> Async.AwaitTask

    let sql = "UPDATE Students SET Grade = @grade WHERE Id = @id;"
    use command = new SqliteCommand(sql, connection)
    command.Parameters.AddWithValue("@grade", newGrade) |> ignore
    command.Parameters.AddWithValue("@id", id) |> ignore

    let! rows = command.ExecuteNonQueryAsync() |> Async.AwaitTask
    printfn "Updated %d row(s) for ID %d." rows id
}

let deleteStudentAsync (id: int) = async {
    use connection = new SqliteConnection(connectionString)
    do! connection.OpenAsync() |> Async.AwaitTask

    let sql = "DELETE FROM Students WHERE Id = @id;"
    use command = new SqliteCommand(sql, connection)
    command.Parameters.AddWithValue("@id", id) |> ignore

    let! rows = command.ExecuteNonQueryAsync() |> Async.AwaitTask
    printfn "Deleted %d student(s) with ID %d." rows id
}

[<EntryPoint>]
let main argv =
    async {
        do! setupDatabaseAsync ()

        do! addStudentAsync "Aryan" 20 "A"
        do! addStudentAsync "Krish" 20 "B"
        do! addStudentAsync "Tarun" 19 "A"

        let! students = getAllStudentsAsync ()
        students |> List.iter (fun s -> printfn "%A" s)

        students |> List.iter (fun s -> printfn "%s" (getGradeMessage s))

        let topStudents = getTopStudentsNames students
        printfn "Top Students (Grade A): %A" topStudents

        if not (List.isEmpty students) then
            let firstStudent = List.head students
            do! updateStudentGradeAsync firstStudent.Id "C"

        let! latestStudents = getAllStudentsAsync ()
        if not (List.isEmpty latestStudents) then
            let lastStudent = List.head latestStudents
            do! deleteStudentAsync lastStudent.Id

        let! finalStudents = getAllStudentsAsync ()
        finalStudents |> List.iter (fun s -> printfn "%A" s)
    } 
    |> Async.RunSynchronously

    0