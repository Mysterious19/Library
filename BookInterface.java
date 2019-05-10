public interface BookInterface {
    public Book create(Book book);

    public Boolean delete(Integer id);

    public List<Book> search(String name);
}