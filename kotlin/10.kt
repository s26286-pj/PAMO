/*
 * Rewrite the circleArea function from the previous exercise as a single-expression function.
 */
import kotlin.math.PI
fun circleArea(radius: Int): Double = radius * radius * PI

fun main() {
	println(circleArea(2))
}