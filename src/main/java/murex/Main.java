/**
 *  Copyright Murex S.A.S., 2003-2020. All Rights Reserved.
 *
 *  This software program is proprietary and confidential to Murex S.A.S and its affiliates ("Murex") and, without limiting the generality of the foregoing reservation of rights, shall not be accessed, used, reproduced or distributed without the
 *  express prior written consent of Murex and subject to the applicable Murex licensing terms. Any modification or removal of this copyright notice is expressly prohibited.
 */
package murex;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import java.net.URL;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


public class Main {

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Methods
    //~ ----------------------------------------------------------------------------------------------------------------

    public static void main(String[] args) throws IOException {

        //List<String> sources = Arrays.asList("a_example", "b_read_on", "c_incunabula", "d_tough_choices", "e_so_many_books", "f_libraries_of_the_world");
        List<String> sources = Arrays.asList("f_libraries_of_the_world");

        for (String dataSource : sources) {
            URL input = Main.class.getClassLoader().getResource("" + dataSource + ".txt");
            assert input != null;
            File file = new File(input.getFile());
            BookingSystem bookingSystem = Parser.parse(file);

            bookingSystem.sortLibrary();
            System.out.println(bookingSystem);

            Plan plan = new Plan();
            int availableSchedule = 0;

            List<Library> libraries = bookingSystem.getLibraries();
            int libCount = libraries.size();

            for (int day = 0; day < bookingSystem.getDays(); day++) {

            }

            for (int libraryIndex = 0; libraryIndex < libCount; libraryIndex++) {
                Library libToSched = libraries.get(0);
                Plan.LibraryPlan libPlan = plan.schedule(libToSched, availableSchedule);
                libPlan.scheduleBooks(bookingSystem.getDays());

                if (libPlan.getBooksSize() > 0) {
                    availableSchedule += libToSched.getSignupCost();

                    libraries = libraries.subList(1, libraries.size());
                    if (libraries.isEmpty()) {
                        break;
                    }
                    for (Library library : libraries) {
                        library.computeCurrentScore(bookingSystem.getDays() - availableSchedule);
                    }

                    //libraries.sort((o1, o2) -> Long.compare(o2.getMaxInitialScore(), o1.getMaxInitialScore()));

                    //J-
//                    libraries.sort((o1, o2) -> Long.compare(
//                            (o2.getBooksCount() ) / o2.getDailyShipCapacity(),
//                            (o1.getBooksCount() ) / o1.getDailyShipCapacity()));
                    //J+

                    libraries.sort(Comparator.comparingLong(Library::getSignupCost));

                    //J-
//                    libraries.sort((o1, o2) -> Long.compare(
//                            o2.getMaxInitialScore() - (2 * o2.getDailyShipCapacity() * o2.getSignupCost()),
//                            o1.getMaxInitialScore() - (2 * o1.getDailyShipCapacity() * o1.getSignupCost())
//                    ));
                    //J+

                } else {
                    libraries.remove(libToSched);
                }

            }
//
//            for (Library library : bookingSystem.getLibraries()) {
//                Plan.LibraryPlan libPlan = plan.schedule(library, availableSchedule);
//
//                libPlan.scheduleBooks(bookingSystem.getDays());
//
//                if (libPlan.getBooksSize() > 0) {
//                    availableSchedule += library.getSignupCost();
//                }
//            }

            //plan.print(new PrintWriter(System.out));

            PrintWriter out = new PrintWriter(dataSource + ".out");
            plan.print(out);
            out.flush();
            out.close();
        }

    }
}
