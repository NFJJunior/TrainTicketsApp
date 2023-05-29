import train.Route;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class ex {
    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        LocalTime time1 = LocalTime.parse("12:00", formatter);
        LocalTime time2 = LocalTime.parse("16:00", formatter);
        LocalTime time3 = LocalTime.parse("16:00:25", formatter);
        System.out.println(time1);
        System.out.println(time2);
        System.out.println(time1.until(time2, ChronoUnit.MINUTES));
        System.out.println(time2.until(time1, ChronoUnit.MINUTES));

//        ArrayList<RouteEdge>
        Route r = new Route(4, "Suceava", "Campulung Moldovenesc", LocalTime.parse("08:05", DateTimeFormatter.ofPattern("HH:mm")), LocalTime.parse("09:32", DateTimeFormatter.ofPattern("HH:mm")));
        r.addRouteEdge("Suceava", "Gura Humorului", 47, LocalTime.parse("08:05", DateTimeFormatter.ofPattern("HH:mm")), LocalTime.parse("08:50", DateTimeFormatter.ofPattern("HH:mm")));
        r.addRouteEdge("Gura Humorului", "Frasin", 7, LocalTime.parse("08:51", DateTimeFormatter.ofPattern("HH:mm")), LocalTime.parse("08:59", DateTimeFormatter.ofPattern("HH:mm")));
        r.addRouteEdge("Frasin", "Campulung Moldovenesc", 35, LocalTime.parse("09:00", DateTimeFormatter.ofPattern("HH:mm")), LocalTime.parse("09:32", DateTimeFormatter.ofPattern("HH:mm")));

        System.out.println(r);
        System.out.println(r.totalDistance());
        r.showDetails();
    }
}
