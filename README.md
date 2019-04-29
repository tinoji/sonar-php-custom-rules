sonar-php-forbidden-string-rules
================================
PHP Custom Rules to detect forbidden word (for example, hard-coded password).

Build
------
`mvn clean package`

Or you can use files in releases.

Install
--------
Place `jar` file in `SONARQUBE_HOME/extensions/plugins` directory.

Usage
------
Create `forbidden.txt` and place it in `sonar-scanner` working directory.

`forbidden.txt` is newline separated list like following.

```
forbidden_string_1
forbidden_string_2
...
```

License
--------
Licensed under the [GNU Lesser General Public License, Version 3.0](http://www.gnu.org/licenses/lgpl.txt)
