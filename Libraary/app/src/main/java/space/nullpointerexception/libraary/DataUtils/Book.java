package space.nullpointerexception.libraary.DataUtils;

import java.io.Serializable;
import java.util.Arrays;

public class Book implements Serializable {

    private String isbn;
    private String title;
    private String ean;
    private float price;
    private String [] authors;
    private String publisher;
    private String audienceAge;
    private String releaseDate;
    private String studio;
    private short pages; //32k should be enough ;)
    private short weight;
    //todo change rating type from short to float
    private float rating;
    private String [] genres;
    private String summary;
    private String bookCover;
    private String language;
    private String country;
    private String dewhy;

    //todo remove publisher and label

    public Book(){

    }

    public void setDewhy(String dewhy) {
        this.dewhy = dewhy;
    }

    public String getDewhy() {
        return dewhy;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public String getLanguage() {
        return language;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String [] getAuthors() {
        return authors;
    }

    public void setAuthors(String[] authors) {
        this.authors = authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getAudienceAge() {
        return audienceAge;
    }

    public void setAudienceAge(String audienceAge) {
        this.audienceAge = audienceAge;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public short getPages() {
        return pages;
    }

    public void setPages(short pages) {
        this.pages = pages;
    }

    public short getWeight() {
        return weight;
    }

    public void setWeight(short weight) {
        this.weight = weight;
    }

    public float getRating() {
        return rating;
    }

    public String[] getGenres() {
        return genres;
    }

    public void setGenres(String[] genres) {
        this.genres = genres;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getBookCover() {
        return bookCover;
    }

    public void setBookCover(String base64Image){
        this.bookCover = base64Image;
    }

    @Override
    public String toString() {
        return isbn + title + ean + Arrays.toString(authors) + publisher + Arrays.toString(genres);
    }
}
