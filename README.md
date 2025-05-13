# COMPX201-Assigment2-Part2

Author: 20233006416

Short Description
-------------------
This program implements a Binary Search Tree (BST) and a command-line Computer Science dictionary application as part of COMPX201 Assignment 2 - Part 2

Part Two builds upon this to create a dictionary (`DictionaryBST.java` and `DictionaryLookup.java`) that can load terms and definitions from a file, and allows users to search, add, remove, and print dictionary entries.


Files Included
-------------------
Core Java Files:
* DictionaryBST.java: Implements the Binary Search Tree specifically for storing dictionary words and their definitions.
* DictionaryLookup.java: Provides the main command-line interface for interacting with the dictionary.

Supporting Files:
* dictionary.txt: A sample dictionary file containing terms and definitions. This file is expected by `DictionaryLookup.java`.



Dictionary Features (Part Two - DictionaryLookup)
-------------------
The `DictionaryLookup` program allows the user to:
1.  Search for a word/phrase in the dictionary.
2.  Print a given word/phrase and its definition.
3.  Add a new word/phrase and its definition to the dictionary.
4.  Remove a word/phrase and its definition from the dictionary.
5.  Print all words/phrases and their definitions in alphabetical order.
6.  Exit the application.


How to Run
-------------------
After successful compilation, run the dictionary application from the same directory using:

java DictionaryLookup

The program will then load the `dictionary.txt` file and present a menu of options.


Assumptions / Notes
-------------------
* The `dictionary.txt` file must be present in the same directory where the `java DictionaryLookup` command is executed for the program to load the initial dictionary data successfully.
* The `loadDictionaryFromFile` method in `DictionaryLookup.java` uses a simplified parser designed to work with the provided `dictionary.txt` format. This parser expects:
    * Dictionary entries to be separated by one or more blank lines.
    * Within an entry, the word and its definition are separated by the first colon (':').
    * Definitions that span multiple lines within an entry block will be concatenated into a single definition string (with internal newlines replaced by spaces).
* If a duplicate word is inserted via the "Add" functionality, its definition will be updated.

