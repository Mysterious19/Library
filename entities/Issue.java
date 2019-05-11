package library.entities;

public class Issue {
    private Integer userId;
    private Integer bookId;
    private String issueDate;
    private String dueDate;

    // ------getters----------
    public Integer getUserId() {
        return userId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    // ------setters---------

    public void setUserId(Integer id) {
        this.userId = id;
    }

    public void setBookId(Integer id) {
        this.bookId = id;
    }

    public void setissueDate(String date) {
        this.issueDate = date;
    }

    public void setDueDate(String date) {
        this.dueDate = date;
    }

    public String toString() {
        return "UserID : "+ userId + "bookID : " + bookId + "issue date : " + issueDate + "due date : " + dueDate;
     }
}