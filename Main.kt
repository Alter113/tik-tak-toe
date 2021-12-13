package tictactoe

fun main() {
    val foundWinner = false
    var turnNumber = 0
    var turnOf = 'X'
    val tempInput = "_________".toCharArray()
    var xCounter = 0
    var oCounter = 0
    var spaceCounter = 0
    while (!foundWinner) {

        grid(tempInput.joinToString(""))


        val input = readLine()!!
        if (input.length != 3) continue
        val coordinates = inputToInt(input)

        val xWins = xAdder(tempInput.joinToString(""))

        val oWins = oAdder(tempInput.joinToString(""))

        val draw = if (winner(oWins, xWins, spaceCounter) == "Draw") 1 else 0

        val winSum = xWins + oWins + draw


        for (i in tempInput) {
            when (i) {
                'X' -> xCounter++
                'O' -> oCounter++
                '_' -> spaceCounter++
            }
        }

        val check = check(input, coordinates, tempInput.joinToString(""), winSum, spaceCounter)
        val winner = winner(oWins, xWins, spaceCounter)

        if (check == "correct" && winner == "Game not finished") {
                tempInput[inputToInt(input)] = turnOf
                when (turnOf) {
                    'X' -> turnOf = 'O'
                    'O' -> turnOf = 'X'
                }
                turnNumber++
                continue

        } else if (check != "correct" && winner == "Game not finished") {
            println(check)
            continue
        } else if (check != "correct" && winner != "Game not finished") {
            println(check)
            continue
        } else if (check == "correct" && winner != "Game not finished") {
            break
        }
}
    val xWins = xAdder(tempInput.joinToString(""))

    val oWins = oAdder(tempInput.joinToString(""))

    when (winner(oWins, xWins, spaceCounter)) {
        "O wins" -> {
            println("O wins")
        }
        "X wins" -> {
            println("X wins")
        }
        "Draw" -> {
            println("Draw")

        }
    }
}


// grid printer
fun grid (input: String) {
    println("")
    println("---------")
    println("| ${input[0]} ${input[1]} ${input[2]} |")
    println("| ${input[3]} ${input[4]} ${input[5]} |")
    println("| ${input[6]} ${input[7]} ${input[8]} |")
    println("---------")
}
// add 1 to "xWins" each time "x" get 3 in a row
fun xAdder (tempInput: String): Int {
    var xWins = 0
    if (tempInput[0] == 'X' && tempInput[1] == 'X' && tempInput[2] == 'X') xWins++
    if (tempInput[3] == 'X' && tempInput[4] == 'X' && tempInput[5] == 'X') xWins++
    if (tempInput[6] == 'X' && tempInput[7] == 'X' && tempInput[8] == 'X') xWins++
    if (tempInput[0] == 'X' && tempInput[3] == 'X' && tempInput[6] == 'X') xWins++
    if (tempInput[1] == 'X' && tempInput[4] == 'X' && tempInput[7] == 'X') xWins++
    if (tempInput[2] == 'X' && tempInput[5] == 'X' && tempInput[8] == 'X') xWins++
    if (tempInput[0] == 'X' && tempInput[4] == 'X' && tempInput[8] == 'X') xWins++
    if (tempInput[2] == 'X' && tempInput[4] == 'X' && tempInput[6] == 'X') xWins++
    return xWins
}
// add 1 to "oWins" each time "o" get 3 in a row
fun oAdder (tempInput: String): Int {
    var oWins = 0
    if (tempInput[0] == 'O' && tempInput[1] == 'O' && tempInput[2] == 'O') oWins++
    if (tempInput[3] == 'O' && tempInput[4] == 'O' && tempInput[5] == 'O') oWins++
    if (tempInput[6] == 'O' && tempInput[7] == 'O' && tempInput[8] == 'O') oWins++
    if (tempInput[0] == 'O' && tempInput[3] == 'O' && tempInput[6] == 'O') oWins++
    if (tempInput[1] == 'O' && tempInput[4] == 'O' && tempInput[7] == 'O') oWins++
    if (tempInput[2] == 'O' && tempInput[5] == 'O' && tempInput[8] == 'O') oWins++
    if (tempInput[0] == 'O' && tempInput[4] == 'O' && tempInput[8] == 'O') oWins++
    if (tempInput[2] == 'O' && tempInput[4] == 'O' && tempInput[6] == 'O') oWins++
    return oWins
}
// prints the different states of the game
fun winner (oWins: Int, xWins:Int, spaceCounter: Int): String {
    val winner = when {
            oWins > 0 -> "O wins"
            xWins > 0 -> "X wins"
            xWins == 0 && oWins == 0 && spaceCounter == 0 -> "Draw"
            xWins == 0 && oWins == 0 && spaceCounter > 0 -> "Game not finished"
            else -> "Impossible"
        }
    return winner
}
fun check (input: String, number: Int, tempInput : String, winSum: Int, spaceCounter: Int): String {

    val coordinates = input.split(" ").toMutableList()

    var out = false// if some number is out of the rang 1..3 but still number:
    var notNumber = false //just not number xd
    var correct = false// prints grid with changes
    val occuped = tempInput[number] == 'O' || tempInput[number] == 'X' && winSum == 0 && spaceCounter > 0
    for (i in coordinates) {
        when {
            i.toInt() in 1..3 -> correct = true
            i.toInt() > 3 || i.toInt() < 1 -> {
                out = true
                correct = false
            }
            else -> {
                notNumber = true
                correct = false
            }
        }
    }
    val str = when {
        out -> "Coordinates should be from 1 to 3!"
        notNumber -> "You should enter numbers!"
        occuped -> "This cell is occupied! Choose another one!"
        correct -> "correct"
        else -> "Impossible"
    }
    return str
}
// transform input into number from 0..8
fun inputToInt (input: String): Int {
    val coordinates = when (input) {
            "1 1" -> 0
            "1 2" -> 1
            "1 3" -> 2
            "2 1" -> 3
            "2 2" -> 4
            "2 3" -> 5
            "3 1" -> 6
            "3 2" -> 7
            "3 3" -> 8
            else -> 0
        }
    return coordinates
    }

