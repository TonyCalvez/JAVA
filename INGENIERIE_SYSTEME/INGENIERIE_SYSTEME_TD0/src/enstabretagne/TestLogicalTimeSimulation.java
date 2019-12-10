package enstabretagne;
import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;

public class TestLogicalTimeSimulation {
    public static void main(String[] args) {
        // String dateBase = "10/12/2016 10:34:47.6789";
        // System.out.println(LogicalDateTime.EstBienStructuree(dateBase));
        LogicalDateTime dateBase = new LogicalDateTime("10/12/2016 10:34:47.6789");
        LogicalDuration duration= LogicalDuration.ofMinutes(15)
                .add(LogicalDuration.ofSeconds(12))
                .add(LogicalDuration.ofMillis(67));

        System.out.println(dateBase);
        System.out.println(duration);
        System.out.println(dateBase.add(duration));
    }
}
