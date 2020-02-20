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
import java.util.List;


public class Main {

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Methods
    //~ ----------------------------------------------------------------------------------------------------------------

    public static void main(String[] args) throws IOException {

        List<String> sources = Arrays.asList("a_example", "b_read_on", "c_incunabula", "d_tough_choices", "e_so_many_books", "f_libraries_of_the_world");

        for (String dataSource : sources) {
            URL input = Main.class.getClassLoader().getResource("" + dataSource + ".txt");
            assert input != null;
            File file = new File(input.getFile());
            BookingSystem bookingSystem = Parser.parse(file);

            bookingSystem.sortLibrary();
            System.out.println(bookingSystem);

            Plan plan = new Plan();
            int availableSchedule = 0;
            for (Library library : bookingSystem.getLibraries()) {
                Plan.LibraryPlan libPlan = plan.schedule(library, availableSchedule);

                libPlan.scheduleBooks(bookingSystem.getDays());

                if (libPlan.getBooksSize() > 0) {
                    availableSchedule += library.getSignupCost();
                }
            }

            //plan.print(new PrintWriter(System.out));

            PrintWriter out = new PrintWriter(dataSource + ".out");
            plan.print(out);
            out.flush();
            out.close();
        }

    }
}
