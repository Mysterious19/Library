# Library

## How to run the project : 

```
cd Library
mkdir classes
javac -d classes entities/*.java interfaces/*.java  mappers/*.java *.java
java -classpath ":sqlite-jdbc-3.27.2.1.jar:./classes" library/Init
```
## Features Implemented

- The database class uses singleton design pattern for database access.
- Interfaces are used to abstract the implementation of the classes from the user.
- Mappers are used to transfer class instance into query. It maps the Java class objects to database table records.
- CRUD operations are implemented with taking into consideration scenarios like
 >admin can not delete users (books) from records, if they have (are) already issued.
- Searching a book by name suggests others books matching the prefix name, and also show if the book is available or not. If not, when will it be available. 
- users can issue book for certain period according to their groups (student, staff, faculty). Also the number of books they can issue at a time vary with thier groups.
- user can see the books he has issued and with their due dates. During returning of the book, if applicable, dues are calculated. 
- Job executor for removing unused books over a period if time is also implemented.

![Relational Schema](./images/relationalSchema.png?raw=true "Relational Schema of database")
