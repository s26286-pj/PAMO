/*
 * Write a function called circleArea that takes the radius of a circle in integer format as a parameter and outputs the area of that circle.
 */
import kotlin.math.PI
fun circleArea(radius: Int): Double {
	return radius * radius * PI
}
fun main() {
	println(circleArea(2))
}