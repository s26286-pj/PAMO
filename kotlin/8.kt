/*
 * You have a list of words. Use for and if to print only the words that start with the letter l.
 */
fun main() {
    val words = listOf("dinosaur", "limousine", "magazine", "language")
    for (word in words) {
        if (word.startsWith("l"))
        	println(word)
        }
}