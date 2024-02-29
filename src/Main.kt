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

    // close ballet text file
    ballotFile?.close()

    println("\nTotal # of ballots: $numBallots\n")

    // import ballot text file to determine number of "full" (voted all) ballots
    val ballotFile2 = ballotFileName?.let { fileName ->
        java.io.File(fileName).bufferedReader()
    }

    val voteKeys = votes.keys.toList()

    var candidate2 = ballotFile2?.readLine()
    while (candidate2 != null) {
        if (candidate2.split(" ").sorted() == voteKeys.sorted()) {
            full++
        }
        candidate2 = ballotFile2?.readLine()
    }

    ballotFile2?.close()
}