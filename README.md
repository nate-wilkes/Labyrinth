# Labyrinth Project

Noah (could be anyone named "Noah," really...) is trapped in the Labyrinth with the Minotaur!
Your task is to help him escape before he gets eaten (Minotaur's are fond of eating freshmen).

[Javadoc](https://friendsbaltcs.github.io/docs/ACS/Labyrinth/)

## Set Up

You will be given a class called `Labyrinth.` A Labyrinth is a rectangular grid of squares
which are either made out of stone or lava. Noah can walk on stone, but not lava (roast
freshman is even more delicious to the Minotaur). In order to escape the Labyrinth, Noah must
walk from the top left square--(0,0)--to the bottom right--(rows-1,cols-1)--both of which are
made out of stone. To help you, the Labyrinth class has some helpful methods

1. A constructor: `Labyrinth(int rows, int cols)` which builds a random solvable labyrinth
with `rows` rows of squares and `cols` columns of squares,
2. A method `isStone(row, col)` to tell if the given row and column are stone, and
3. A method `isValid(row, col)` to tell if the given row and column are on within the grid.
4. A method `solves(int[] solution)` which checks your solution to see if it will rescue Noah!

## Solution

You should use backtracking to find a path through the Labyrinth. Your final solution will
be represented as an `int[]` array of integers from 0 through 3. 0 Represents a step one
grid square up, 1 a step down, 2 left, and 3 right. You should write a class which contains
a `solve(Labyrinth l)` method which returns this `int[]` array of instructions to navigate
the given Labyrinth from the top left to the bottom right corners.

A complete description of the Labyrinth class can be found in the Javadoc above.
