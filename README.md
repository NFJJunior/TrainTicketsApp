# TrainTicketsApp
This is a Java project developed for the Advanced Object Programming class. The project aims to simulate an app for purchasing train tickets, similar to cfrcalatori, and implements various features related to train ticket management.
## Description
The project consists of a train ticket platform that allows users to perform different operations related to train tickets. The system is designed using several classes to represent different entities and functionalities.


### System definition
The system is composed of the following classes:

* Car (abstract class)
  + DaylightCar
  + SleepingCar
* Train (abstract class)
  + InterRegio
  + InterCity
* Ticket
* Route (with a package-private class RouteEdge)

The implemented features are:
1. Create Car/Train/Route: Users can create instances of cars, trains, and routes.
2. Show Car/Train/Route: Users can view information about specific cars, trains, and routes.
3. Add car to a train: Cars can be added to existing trains.
4. Add route to a train: Routes can be assigned to existing trains.
5. Verify available seats for a train: Users can check the availability of seats on a specific train.
6. Show details about train prettified: Detailed information about a train is displayed in a user-friendly format.
7. Calculate the price of a ticket: The system calculates the price of a train ticket based on various factors.
8. Buy a ticket: Users can purchase tickets for a specific train.
9. Remove sleeping car: Sleeping cars from trains that operate during daylight are automatically removed.
10. Delete Car/Train/Route: Users can delete cars, trains, and routes from the system.

### Implementation details
* The Train class utilizes a sorted collection (TreeMap) to store all the available types of seats.
* The Car and Train classes are organized using inheritance to create a hierarchy.
* The operations and functionalities of the system are implemented through static methods in the AppService class.
* The menu functionality is implemented in the Main class.
* An Audit service is implemented using a static class named AuditService, which includes a single static method for auditing purposes.

