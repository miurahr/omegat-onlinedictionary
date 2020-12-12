# OmegaT plugin for Online dictionary search

This is experimental implementation of online dictionary search plugin for OmegaT.
Current status is `ALPHA`.

- OmegaWiki
- Oxford dictionaries API

When using the plugin, you should put config yaml file, which name should end with service.yml,
in `dictionary` directory of OmegaT project.

For OmegaWiki search:

```
---
name: OmegaWiki
endpointUrl: http://omegawiki.org/api.php
driver: omegawiki
key:
secret:
```

For Oxford Dictionaries API search:

```
---
name: Oxford Dictionaries
endpointUrl: https://od-api.oxforddictionaries.com/api/v2/
driver: oxfordapi
key: "aa5cff0a"
secret: "a8ab0ffXXX8886XX999303f2962e965"
```

You should prepare  app_id and app_key from [Oxford dictionaries developer site](https://developer.oxforddictionaries.com/).
To get translated words, you may need to select DEVELOPER plan on the registration. 
Otherwise, you only get definitions of word.
