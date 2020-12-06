# OmegaT plugin for Online dictionary search

This is experimental implementation of online dictionary search plugin for OmegaT.
Current status is `EARLY ALPHA`.

- OmegaWiki: only get a definition.
- Oxford dictionary API: under development.

When using the plugin, you should put snippet yaml file(service.yml) in the OmegaT project
folder's dictionary directory.

For OmegaWiki search:

```
---
name: omegawiki
endpointUrl: http://omegawiki.org/api.php
driver: omegawiki
key:
secret:
```

For Oxford Dictionary API search:

```
---
name: oxford dictionary api
endpointUrl: https://od-api.oxforddictionaries.com/api/v2/
driver: oxford
key: "aa5cff0a"
secret: "a8ab0ffXXX8886XX999303f2962e965"
```

You should prepare  product_id and key_id from Oxford dictionary site with contract.