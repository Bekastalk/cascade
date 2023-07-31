package bekks;

import bekks.config.Config;
import bekks.entity.Author;
import bekks.entity.Book;
import bekks.entity.Publisher;
import bekks.entity.Reader;
import bekks.service.AuthorService;
import bekks.service.BookService;
import bekks.service.PublisherService;
import bekks.service.ReaderService;
import bekks.service.impl.AuthorServiceImpl;
import bekks.service.impl.BookServiceImpl;
import bekks.service.impl.PublisherServiceImpl;
import bekks.service.impl.ReaderServiceImpl;
import enums.Gender;
import enums.Genre;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Config.getEntityManager();
        PublisherService publisherService = new PublisherServiceImpl();
        AuthorService authorService = new AuthorServiceImpl();
        BookService bookService = new BookServiceImpl();
        ReaderService readerService = new ReaderServiceImpl();


        while (true) {
            System.out.println("""
                    Press to 1 save Publisher
                    Press to 2 get by id
                    Press to 3 get all Publisher
                    Press to 4 update
                    Press to 5 delete by name
                    Press to 6 save Author
                    Press to 7 update Author
                    Press to 8 find By id Author
                    Press to 9 getAuthorsByPublisherId
                    Press to 10 deleteAuthorById
                    Press to 11 assignAuthorToPublisher
                    Press to 12 save book
                    Press to 13 updateBookAuthor
                    Press to 14 getBookAndPublisherByBookId
                    Press to 15 delete book by author id
                    Press to 16 save Reader
                    """);
            switch (new Scanner(System.in).nextLine()) {
                case "1" -> {
                    Publisher publisher = new Publisher("Oshsky", "Osh");
                    publisherService.savePublisher(publisher);
                }
                case "2" -> {
                    System.out.println(publisherService.getPublisherById(1L));
                }
                case "3" -> {
                    publisherService.getAllPublishers().forEach(System.out::println);
                }
                case "4" -> {
                    publisherService.updatePublisher(2L, new Publisher("Sham", "Naryn"));
                }
                case "5" -> {
                    System.out.println("Write name: ");
                    publisherService.deletePublisherByName(new Scanner(System.in).nextLine());
                }
                case "6" -> {
                    Date date = Date.valueOf("1999-05-05");
                    Author author = new Author("Said", "Nursi", "said@gmail", date, "TY", Gender.Male);
                    authorService.saveAuthor(author);
                }
                case "7" -> {
                    Date date1 = Date.valueOf("1999-05-05");
                    authorService.updateAuthor(2L, new Author("Bekzat", "Abdibaliev", "bekzat@gmail", date1, "Turkey", Gender.Male));
                }
                case "8" -> {
                    System.out.println(authorService.getAuthorById(2L));
                }
                case "9" -> {
                    authorService.getAuthorsByPublisherId(1L).forEach(System.out::println);
                }
                case "10" -> {
                    authorService.deleteAuthorById(1L);
                }
                case "11" -> {
                    authorService.assignAuthorToPublisher(2L, 4L);
                }
                case "12" -> {
                    Book book = new Book("Risale-I-Nur", "Turkey", 1920, 100000, Genre.BIOGRAPHY);
                    bookService.saveBook(3L,book);
                }
                case "13"->{
                    bookService.updateBookAuthor(2L, 3L);
                }
                case "14"->{
                    System.out.println(bookService.getBookAndPublisherByBookId(1L));
                }
                case "15"->{
                    bookService.deleteBookByAuthorId(3L);
                }
                case "16"->{
                    Reader reader=new Reader("Daniel", 20,"donidat@gmail.com");
                    readerService.saveReader(reader);
                }
                case "17"->{
                    readerService.updateReader(1L, "Datka", 21, "datka@gmail.com");
                }
                case "18"->{
                    System.out.println(readerService.getReaderByBookId(2L));
                }
                case "19"->{
                    readerService.deleteReaderById(1L);
                }
                case "20"->{
                    readerService.getReadersByAuthorId(2L).forEach(System.out::println);
                }
            }
        }
    }
}


/*3.1) Publisher кошуу учун SavePublisher()
деген метод тузуп, сакталган Publisher ди кайтаруу керек.

3.2) Издательстводо getPublisherById(),
getAllPublishers()(аты боюнча сорттоп чыгаруу),
updatePublisher(),
deletePublisherByName() (издательствону очургондо,
 ага тиешелуу китептер жана авторлор  очпошу керек),
 методдорун тузуп, ишке ашыруу.*/

/*4.1) Автордо saveAuthor(),
updateAuthor(),
getAuthorById(),
getAuthorsByPublisherId()(тиешелуу издательствонун авторлорун чыгарып беруу),
deleteAuthorById()(автор очкондо, авторго тиешелуу издательство очпошу керек, китептер очуш керек),
assignAuthorToPublisher()(авторду издательствого кошуп коюу(назначить)).*/

/*5.1) Book да saveBook()(Book сакталып жатканда кандайдыр бир авторго тиешелуу болуп сакталуусу керек),
 updateBookAuthor(),
  getBookAndPublisherByBookId()(Бир Id ге тиешелуу book тун маалыматтары жана
  ага тиешелуу издательствосу чыксын),
   deleteBookByAuthorId();*/

/*saveReader(),
updateReader(),
getReaderByBookId(),
deleteReaderById(),
getReadersByAuthorId();*/