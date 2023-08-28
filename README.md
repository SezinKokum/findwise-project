# findwise-project
# Java assignment – Simple search engine

The search engine will take the query from the user. 

The search engine is implemented as an inverted index that runs in memory returns a result list that is sorted by TF-IDF.

## Installation

The project is written with Spring Boot and can be imported to any spring boot environment.

## Usage

When the SearchEngine is started, user can query word. If the user wants to end the Search Engine, has to press q to finish it.

![output](https://github.com/SezinKokum/findwise-project/assets/29343187/1033a0a0-9b12-4233-95c2-e58b941e4b54)

The documents and their contents are given as static variables in the code. 

The following documents are indexed:

Document 1: “the brown fox jumped over the brown dog”

Document 2: “the lazy brown dog sat in the corner”

Document 3: “the red fox bit the lazy dog”

![invertedindexx](https://github.com/SezinKokum/findwise-project/assets/29343187/1e2c393d-e9a8-49ea-936b-0079e345b25e)

A search for “brown” should now return the list: [document 1, document 2].

A search for “fox” should return the list: [document 3, document 1]

![imagescorefox](https://github.com/SezinKokum/findwise-project/assets/29343187/d570e4d5-b703-453e-8529-09cb7c23ee7c)

But if user wants to use SearchEngine with different contents and documents, can call createDocumentsWithPath instead of createDocuments method in Controller class. Then add the txt files into resources folder of the project. While running the code, user has to give their filepath for the txt files when it asks Enter file path.
