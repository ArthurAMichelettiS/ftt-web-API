package ec.ftt.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ec.ftt.beans.Chart;
import ec.ftt.model.DBUtil;

public class DataChartDao {

    private Connection connection;

    public DataChartDao() {
        connection = DBUtil.getConnection();
    } //chartDao

    public void addchart(Chart chart) {
        
    	try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO chart (qtdHappy, qtdSad, qtdConfused) VALUES (?, ?, ?)");
            
            // Parameters start with 1
            preparedStatement.setLong(1, chart.getqtdHappy());
            preparedStatement.setLong(2, chart.getqtdSad());
            //preparedStatement.setDate(3, (java.sql.Date)chart.getDob());
            preparedStatement.setLong(3, chart.getqtdConfused());

            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    } //addchart
    
    public void deletechart(Long id) {
    	
    	Chart chart = new Chart();
    	chart.setId(id);
    	
    	deletechart(chart);
    	
    } // deletechart long

    public void deletechart(Chart chart) {
        try {
            
        	PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM chart WHERE ID=?");
            
            // Parameters start with 1
            preparedStatement.setLong(1, chart.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    } //deletechart

    public void updatechart(Chart chart) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE chart SET qtdHappy=?, " 
                    		                          + "qtdSad=?, " 
                    		                          + "qtdConfused=? " 
                                               + "WHERE ID=?");
            
            // Parameters start with 1
            preparedStatement.setLong(1, chart.getqtdHappy());
            preparedStatement.setLong(2, chart.getqtdSad());
            //preparedStatement.setDate(3, (java.sql.Date)chart.getDob());
            preparedStatement.setLong(3, chart.getqtdConfused());
            
            preparedStatement.setLong(4, chart.getId());
            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    } //updatechart

    public List<Chart> getAllchart() {
        
    	List<Chart> chartList = new ArrayList<Chart>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM chart");
            while (rs.next()) {
                
            	Chart chart = new Chart();
                
            	chart.setId(rs.getLong("ID"));
                chart.setqtdHappy(rs.getLong("qtdHappy"));
                chart.setqtdSad(rs.getLong("qtdSad"));
                //chart.setDob(rs.getDate("DOB"));
                chart.setqtdConfused(rs.getLong("qtdConfused"));

                chartList.add(chart);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return chartList;
    } //getAllchart

    public Chart getchartById(Long id) {
    	
    	Chart chart = new Chart();
    	chart.setId(id);
    	
    	return getchartById(chart);
    	
    } // getchartById long
    
    
    	
    public Chart getchartById(Chart chart) {

    	Chart chartOutput = new Chart();
        
    	try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("SELECT * from chart WHERE ID=?");
            
            preparedStatement.setLong(1, chart.getId());
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
            	
            	chartOutput.setId(rs.getLong("ID"));
            	chartOutput.setqtdHappy(rs.getLong("qtdHappy"));
            	chartOutput.setqtdSad(rs.getLong("qtdSad"));
            	//chartOutput.setDob(rs.getDate("DOB"));
            	chartOutput.setqtdConfused(rs.getLong("qtdConfused"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return chartOutput;
    } //getchartById
    
    public String getDbDate() {

    	String output="";
    	
    	try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("SELECT now() NOW");
            
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
            	output = rs.getString("NOW");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return output;
    } //getDbDate
    
} //chartDao