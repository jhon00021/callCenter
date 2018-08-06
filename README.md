Project Title

Almundo callCenter proyect

This app can dispatch call center calls, it contains 3 kind of employees (operators, supervisors and directors), this app uses a queue that is order by kind of employee, first all operators  attend calls, if there is not any operator available and there is another call, the supervisor will take it, the same happen when there are not available supervisor, the call will be take by a director.

When an employee finishes a call, it will be able to attened another call.
if all the employees are busy the call will be waiting until an employee can attent it.

Each call can last between 5 and 10 minuts pir call.

 you can configure using the file application.poperties you can set the max number of operators, supervisors and directosr, how many call will be attened at the same time and the maximin number of the calls queue. and the calls durations.

Getting Started

To run this proyect, you have to install maven and java 8.
you have to download this proyect with git, after you have to install dependencies with maven using mvn clean install, and you can run the project with the command mvn spring-boot:run or you can generate the jar file with mvn clean install and open the target folder and excecute it.

Built With
Maven

Author
Jhon Velasquez.
