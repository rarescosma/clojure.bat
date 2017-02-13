# bat

This project accompanies my journey through the excelent book [Clojure for the Brave and True](https://www.goodreads.com/book/show/20873338-clojure-for-the-brave-and-true)

It's also my first attempt at [literate programming](https://en.wikipedia.org/wiki/Literate_programming) by using [Marginalia](https://github.com/gdeer81/marginalia) to generate a side-by-side code story based on the examples in the book and manually extracted bits of information.

## Dev Dependencies

#### Marginalia

You should have the marginalia uberjar built and residing in `/src/marginalia/target/marginalia-0.9.0-standalone.jar`.

I'm building mine from a personal fork, aimed to fix namespace sorting and tweak the output HTML for live editing.

#### Livereload

`npm install` should get you the livereload binary.

## Autodoc

`./bin/autodoc.py` will start a multi-threaded python daemon with the following components:
- A filesystem watcher targeting `src/bat/*.clj` which invokes the Marginalia jar whenever source changes are detected. The documentation is generated under `docs/uberdoc.html`
- A Livereload server running on the default `35729` port, watching for changes to the `docs/uberdoc.html` file
- A `SimpleHTTPServer` running on port `8000` serving `docs`

From this point on, simply open [http://localhost:8000/uberdoc.html](http://localhost:8000/uberdoc.html) in your browser where changes in the source files should be automagically reflected. 
