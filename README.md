# Detecting duplicate files in a given path

In this homework, you will recursively traverse all the regular files in a given path.

Instead of comparing two files byte by byte, we are comparing with their checksum values. 


Our program will sort the duplicate lists by the number of files in each list.
For example, if four duplicates of a file is detected, then this four files will be printed before a duplicate list of size two.

* java -jar target/HW1-1.0.jar /Users/iorixxx/Desktop/solr-6.4.1

and the program will print out something like:

```
...
licenses/httpclient-NOTICE.txt licenses/httpcore-NOTICE.txt licenses/httpmime-NOTICE.txt  
...
server/solr-webapp/webapp/js/lib/highlight.js server/solr-webapp/webapp/libs/highlight.js  
... 
server/solr-webapp/webapp/WEB-INF/lib/httpmime-4.4.1.jar dist/solrj-lib/httpmime-4.4.1.jar 
...
docs/images/solr.svg server/solr-webapp/webapp/img/solr.svg  
...
```
