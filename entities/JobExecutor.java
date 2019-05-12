package library.entities;

import java.util.*;
import library.mappers.BookMap;

/*
Description : a generic job executor class to perform query and corrsponding actions.
Here only one query and action of removing unused books is performed. 
*/

public class JobExecutor {

    //method to map query with the action
    public Integer executeJob(String query, Object values[]) {
        Integer res = 0;

        if (query == "RemoveUnused") {
            res = ActionRemoveUnused(values);
        }

        return res;
    }

    //action performed
    public Integer ActionRemoveUnused(Object values[]) {
        BookMap bookMapper = new BookMap();
        List<Book> books = bookMapper.unusedBooksOperation(values);

        if (books.isEmpty()) {
            return 0;
        }

        Integer res = 0;

        for (Book book : books) {
            res = bookMapper.delete(book.getId());
        }

        return res;
    }
}