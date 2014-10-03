package uk.carwynellis.bloom

import scala.util.hashing.byteswap32
import scala.util.hashing.MurmurHash3

/**
 * Singleton encapsulating a collection of hashing algorithms with a consistent
 * interface.
 *
 * Implemented this way as an alternative to defining an abstract super class with hash specific
 * sub-classes.
 */

object Hashing {

  val murmurHash: (String) => Int = (element: String) => MurmurHash3.stringHash(element)

  val byteSwapHash: (String) => Int = (element: String) => {
    val sum = element.map(c => c.toInt).sum
    byteswap32(sum)
  }

  val hashes = List(murmurHash, byteSwapHash)
}
