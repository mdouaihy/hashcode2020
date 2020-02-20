/**
 *  Copyright Murex S.A.S., 2003-2020. All Rights Reserved.
 *
 *  This software program is proprietary and confidential to Murex S.A.S and its affiliates ("Murex") and, without limiting the generality of the foregoing reservation of rights, shall not be accessed, used, reproduced or distributed without the
 *  express prior written consent of Murex and subject to the applicable Murex licensing terms. Any modification or removal of this copyright notice is expressly prohibited.
 */
package murex;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public final class BookingSystem {

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Instance fields
    //~ ----------------------------------------------------------------------------------------------------------------

    private final List<Book> books;
    private final List<Library> libraries;
    private final int days;
    private final long allScores;

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Constructors
    //~ ----------------------------------------------------------------------------------------------------------------

    public BookingSystem(List<Book> books, List<Library> libraries, int days, long allScores) {
        this.books = books;
        this.libraries = libraries;
        this.days = days;
        this.allScores = allScores;
    }

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Methods
    //~ ----------------------------------------------------------------------------------------------------------------

    public List<Book> getBooks() {
        return books;
    }

    public List<Library> getLibraries() {
        return libraries;
    }

    public int getDays() {
        return days;
    }

    public long getAllScores() {
        return allScores;
    }

    public void sortLibrary() {
        //libraries.sort((o1, o2) -> Long.compare(o2.getMaxInitialScore(), o1.getMaxInitialScore()));
        //libraries.sort(Comparator.comparingLong(Library::getSignupCost));

        //J-
        libraries.sort((o1, o2) -> Long.compare(
                o2.getMaxInitialScore() - (2 * o2.getDailyShipCapacity() * o2.getSignupCost()),
                o1.getMaxInitialScore() - (2 * o1.getDailyShipCapacity() * o1.getSignupCost())
        ));
        //J+
    }
}
