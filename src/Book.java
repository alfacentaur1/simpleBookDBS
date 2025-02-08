public class Book {
    private String title;
    private String author;
    private String publisher;
    private int pages;
    private int rating;

    public Book(String title, String author,String publisher, int pages, int rating){
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.pages = pages;
        this.rating = rating;
    }

    public String getTitle(){
        return this.title;

    }
    public String getAuthor(){
        return this.author;
    }

    public String getPublisher(){
        return this.publisher;
    }

    public int getPages(){
        return this.pages;
    }

    public int getRating(){
        return this.rating;
    }

    public void setTitle(String title){
        this.title = title;
    }
    public void setAuthor(String author){
        this.author = author;
    }

    public void setPublisher(String publisher){
        this.publisher = publisher;
    }
    public void setPages(int pages){
        this.pages = pages;
    }
    public void setRating(int rating){
        this.rating = rating;
    }

}
