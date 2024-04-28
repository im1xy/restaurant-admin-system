package DB.SQL.dashboard;

import javafx.scene.chart.XYChart;

import static DB.SQL.dashboard.DashboardSQLTools.getIntegerFromQuery;
import static DB.SQL.dashboard.DashboardSQLTools.getXYSeriesFromQuery;


public interface DashboardSQL {
    static Integer calculateTotalCustomers() {
        String selectQuery =
                """
                select
                	count(*) as count
                from
                	rest.orders;
                """;
        return getIntegerFromQuery(selectQuery);
    }

    static Integer calculateTodaysCustomers() {
        String selectQuery =
                """
                select
                	count(*) as count
                from
                	rest.orders
                where
                	Date between (date(now()) - INTERVAL 1 DAY) and date(now());
                
                """;
        return getIntegerFromQuery(selectQuery);
    }

    static Integer calculateTotalIncome() {
        String selectQuery =
                """
                select
                	sum(Total) as sum
                from
                	rest.orders
                """;
        return getIntegerFromQuery(selectQuery);
    }

    static Integer calculateTodaysIncome() {
        String selectQuery =
                """
                select
                	sum(Total) as sum
                from
                	rest.orders
                where
                	Date between (date(now()) - INTERVAL 1 DAY) and date(now());
                """;
        return getIntegerFromQuery(selectQuery);
    }

    static Integer calculateTotalSoldItems() {
        String selectQuery =
                """
                select
                	sum(Quantity) as sum
                from
                	rest.orders inner join rest.orderdetails
                		on orders.ID = orderdetails.Order_ID
                """;
        return getIntegerFromQuery(selectQuery);
    }



    static XYChart.Series<String, Number> calculateCustomersPerMonth() {
        String selectQuery =
                """
                select
                    count(*) as custCount,
                    month(Date) as Date
                from
                    rest.orders
                group by
                   	month(Date)
                order by
                	Date
                """;
        return getXYSeriesFromQuery(selectQuery);
    }

    static XYChart.Series<String, Number> calculateIncomePerMonth() {
        String selectQuery =
                """
                select
                    Sum(Total) as Sum,
                    month(Date) as Month
                from
                	rest.orders
                group by
                	month(Date)
                order by
                	Month
                """;
        return getXYSeriesFromQuery(selectQuery);
    }

    static XYChart.Series<String, Number> calculateOrdersForLastWeek() {
        String selectQuery =
                """
                select
                    count(*) as orderCount,
                	date(Date) as Date
                from
                	rest.orders
                where
                	Date between date_sub(now(), INTERVAL 1 WEEK) and now()
                group by
                	date(Date)
                """;
        return getXYSeriesFromQuery(selectQuery);
    }

    static XYChart.Series<String, Number> calculateIncomePerYear() {
        String selectQuery =
                """
                select
                    Sum(Total) as Sum,
                	year(Date) as Year
                from
                	rest.orders
                group by
                	year(Date)
                """;
        return getXYSeriesFromQuery(selectQuery);
    }
}
