package uk.carwynellis.bloom

import org.scalatest.FunSuite

class BloomFilterSetSpec extends FunSuite {

  test("an empty set should report false for exists on anything") {
    val bs = new BloomFilterSet(1)
    assertResult(false) { bs exists "Foo" }
  }

  test("exists should report true for an element added to an empty set") {
    val bs = new BloomFilterSet(10)
    val element = "This is a value"
    bs.add(element)
    assert(bs exists element)
  }
}
