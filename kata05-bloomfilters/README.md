# Name

Code Kata 5 - Bloom Filters

# Description

Coding exercise to implement a simple bloom filter with multiple hashing
algorithms.

Includes a simple dictionary lookup and also code to compare the bloom filter
with a dictionary using randomly generated words to determine the number of 
false positives reported by the bloom filter.

See http://codekata.com/kata/kata05-bloom-filters/

# Notes

* Hashing algorithms wrapped with functions on a singleton rather than using
  standard OO style
* BloomFilterSet exists method could be optimised to exit early on finding a
  'false' value. The bloom filter may report a false positive but we can be
  certain that if a false value is associated with a given string that that
  string is not a member of the set. So we can optimised by exiting early if a
  false value is found rather than testing all hashing algorithms as in the
  current implementation.

