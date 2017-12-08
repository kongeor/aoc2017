## day 2

### Part 1

Let's start with the `ns`:

```clj
(ns aoc2017.day2)
```


The first thing we need to do is to read our input. We will need the io tools for that,
so let's `require` the necessary stuff:

```clj
(ns aoc2017.day2
  (:require [clojure.java.io :as io]))
```

Try the following in the repl:

```clj
(io/resource "day2")
=> #object[java.net.URL 0x109d264 "file:/C:/temp/clj/aoc2017/resources/day2.txt"]

```

If you getting `nil`, then your path is not correct. Let's `slurp` the data:

```clj
(slurp (io/resource "day2.txt"))
```

You should get all the data in the file. Notice that the numbers are separated using a tab character.

We will need to do some element splitting. So let's require the `clojure.string` for that:

```clj
(ns aoc2017.day2
  (:require [clojure.java.io :as io]
            [clojure.string :as string]))
```

Re-evaluate your namespace declaration to bring in the `string`. Not we can split the lines:

```clj
(string/split (slurp (io/resource "day2.txt")) #"\n")
```

Let's spit the elements of each sequence:

```clj
(map #(string/split % #"\t") (string/split (slurp (io/resource "day2.txt")) #"\n"))
```

Now we need to convert our strings to ints. Let's create a helper function for that:

```clj
(defn str->int [s]
  (Integer. s))
```

Verify in the REPL that it does the right thing:

```clj
(str->int "5")
=> 5
```

Having our helper we need to `map` all the elements in each sequence, which is something like a double map:

```clj
(map #(map str->int %) (map #(string/split % #"\t") (string/split (slurp (io/resource "day2.txt")) #"\n")))
```

Now we need to find the max and min elements for each sequence. This should be easy to do using the built-in functions:

```clj
(map (fn [seq] [(apply max seq) (apply min seq)]) (map #(map str->int %) (map #(string/split % #"\t") (string/split (slurp (io/resource "day2.txt")) #"\n"))))
```

Returning a tuple of max/min, allows us finding the difference easily:

```clj
(map (fn [[ma mi]] (- ma mi)) (map (fn [seq] [(apply max seq) (apply min seq)]) (map #(map str->int %) (map #(string/split % #"\t") (string/split (slurp (io/resource "day2.txt")) #"\n")))))
```

TODO descructing/shadowing.

And the grand crescendo is to sum things up:

```clj
(reduce + (map (fn [[max min]] (- max min)) (map (fn [seq] [(apply max seq) (apply min seq)]) (map #(map str->int %) (map #(string/split % #"\t") (string/split (slurp (io/resource "day2.txt")) #"\n"))))))
```

I know what you are thinking:

    Omg !@#%#$&^&%**{}$% ))))))
    
And yes ... thing is a very long line. But we did it interactively and it's very easy to rewrite this code using the thread last macro:

```clj
(->> (string/split (slurp (io/resource "day2.txt")) #"\n")
     (map #(string/split % #"\t"))
     (map #(map str->int %))
     (map (fn [seq] [(apply max seq) (apply min seq)]))
     (map (fn [[max min]] (- max min)))
     (reduce +))
```

You can wrap this code in a `defn`, so you can call it on demand, or in a `comment` block so it's not evaluated if you
evaluate the entire file.

Let's proceed to ...

### Part 2

As usually the second part is more challenging. For each row we need to find even divisors. Let's start with a draft:

```clj
(defn even-divisors [seq]
  (for [x seq
        y seq]
    (when (and (not= x y) (= (mod x y) 0))
      (/ x y))))
```

That is, for each combination of numbers which are not equal and their modulo is 0, return the result of the division.

Let's try that out:

```clj
(even-divisors [5 9 2 8])
=> (nil nil nil nil nil nil nil nil nil nil nil nil nil nil 4 nil)
```

The use of `for` here will produce all the possible pairs. It's like writing a double for loop in java:

TODO

TODO show macro expansion

To make sure that this is what you need you can macroexpand the `for`. Just kidding, don't macroexpand the for.

You did macroexpand the for, didn't you?

What I like about these problems is that there are no edge cases so you can get around with very simple approaches.

Let's filter out the `nil`s and get the first element and ahm ... rename the function maybe?

```clj
(defn even-div [seq]
  (->>
    (for [x seq
          y seq]
      (when (and (not= x y) (= (mod x y) 0))
        (/ x y)))
    (remove nil?)
    first))
```

Don't have a much better name for now. Evaluate and give it a go:

```clj
(even-div [5 9 2 8])
=> 4
```

This seems working. Now with some copy-pasta magic from the first part, we can find the result:

```clj
(->> (string/split (slurp (io/resource "day2.txt")) #"\n")
     (map #(string/split % #"\t"))
     (map #(map str->int %))
     (map even-div)
     (reduce +))
```

which is correct btw! :D




