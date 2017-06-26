**JCS basic cache POC**
Description:

This is a simple POC of using apache JCS cache. This is a maven project 

Dependency:

commmons-jcs-core-2.1.jar

Configuration:
JCS Cache configuraion is in main\resources\cache.ccf and currently to set to basic in memory cache. The parameters can be tweaked when needed. 


Build Instructions:

1] use command mvn clean package to build war file
2] deploy the war file in server JBoss, Glassfish.
3] Use the URL - http://<server>:<port>/poc/jcscache/param?param-name=<paramname>
4] After the first call the same UUID is returned for a given param.
5] Logs show the time taken by the api each time. 


Sample log:

JCSCachePOC was successfully deployed in 3,953 milliseconds.
Info:   Time taken = 13 ms
Info:   Time taken = 3 ms
Info:   Time taken = 0 ms
Info:   Time taken = 0 ms
Info:   Time taken = 0 ms
Info:   Time taken = 0 ms
Info:   Time taken = 1 ms
Info:   Time taken = 0 ms