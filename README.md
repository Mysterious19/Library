# Library

## How to run the project : 

```
javac -d ./classes entities/*.java mappers/*.java *.java
java -classpath ":sqlite-jdbc-3.27.2.1.jar:./classes" library/Init
```
### Features Implemented

- *The basic operations of creation / deletion of users and entry / removal of books have been implemented.*
- *Quantities of each book is maintained.*
- *Searching a book. The system also suggests the books in the database matching that prefix, and also show if the book is available or not. If not, when will it be available.* 
- *Query for books that has not been used from the last some time (specified in the query)*
- *Issuance and returning of the books by the system admin. Availability of books is taken into consideration before issuing or removing any records.*
