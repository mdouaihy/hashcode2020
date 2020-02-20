/**
 *  Copyright Murex S.A.S., 2003-2020. All Rights Reserved.
 *
 *  This software program is proprietary and confidential to Murex S.A.S and its affiliates ("Murex") and, without limiting the generality of the foregoing reservation of rights, shall not be accessed, used, reproduced or distributed without the
 *  express prior written consent of Murex and subject to the applicable Murex licensing terms. Any modification or removal of this copyright notice is expressly prohibited.
 */
package murex;

import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.List;

import it.unimi.dsi.fastutil.ints.IntArrayList;


public final class Plan {

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Instance fields
    //~ ----------------------------------------------------------------------------------------------------------------

    private List<LibraryPlan> libraries = new ArrayList<>();

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Methods
    //~ ----------------------------------------------------------------------------------------------------------------

    public LibraryPlan schedule(Library library, int day) {
        LibraryPlan libraryPlan = new LibraryPlan(day, library);
        libraries.add(libraryPlan);
        return libraryPlan;
    }

    public void print(PrintWriter out) {
        out.println(libraries.stream().filter(x -> !x.books.isEmpty()).count());
        for (LibraryPlan library : libraries) {
            if (!library.books.isEmpty()) {
                out.print(library.library.getId());
                out.print(" ");
                out.print(library.books.size());

                out.println();

                for (int book : library.books) {
                    out.print(book);
                    out.print(" ");
                }

                out.println();
            }
        }

        //out.flush();
    }

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Nested Classes
    //~ ----------------------------------------------------------------------------------------------------------------

    public static class LibraryPlan {
        private final int signupday;
        private final Library library;
        private IntArrayList books = new IntArrayList();

        public LibraryPlan(int signupday, Library library) {
            this.signupday = signupday;
            this.library = library;
        }

        public int getSignupday() {
            return signupday;
        }

        public Library getLibrary() {
            return library;
        }

        public int getBooksSize() {
            return books.size();
        }

        public void scheduleBooks(int maxDays) {
            int maxCapacity = (maxDays - signupday) * library.getDailyShipCapacity();
            int currentCapacity = 0;

            for (Book book : library.getBooks()) {
                if (currentCapacity >= maxCapacity) {
                    break;
                }
                if (!book.isScanned()) {
                    books.add(book.getId());
                    currentCapacity++;
                    book.setScanned(true);
                }

            }
        }
    }

}
