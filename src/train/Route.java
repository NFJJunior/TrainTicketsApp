package train;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Objects;

class RouteEdge {
    String start, finish;
    int distance;
    LocalTime startTime, finishTime;

    RouteEdge(String start, String finish, int distance, LocalTime startTime, LocalTime finishTime) {
        this.start = start;
        this.finish = finish;
        this.distance = distance;
        this.startTime = startTime;
        this.finishTime = finishTime;
    }

    RouteEdge(RouteEdge ob) {
        this.start = ob.start;
        this.finish = ob.finish;
        this.distance = ob.distance;
        this.startTime = ob.startTime;
        this.finishTime = ob.finishTime;
    }
}
public class Route {
    //  Attributes
    boolean hasTrain;
    private final int nrStations;
    private final String startStation;
    private final String finishStation;
    private final LocalTime startTime;
    private final LocalTime finishTime;

    private final ArrayList<RouteEdge> route;

    //  Constructors
    public Route(int nrStations, String startStation, String finishStation, LocalTime startTime, LocalTime finishTime) {
        this.hasTrain = false;
        this.nrStations = nrStations;
        this.startStation = startStation;
        this.finishStation = finishStation;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.route = new ArrayList<>(nrStations - 1);
    }

    //  Getters & Setters
    public boolean getHasTrain() {
        return hasTrain;
    }

    public void setHasTrain(boolean hasTrain) {
        this.hasTrain = hasTrain;
    }

    public int getNrStations() {
        return nrStations;
    }

    public String getStartStation() {
        return startStation;
    }

    public String getFinishStation() {
        return finishStation;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getFinishTime() {
        return finishTime;
    }

    public ArrayList<RouteEdge> getRoute() {
        return route;
    }

    //  Methods
    @Override
    public String toString() {
        return startStation + "(" +
                startTime + ") -> " +
                finishStation + "(" +
                finishTime + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route1 = (Route) o;
        return hasTrain == route1.hasTrain && nrStations == route1.nrStations && Objects.equals(startStation, route1.startStation) && Objects.equals(finishStation, route1.finishStation) && Objects.equals(startTime, route1.startTime) && Objects.equals(finishTime, route1.finishTime) && Objects.equals(route, route1.route);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hasTrain, nrStations, startStation, finishStation, startTime, finishTime, route);
    }

    public void addRouteEdge(String start, String finish, int distance, LocalTime startTime, LocalTime finishTime) {
        route.add(new RouteEdge(start, finish, distance, startTime, finishTime));
    }

    public int totalDistance() {
        int distance = 0;
        for (RouteEdge re : route)
            distance += re.distance;
        return distance;
    }

    public void showDetails() {
        LocalTime helpingTime = null;

        for(int i = 0; i < nrStations - 1; i++) {
            System.out.printf("%-15s%-25s%-25s\n", (helpingTime != null) ? helpingTime : "", route.get(i).start, route.get(i).startTime);
            helpingTime = route.get(i).finishTime;
        }
        System.out.printf("%-15s%-25s%-25s\n", helpingTime, finishStation, "");
    }

    public void showStations() {
        for(int i = 0; i < nrStations - 1; i++) {
            System.out.print(route.get(i).start + "->");
            if (i % 5 == 4)
                System.out.println();
        }
        System.out.println(finishStation);
    }
}
