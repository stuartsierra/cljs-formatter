cljs-formatter
========================================

A ClojureScript pretty-printer that utilizes the DOM.

**Not under active development**


Sample App
----------------------------------------

Built using [ClojureScript One](http://clojurescriptone.com/).

To run the sample application, you will need: 

* [Leiningen](https://github.com/technomancy/leiningen) version **2.0.0** or higher

Run:

   cd sample-app
   lein repl

Once at the REPL, type:

   (go)


Usage
----------------------------------------

    (ns example
      (:require [cljs-formatter :as format]))

cljs-formatter works in two step.

**Step One:** Render ClojureScript data structures as an HTML string:

    (def html (format/html {:a 1 :b [1 2 3]}))

Take that string and add it to the DOM somewhere. Get the DOM element.

**Step Two:** Style the elements based on sizing information extracted
from the DOM:

    (format/arrange! element container)

The `element` is the DOM node created from the HTML in Step One. The
`container` is some other DOM element that is a parent or ancestor of
`element`. The `container` sets the maximum horizontal width of the
rendered data, and will be used to decide where to place line breaks.
