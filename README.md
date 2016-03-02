# luminus-immutant

Immutant HTTP adapter for Luminus

## Usage

The library provides three function for managing the server lifecycle.


The server can be started using the `start` function that accepts a Ring
handler and a map of options described in the [official documentation](http://immutant.org/documentation/current/apidoc/immutant.web.html).

```clojure
(require '[luminus.http-server :as http])

(def server (http/start handler {:port 3000}))
```

The server can have multiple handlers, these are appended using the `wrap-handler`
function:

```clojure
(-> server
    (http/wrap-handler another-handler {:path "/foo"})
    (http/wrap-handler yet-another-handler {:path "/bar"}))
```

The server is stopped using the `stop` function:

```clojure
(http/stop server)
```



## License

Copyright © 2016 Dmitri Sotnikov

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
