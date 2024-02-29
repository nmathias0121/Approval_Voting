fun main() {
    // input ballot text file location
    // accepts both absolute path and repository path
    // right click on text file to obtain/copy path
    println("Enter the location of the ballot file?")
    val ballotFileName = readLine()

    // import ballot text file
    val ballotFile = ballotFileName?.let { fileName ->
        java.io.File(fileName).bufferedReader()
    }
}