# raging-kumquat
Maximum common sequence

Use Dynamic Programming to find the longest common sequence (LCS) of two character strings.

## To build and run
This project assumes you have leiningen and Clojure installed.

Start by cloning the project.  To test, cd to *raging-kumquat* and enter ```lein test```. 

To see comparative results of the three approaches to the algorithm, enter

```
lein run "ababgdcddca" "gdcabcdgccab"
```

## Dynamic Programming and Memoization

This started out being an experiment in Dynamic Programming, and ended up being a learning 
experience with memoization and atoms.

The concept of memoization has been part of Dynamic Programming since the 1960s. The fundamental idea is pretty simple: don't
recompute pure functions a huge number of times.  Memoization can transform a problem that requires exponential time to
a problem with polynomial upper bounds.

## Atoms and the *memoize* Function

After I got ```lein run``` to work, unit tests still failed.  The problem was that the code included atom definitions such as:

```
(def list-cache (atom {}))
```

The "defs" were persisting across execution of different tests. The work-around for explicit, 
hand-rolled memoization was to create an atom in the wrapper function and pass that to the 
recursive  function.

The next problem was that a naive attempt to use the *memoize* function of Clojure failed.
Here the fix was to make sure that the recursive calls within the LCS function were also
using the memoized function.
