package uk.carwynellis.bloom

import org.scalatest.FunSuite

class FalsePositivesSpec extends FunSuite {
  test("makeRandomString should return a string of the specified length") {
    val rs = FalsePositives.makeRandomString(5)
    assert(rs.length == 5)
  }
}
