## Problem: Application for cinema worker
To create a console application that will allow an employee
of a cinema (with a single auditorium) to manage data about films
at the box office and the schedule of their screenings, sell,
refund tickets and mark session visitors.

## Functional requirements for the application
1. The possibility of fixing the sale of tickets to cinema visitors for a session with a choice of seats.
2. Realization of the possibility of returning sold tickets to visitors before the start of the session.
3. The ability to display available and sold seats for the selected session.
4. The ability to edit data about films that are in the cinema box office and the schedule of their screenings.
5. The ability to mark occupied seats in the hall by visitors of a particular session.
6. Implementation of a simple user registration functionality with mandatory password encryption and authorization of users (employees) at the entrance.

## Instructions for the user
Iterations in all menus are performed by entering the numbers of the necessary commands. In general, the entire application supports incorrect input (empty lines, incorrect input formats)

When you launch the application, the registration menu will appear. You need an account to register. It can be pre-created. To create it, you will need to come up with a username and password

After successful authorization, a menu for interaction will appear. The 4 and 5 keys open the internal menus for interacting, respectively, with films that are in the rental and sessions in the database. Each of the menus allows you to add the corresponding entity to the database, remove the entity from the database, or display all currently existing entities. Pressing 4 inside these menus will return you to the main menu

The main menu implements the main functionality in accordance with the functional requirements. The 1 key will allow you to sell a ticket for a certain session. To do this, you need to know the session ID, you can first view it in the sessions menu (press 5). After selecting a session, you will be asked to choose a place (of course, the layout of the hall is pre-displayed). If it is not occupied, the purchase will be fixed

The 2 key will allow you to make a refund - mark the sold place as free (of course, if the session has not started yet). The data requested by the program is similar to the ticket sale. Using the 3 key, you can get a diagram of a specific session (the session is accessed in the same way by ID). On the 6 key, you can mark the sold place in a particular session as occupied (the visitor has already arrived)

![Cinema classes](https://github.com/vadiikkk/Application-for-cinema-worker/assets/132217692/23c6d0d6-38fc-471b-ba5f-c4da25c02a4b)
