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

import java.util.List;
import java.util.Map;


public class Main {

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Methods
    //~ ----------------------------------------------------------------------------------------------------------------

    public static void main(String[] args) throws IOException {

        //String dataSource = "a_example";
        //String dataSource = "d_pet_pictures";
        //String dataSource = "e_shiny_selfies";
        //String dataSource = "c_memorable_moments";
        String dataSource = "a_example";

        URL input = Main.class.getClassLoader().getResource("" + dataSource + ".txt");
        File file = new File(input.getFile());
        BookingSystem bookingSystem = Parser.parse(file);

        System.out.println(bookingSystem);

/*
 *
 *    URL input = Main.class.getResource("/" + dataSource + ".txt");
 *    //URL input = Main.class.getResource("/b_lovely_landscapes.txt");
 *    File file = new File(input.getFile());
 *    List<Photo> photos = Parser.parse(file);
 *
 *    //Photo.print(photos);
 *
 *    Slide.SlideShow verticalSlideShow = new Slide.SlideShow(false);
 *    Slide.SlideShow slideShow = new Slide.SlideShow(true);
 *
 *    for (Photo photo : photos) {
 *       if (photo.getOrientation() == Orientation.VERTICAL) {
 *          System.out.println("Placing Vertical Photo " + photo.getId());
 *          verticalSlideShow.placePhoto(photo);
 *       } else {
 *          System.out.println("Placing Horizontal Photo " + photo.getId());
 *          slideShow.placePhoto(photo);
 *       }
 *    }
 *
 *    System.out.println("Photos placed");
 *
 *    Slide previousSlide = null;
 *    for (Map.Entry<Integer, List<Slide>> sameTagsSlides : verticalSlideShow.getSlides().entrySet()) {
 *       for (Slide slide : sameTagsSlides.getValue()) {
 *          if (previousSlide == null) {
 *             previousSlide = slide;
 *          } else {
 *             slideShow.placeSlide(new Slide(previousSlide.getLeft(), slide.getLeft()));
 *             previousSlide = null;
 *          }
 *       }
 *    }
 *
 *    PrintWriter out = new PrintWriter(dataSource + ".out");
 *
 *    slideShow.print(out);
 *
 *    out.flush();
 *    out.close();
 *
 */
    }
}
