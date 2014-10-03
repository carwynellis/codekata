package uk.carwynellis.bloom

import scala.io.Source
import scala.io.StdIn.readLine

/**
 * Simple command line interface to a bloom filter set populated with a dictionary
 * of words which could be used as a spellchecker.
 *
 * See http://codekata.com/kata/kata05-bloom-filters/
 */

object SpellCheck {
  val FilterSetSize = 1000000

  def main(args: Array[String]): Unit = {
    val words = Source.fromURL(getClass.getResource("/words"))
    val set = new BloomFilterSet(FilterSetSize)

    // Populate the bloom filter set with words
    words.getLines() foreach { set add }

    while (true) {
      val word = readLine("enter word > ").toLowerCase
      if (set.exists(word)) {
        println(s"word '$word' exists in dictionary set")
      }
      else {
        println(s"word '$word' does not exist in dictionary set")
      }
    }
  }
}
