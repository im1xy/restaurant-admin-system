package GUI.panel.dashboard;

import DB.SQL.dashboard.DashboardSQL;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    @FXML private AnchorPane dashboardForm;
    @FXML private Label totalCustomers;
    @FXML private Label todaysIncome;
    @FXML private Label totalIncome;
    @FXML private Label todaysCustomers;
    @FXML private Label totalSoldItems;
    @FXML private BarChart<String, Number> customersPerMonthBarChart;
    @FXML private LineChart<String , Number> incomePerYearLineChart;
    @FXML private AreaChart<String, Number> incomePerMonthAreaChart;
    @FXML private BarChart<String, Number> ordersForLastWeekBarChart;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDashboardPage();
    }

    public void loadDashboardPage() {
        loadCharts();
        loadCardsData();
    }

    private void loadCharts() {
        customersPerMonthBarChart.getData().add(DashboardSQL.calculateCustomersPerMonth());
        incomePerYearLineChart.getData().add(DashboardSQL.calculateIncomePerYear());
        incomePerMonthAreaChart.getData().add(DashboardSQL.calculateIncomePerMonth());
        ordersForLastWeekBarChart.getData().add(DashboardSQL.calculateOrdersForLastWeek());
    }

    private void loadCardsData() {
        todaysCustomers.setText(String.valueOf(DashboardSQL.calculateTodaysCustomers()));
        totalCustomers.setText(String.valueOf(DashboardSQL.calculateTotalCustomers()));
        todaysIncome.setText(String.valueOf(DashboardSQL.calculateTodaysIncome()));
        totalIncome.setText(String.valueOf(DashboardSQL.calculateTotalIncome()));
        totalSoldItems.setText(String.valueOf(DashboardSQL.calculateTotalSoldItems()));
    }
}
