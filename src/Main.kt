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
    var candidates_selected_by_voter = ballotFile?.readLine()
    while (candidates_selected_by_voter != null) {
        if ("none" in candidates_selected_by_voter) {
            empty++
        } else {
            candidates_selected_by_voter.split(" ").distinct().forEach { vote ->
                votes[vote] = votes.getOrDefault(vote, 0) + 1
            }
        }
        if (ballotFile != null) {
            candidates_selected_by_voter = ballotFile.readLine()
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

    // get list of all candidates
    val voteKeys = votes.keys.toList()

    var candidate_selected_by_voter_2 = ballotFile2?.readLine()
    while (candidate_selected_by_voter_2 != null) {
        if (candidate_selected_by_voter_2.split(" ").sorted() == voteKeys.sorted()) {
            full++
        }
        candidate_selected_by_voter_2 = ballotFile2?.readLine()
    }

    ballotFile2?.close()

    // print number of votes
    val voteItems = votes.toList().sortedByDescending { it.second }
    var counter = 0

    voteItems.forEach { (candidate, count) ->
        if (counter == 0) println("$candidate: $count (winner)")
        else println("$candidate: $count")
        counter++
    }

    // print number of empty and full ballots
    println("\nempty: $empty")
    println("full: $full")
}