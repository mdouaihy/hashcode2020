/**
 *  Copyright Murex S.A.S., 2003-2020. All Rights Reserved.
 *
 *  This software program is proprietary and confidential to Murex S.A.S and its affiliates ("Murex") and, without limiting the generality of the foregoing reservation of rights, shall not be accessed, used, reproduced or distributed without the
 *  express prior written consent of Murex and subject to the applicable Murex licensing terms. Any modification or removal of this copyright notice is expressly prohibited.
 */
package murex;

import java.util.Arrays;
import java.util.Comparator;


public final class Library {

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Instance fields
    //~ ----------------------------------------------------------------------------------------------------------------

    private final int id;
    private final int signupCost;
    private final int booksCount;
    private final int dailyShipCapacity;
    private final Book[] books;
    private long booksScore;
    private int booksIndex;

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Constructors
    //~ ----------------------------------------------------------------------------------------------------------------

    public Library(int id, int signupCost, int booksCount, int dailyShipCapacity) {
        this.id = id;
        this.signupCost = signupCost;
        this.booksCount = booksCount;
        this.dailyShipCapacity = dailyShipCapacity;
        this.books = new Book[booksCount];
        this.booksScore = 0L;
        this.booksIndex = 0;
    }

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Methods
    //~ ----------------------------------------------------------------------------------------------------------------

    @Override
    public String toString() {
        return "Book{" + id + ", signupCost=" + signupCost + ", booksCount=" + booksCount + ", dailyShipCapacity=" + dailyShipCapacity + '}';
    }

    public int getId() {
        return id;
    }

    public int getSignupCost() {
        return signupCost;
    }

    public int getBooksCount() {
        return booksCount;
    }

    public int getDailyShipCapacity() {
        return dailyShipCapacity;
    }

    public Book[] getBooks() {
        return books;
    }

    public long getBooksScore() {
        return booksScore;
    }

    void addBook(Book book) {
        booksScore += book.getScore();
        books[booksIndex] = book;
        booksIndex++;
    }

    void sortBooks() {
        Arrays.sort(books, (o1, o2) -> Integer.compare(o2.getScore(), o1.getScore()));
    }
}
