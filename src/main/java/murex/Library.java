/**
 *  Copyright Murex S.A.S., 2003-2020. All Rights Reserved.
 *
 *  This software program is proprietary and confidential to Murex S.A.S and its affiliates ("Murex") and, without limiting the generality of the foregoing reservation of rights, shall not be accessed, used, reproduced or distributed without the
 *  express prior written consent of Murex and subject to the applicable Murex licensing terms. Any modification or removal of this copyright notice is expressly prohibited.
 */
package murex;

import java.util.ArrayList;
import java.util.List;


public final class Library {

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Instance fields
    //~ ----------------------------------------------------------------------------------------------------------------

    private final int id;
    private final int signupCost;
    private final int booksCount;
    private final int dailyShipCapacity;
    private final List<Book> books;

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Constructors
    //~ ----------------------------------------------------------------------------------------------------------------

    public Library(int id, int signupCost, int booksCount, int dailyShipCapacity) {
        this.id = id;
        this.signupCost = signupCost;
        this.booksCount = booksCount;
        this.dailyShipCapacity = dailyShipCapacity;
        this.books = new ArrayList<>(booksCount);
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

    public List<Book> getBooks() {
        return books;
    }

    void addBook(Book book) {
        books.add(book);
    }
}
