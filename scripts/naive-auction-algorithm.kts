// Naive auction algorithm
// (not guaranteed to terminate)
//
// INPUT
// A n x n Matrix with rows being persons and columns being objects
// each point in the matrix determines the value of assigning the
// person to that object.
//
// OUTPUT
// The result of auction() is an array of length n with indices
// begin the persons in order (0,1,2...) and the values being the
// index of the object assigned.
fun auction(matrix: Array<IntArray>): IntArray {
  val n = matrix.count()

  val objectPrices = Array(n) { 0 }
  val personToObjectAssignments = Array<Int?>(n) { null }

  fun assignBest(personIndex: Int)  {
    // Find the best object and it's value for this person,
    // and keep track of the 2nd best object value as well
    var objectIndex = 0
    var valueFst = -1
    var valueSnd = -1
    for ((_objectIndex, personObjectValue) in matrix[personIndex].withIndex()) {
      val v = personObjectValue - objectPrices[_objectIndex]
      if (v > valueFst) {
        // best value found so far
        valueSnd = valueFst;
        valueFst = v;
        objectIndex = _objectIndex;
      } else if (v > valueSnd) {
        // 2nd best value found so far
        valueSnd = v;
      }
    }

    // Best object found, unassign from previous bidder (if any)
    val prev = personToObjectAssignments.indexOf(objectIndex)
    if (prev != -1) {
      personToObjectAssignments[prev] = null
    }

    // Store new price and new person-object assignment
    objectPrices[objectIndex] += valueFst - valueSnd
    personToObjectAssignments[personIndex] = objectIndex
  }

  // As long there are unassigned person
  // try find a best object match for such person
  // IMPORTANT: this naive algorithm will not always terminates
  while (true) {
    val personIndex = personToObjectAssignments.indexOf(null)
    if (personIndex == -1) break;
    assignBest(personIndex)
  }

  return IntArray(n) { i -> personToObjectAssignments[i] as Int }
}



// Example
val input = arrayOf(
  intArrayOf(1, 2),
  intArrayOf(4, 3)
)
val output = auction(input);

// Print example to console
println()
println("EXAMPLE")
println("=======")
println()
println("matrix with person-object pair values:")
for ((i, values) in input.withIndex()) {
  println("person $i")
  for ((j, value) in values.withIndex()) {
    println("  gets a value of $value for object $j")
  }
}
println("")
println("Resulting in the following assignments:")
for ((i, j) in output.withIndex()) {
  println("person $i to object $j")
}
println()
