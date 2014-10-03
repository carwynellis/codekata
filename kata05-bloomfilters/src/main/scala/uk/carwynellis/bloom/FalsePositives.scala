package uk.carwynellis.bloom

import scala.annotation.tailrec
import scala.collection.mutable
import scala.io.Source
import scala.util.Random

/**
 * Tests a populated bloom filter set for false positives.
 *
 * The same words file is loaded in as per the SpellCheck example and randomly
 * generated 5 character strings are tested for their presence in the set.
 *
 * If present this is then checked against the dictionary file.
 *
 * This is just an exercise to demonstrate the likelihood of false positives
 * when using bloom filters.
 *
 * See: http://codekata.com/kata/kata05-bloom-filters/
 */

object FalsePositives {
  val FilterSetSize = 1000000
  // BloomFilterSet bitmap resolution
  val WordAttempts = 1000
  // Number of random words to try
  val WordLength = 5
  // Length of randomly generated words, in chars
  val rand = new Random()

  // TODO - support command line options for set size, number of tests and word length
  def main(args: Array[String]): Unit = {
    val words = Source.fromURL(getClass.getResource("/words"))
    val set = new BloomFilterSet(FilterSetSize)
    val dict = new mutable.HashMap[String, Boolean]

    // Populate the bloom filter set and a hashmap so we can cross reference
    // when the bloom filter set reports a positive to determine whether it's a
    // false positive or not.
    words.getLines() foreach (word => {
      set.add(word)
      dict.put(word, true)
    })


    // Build the list of words that are reported as false positives by the bloom filter set
    val randomWords = for(i <- 1 to WordAttempts) yield makeRandomString(WordLength)
    val falsePositives = randomWords.filter(w => set.exists(w) && !dict.contains(w))

    println(s"False Positives Matched: $falsePositives")
    println(s"For $WordAttempts attempts, found ${falsePositives.length} false positives")
  }

  /**
   * Generates a random string of alphabetic characters
   * 
   * @param l
   * @return
   */
  def makeRandomString(l: Int = 5): String = {
    val chars = 'a' to 'z'

    @tailrec
    def genString(s: String): String = {
      if (s.length == l) s
      else genString(s + chars.charAt(rand.nextInt(chars.length)))
    }

    genString("")
  }
}
