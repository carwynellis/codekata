package uk.carwynellis.bloom

import org.scalatest.FunSuite

/**
 * Simple tests for the Hash function wrappers
 */

class HashingSpec extends FunSuite {

  test("murmurHash should return an int value") {
    assert(Hashing.murmurHash("foo").isInstanceOf[Int])
  }

  test("byteSwapHash should return an int value") {
    assert(Hashing.byteSwapHash("foo").isInstanceOf[Int])
  }

}