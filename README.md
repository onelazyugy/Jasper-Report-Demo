# Jasper Report Demo with Spring Boot

## REST API
- http://localhost:9001/api/demo/somedemotext (will generate and put the pdf file onto your local directory, modify directory from JasperReportController.java)

- http://localhost:9001/api/report/VIETLEISAWESOME (open this from the browser and you will see the pdf)

## HOW TO RUN THE APP
- $ gradle --refresh-dependencies (if you have gradle install globally)
- $ ./gradlew --refresh-dependencies (if you use the gradle wrapper that comes with the app)
- $ ./gradlew build (this will produce a jar file under build/libs)
