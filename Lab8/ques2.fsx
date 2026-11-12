#r "nuget: Microsoft.Data.Sqlite, 8.0.0"
#r "nuget: MongoDB.Driver, 2.28.0"

open System
open Microsoft.Data.Sqlite
open MongoDB.Bson
open MongoDB.Driver

// ==========================================
// PART 1: SQLITE DATABASE
// ==========================================

printfn "===== SQLITE DATABASE ====="

let con = new SqliteConnection("Data Source=lab8.db")
con.Open()

// Create Table
let cmd = con.CreateCommand()
cmd.CommandText <-
    "CREATE TABLE IF NOT EXISTS Students
     (Id INTEGER PRIMARY KEY, Name TEXT, Age INTEGER)"
cmd.ExecuteNonQuery() |> ignore

// CREATE
let addStudent = con.CreateCommand()
addStudent.CommandText <-
    "INSERT OR REPLACE INTO Students VALUES (1, 'Aryan', 20)"
addStudent.ExecuteNonQuery() |> ignore

// Display Records
let displayData() =
    let readCmd = con.CreateCommand()
    readCmd.CommandText <- "SELECT * FROM Students"

    use reader = readCmd.ExecuteReader()

    while reader.Read() do
        printfn "ID: %d  Name: %s  Age: %d"
            (reader.GetInt32(0))
            (reader.GetString(1))
            (reader.GetInt32(2))

printfn "\nAfter Create:"
displayData()

// READ
printfn "\nAfter Read:"
displayData()

// UPDATE
let editCmd = con.CreateCommand()
editCmd.CommandText <-
    "UPDATE Students SET Age = 21 WHERE Id = 1"
editCmd.ExecuteNonQuery() |> ignore

printfn "\nAfter Update:"
displayData()

// DELETE
let removeCmd = con.CreateCommand()
removeCmd.CommandText <-
    "DELETE FROM Students WHERE Id = 1"
removeCmd.ExecuteNonQuery() |> ignore

printfn "\nAfter Delete:"
displayData()

con.Close()


// ==========================================
// PART 2: MONGODB DATABASE
// ==========================================

printfn "\n===== MONGODB DATABASE ====="

// MongoDB Atlas Connection

let username = "techforaryan0209_db_user"

// PUT YOUR NEW PASSWORD HERE
let password = Uri.EscapeDataString("YOUR_NEW_PASSWORD")

let url =
    $"mongodb+srv://techforaryan0209_db_user:xPtHwoi5VbitjAsi@cluster0.w0k37uz.mongodb.net/?appName=Cluster0"

let mongoClient = MongoClient(url)

let db = mongoClient.GetDatabase("StudentDB")

let students = db.GetCollection<BsonDocument>("Students")

let search =
    Builders<BsonDocument>.Filter.Eq("_id", 1)

// CREATE
let data = BsonDocument()
data.Add("_id", 1)
data.Add("Name", "Aryan")
data.Add("Age", 20)

students.DeleteOne(search)
students.InsertOne(data)

printfn "\nAfter Create:"
let result1 = students.Find(search).FirstOrDefault()
printfn "%s" (result1.ToJson())

// READ
printfn "\nAfter Read:"
let result2 = students.Find(search).FirstOrDefault()
printfn "%s" (result2.ToJson())

// UPDATE
let change =
    Builders<BsonDocument>.Update.Set("Age", 21)

students.UpdateOne(search, change)

printfn "\nAfter Update:"
let result3 = students.Find(search).FirstOrDefault()
printfn "%s" (result3.ToJson())

// DELETE
students.DeleteOne(search)

printfn "\nAfter Delete:"
let result4 = students.Find(search).FirstOrDefault()

if isNull result4 then
    printfn "Record deleted successfully!"

printfn "\nAll CRUD Operations Completed!"