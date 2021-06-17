package space.nullpointerexception.libraary.DataUtils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.StringReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class BooksManager {

    private static BooksManager booksManager = null;
    /** arraylist to store all the books */
    private final ArrayList<Book> books = new ArrayList<>();
    /** arraylist to store the books which have been added to the shopping cart*/
    private final ArrayList<Book> addedBooks = new ArrayList<>();

    private BooksManager(){

    }

    /** @return the singleton instance of the BooksManager */
    public static BooksManager getInstance(){
        booksManager = (booksManager == null) ? new BooksManager() : booksManager ;
        return booksManager;
    }

    /** @param xmlString xml data (to decode) which contains all the books  */
    public void decode(String xmlString){
        try {
            books.clear();
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new InputSource(new StringReader(xmlString)));
            doc.getDocumentElement().normalize();

            NodeList cList = doc.getElementsByTagName("collection");

            for(int x = 0; x < cList.getLength(); x ++) {
                Node tList = cList.item(x);
                NodeList nList = ((Element) tList).getElementsByTagName("book");
                //NodeList nList = cList.item(x).getE doc.getElementsByTagName("book");

                for (int temp = 0; temp < nList.getLength(); temp++) {
                    Book tBook = new Book();
                    Node nNode = nList.item(temp);

                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element e = (Element) nNode;

                        String title = e.getElementsByTagName("title").item(0).getTextContent();
                        if (title.length() > 52) title = title.substring(0,52) + "...";

                        tBook.setDewhy(e.getAttribute("full_dewhy"));
                        tBook.setIsbn(e.getElementsByTagName("isbn").item(0).getTextContent());
                        tBook.setTitle(title);
                        tBook.setEan(e.getElementsByTagName("ean").item(0).getTextContent());
                        tBook.setPrice(Float.parseFloat(e.getElementsByTagName("price").item(0).getTextContent().trim()));
                        tBook.setPublisher(e.getElementsByTagName("publisher").item(0).getTextContent());
                        tBook.setAudienceAge(e.getElementsByTagName("audience_age").item(0).getTextContent());
                        tBook.setReleaseDate(e.getElementsByTagName("release_date").item(0).getTextContent());
                        tBook.setStudio(e.getElementsByTagName("studio").item(0).getTextContent());
                        tBook.setPages(Short.parseShort(e.getElementsByTagName("pages").item(0).getTextContent().trim()));
                        tBook.setWeight(Short.parseShort(e.getElementsByTagName("weight").item(0).getTextContent().trim()));
                        tBook.setBookCover(e.getElementsByTagName("book_cover").item(0).getTextContent());
                        tBook.setRating(Float.parseFloat(e.getElementsByTagName("rating").item(0).getTextContent().trim()));
                        tBook.setSummary(e.getElementsByTagName("summary").item(0).getTextContent());
                        tBook.setCountry(e.getElementsByTagName("country").item(0).getTextContent());
                        tBook.setLanguage(e.getElementsByTagName("language").item(0).getTextContent());


                        NodeList authors = ((Element) nNode).getElementsByTagName("author");
                        NodeList genres = ((Element) nNode).getElementsByTagName("genre");

                        String[] auths = new String[authors.getLength()];
                        String[] gens = new String[genres.getLength()];

                        for (int i = 0; i < authors.getLength(); i++) {
                            auths[i] = authors.item(i).getTextContent();
                        }


                        for (int i = 0; i < genres.getLength(); i++) {
                            gens[i] = genres.item(i).getTextContent();
                        }

                        tBook.setAuthors(auths);
                        tBook.setGenres(gens);
                        books.add(tBook);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getSize(){
        return books.size();
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    /**@return the reccomanded books*/
    public ArrayList<Book> getRecBooks() {
        ArrayList<Book> recBooks = new ArrayList<>();
        for(int x = 0; x < books.size(); x ++){
            if(books.get(x).getRating() >= 4){
                recBooks.add(books.get(x));
            }
        }
        return recBooks;
    }

    /**
     * @param query string that stores the value which we will be using to run queries on the list
     * @return an arraylist of books that have been queried*/
    public ArrayList<Book> search(String query){
        ArrayList<Book> temp = new ArrayList<>();
        for(Book t: books){
            if(t.toString().toLowerCase().contains(query.toLowerCase())) temp.add(t);
        }
        return temp;
    }

    /**@return the newest books*/
    public ArrayList<Book> getNewBooks() {
        ArrayList<Book> newBooks = new ArrayList<>();
        for(int x = 0; x < 5;){
            Book temp = books.get(new Random().nextInt(books.size()));
            if(newBooks.contains(temp)){

            }else{
                newBooks.add(temp);
                x ++;
            }
        }
        return newBooks;
    }

    public void addBookToShoppingCart(Book tBook){
        addedBooks.add(tBook);
    }

    public ArrayList<Book> getAddedBooks(){
        return  addedBooks;
    }

    /**save the status of the current shopping cart*/
    public void saveStatus(Context context){
        try {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor editor = preferences.edit();
            Gson gson = new Gson();
            String json = gson.toJson(addedBooks);
            editor.putString("cart", json);
            editor.apply();
        }catch (Exception ignored){}
    }

    /**read the status of the latest shopping cart*/
    public void readLatestStatus(Context context){
        try {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
            Gson gson = new Gson();
            String json = preferences.getString("cart", null);
            Type type = new TypeToken<ArrayList<Book>>() {
            }.getType();
            addedBooks.addAll(gson.fromJson(json, type));
        }catch (Exception ignored){
            addedBooks.addAll(new ArrayList<>());
        }
    }
}
