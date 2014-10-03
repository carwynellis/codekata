package uk.carwynellis.bloom

import scala.annotation.tailrec
import scala.math.abs

/**
 * Initial attempt at defining an object that encapsulates a simple bloom filter.
 *
 * See: http://codekata.com/kata/kata05-bloom-filters/
 *
 * @param resolution size of the bloomfilter bitmap, defaults to 1000
 */
class BloomFilterSet(val resolution: Int = 1000) {
  // Stores the bloom filter bitmap
  private val bitmap = new Array[Boolean](resolution)

  /**
   * Adds an element to the set
   *
   * @param element the element string to add to the set
   */
  def add(element: String): Unit =
    Hashing.hashes map { h =>
      bitmap(computeArrayIndex(element, h)) = true
    }

  /**
   * Returns true if element is present in the set.
   *
   * @param element the element string to test for set membership
   * @return true if element exists, false otherwise
   */
  def exists(element: String): Boolean = {
    // Collect the bitmap values associated with the element and the hashing
    // algorithms in use in the Hashing singleton.
    val bits = Hashing.hashes map { h =>
      bitmap(computeArrayIndex(element, h))
    }
    // If any bit is false then we know the value is definitely not in the set
    // So test for the presence of false and invert the result to determine if
    // the specified element exists or not.
    ! bits.contains(false)
  }

  /**
   * Computes an array index for the specified element and hashing algorithm
   * for the current bloom filter bitmap.
   *
   * @param element the element string to be hashed
   * @param h reference to a hash function that takes a string and returns an int
   * @return an array index which can be used to set or query the bitmap array
   */
  private def computeArrayIndex(element: String, h: (String) => Int): Int = {
    // Hash function may return a negative number so abs since we will derive
    // an array index from this value.
    val hash = abs(h(element))

    // If the hash value exceeds our bitmap resolution then scale it down
    // until we have a value that does not exceed our resolution
    @tailrec
    def calcIndex(i: Int): Int = {
      if (i < resolution) i
      else calcIndex(i / 2)
    }
    calcIndex(hash)
  }
}
