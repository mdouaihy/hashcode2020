/**
 *  Copyright Murex S.A.S., 2003-2020. All Rights Reserved.
 *
 *  This software program is proprietary and confidential to Murex S.A.S and its affiliates ("Murex") and, without limiting the generality of the foregoing reservation of rights, shall not be accessed, used, reproduced or distributed without the
 *  express prior written consent of Murex and subject to the applicable Murex licensing terms. Any modification or removal of this copyright notice is expressly prohibited.
 */
package murex;

import java.io.File;
import java.io.IOException;

import java.nio.file.Files;

import java.util.ArrayList;
import java.util.List;


final class Parser {

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Methods
    //~ ----------------------------------------------------------------------------------------------------------------

    static BookingSystem parse(File file) throws IOException {

        System.out.println("Reading Data Started");

        List<String> allLines = Files.readAllLines(file.toPath());

        //Parse first line
        String[] lineTokens = allLines.get(0).split(" ");
        int booksCount = Integer.parseInt(lineTokens[0]);
        int libraryCount = Integer.parseInt(lineTokens[1]);
        int dayCount = Integer.parseInt(lineTokens[2]);

        // Read Book Scores
        lineTokens = allLines.get(1).split(" ");
        List<Book> books = new ArrayList<>(booksCount);
        long allScores = 0L;
        for (int bookIndex = 0; bookIndex < booksCount; bookIndex++) {
            int bookScore = Integer.parseInt(lineTokens[bookIndex]);
            allScores += bookScore;
            books.add(new Book(bookIndex, bookScore));
        }

        List<Library> libraries = new ArrayList<>(libraryCount);
        int currentLine = 2;
        for (int libraryIndex = 0; libraryIndex < libraryCount; libraryIndex++) {

            // Read Library Info
            lineTokens = allLines.get(currentLine).split(" ");
            int libraryBooksCount = Integer.parseInt(lineTokens[0]);
            int signupCost = Integer.parseInt(lineTokens[1]);
            int dailyCapacity = Integer.parseInt(lineTokens[2]);

            Library library = new Library(libraryIndex, signupCost, libraryBooksCount, dailyCapacity);

            // Read Library Books
            lineTokens = allLines.get(currentLine + 1).split(" ");

            for (int libBookIndex = 0; libBookIndex < libraryBooksCount; libBookIndex++) {
                int libBookId = Integer.parseInt(lineTokens[libBookIndex]);
                Book book = books.get(libBookId);
                library.addBook(book);
            }

            // Sort Library Books by their Scores
            library.sortBooks();
            library.computeCurrentScore(dayCount);

            libraries.add(library);

            currentLine += 2;
        }

        System.out.println("Reading Photos Ended");

        return new BookingSystem(books, libraries, dayCount, allScores);
    }
}
