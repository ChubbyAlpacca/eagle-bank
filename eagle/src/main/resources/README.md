#Eagle Bank
A simple example of a banking application that uses OpenAPI specifications to generate models and controllers and liquibase
combined with H2 for simple persistance layer.

##Set Up Instructions

No environment variables or cmd line args are required to run the application.

**Install OAS models on community edition (Mac OS)**

brew install openapi-generator
openapi-generator generate -i openapi.yaml -g java -o ./generated
mvn clean install -DskipTests (from project root)
*if this fails to allow controllers to be implemented, try running `mvn clean install` from the `eagle` directory instead of the project root*
**Install OAS models on community edition (Linux)**
sudo apt install openapi-generator
openapi-generator generate -i openapi.yaml -g java -o ./generated
mvn clean install -DskipTests (from project root)
*if this fails to allow controllers to be implemented, try running `mvn clean install` from the `eagle` directory instead of the project root*
**Install OAS models on community edition (Windows)**
choco install openapi-generator
openapi-generator generate -i openapi.yaml -g java -o ./generated
mvn clean install -DskipTests (from project root)
*if this fails to allow controllers to be implemented, try running `mvn clean install` from the `eagle` directory instead of the project root*
