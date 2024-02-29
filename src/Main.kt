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

    val votes = mutableMapOf<String, Int>()
    var numBallots = 0
    var empty = 0
    var full = 0

    // extract votes out of ballot text file
    var candidate = ballotFile?.readLine()
    while (candidate != null) {
        if ("none" in candidate) {
            empty++
        } else {
            candidate.split(" ").distinct().forEach { vote ->
                votes[vote] = votes.getOrDefault(vote, 0) + 1
            }
        }
        if (ballotFile != null) {
            candidate = ballotFile.readLine()
        }
        numBallots++
    }
    println(numBallots)
}