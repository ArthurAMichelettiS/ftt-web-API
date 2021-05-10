package ec.ftt.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import ec.ftt.beans.chart;
import ec.ftt.model.DBUtil;

public class chartDao {

    private Connection connection;

    public chartDao() {
        connection = DBUtil.getConnection();
    } //chartDao

    public void addchart(chart chart) {
        
    	try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO CHART (Happy, Sad, Confused) VALUES (?, ?, ?)");
            
            // Parameters start with 1
            preparedStatement.setString(1, chart.qtdHappy());
            preparedStatement.setString(2, chart.getqtdSad());
            //preparedStatement.setDate(3, (java.sql.Date)chart.getDob());
            preparedStatement.setString(3, chart.getqtdConfused());

            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    } //addchart
    
    public void deletechart(Long id) {
    	
    	chart chart = new chart();
    	chart.setId(id);
    	
    	deletechart(chart);
    	
    } // deletechart long

    public void deletechart(chart chart) {
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

    public void updatechart(chart chart) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE chart SET Happy=?, " 
                    		                          + "Sad=?, " 
                    		                          + "Confused=? " 
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

    public List<chart> getAllchart() {
        
    	List<chart> chartList = new ArrayList<chart>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM chart");
            while (rs.next()) {
                
            	chart chart = new chart();
                
            	chart.setId(rs.getLong("ID"));
                chart.setName(rs.getString("Happy"));
                chart.setEmail(rs.getString("Sad"));
                //chart.setDob(rs.getDate("DOB"));
                chart.setColor(rs.getString("Confused"));

                chartList.add(chart);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return chartList;
    } //getAllchart

    public chart getchartById(Long id) {
    	
    	chart chart = new chart();
    	chart.setId(id);
    	
    	return getchartById(chart);
    	
    } // getchartById long
    
    
    	
    public chart getchartById(chart chart) {

    	chart chartOutput = new chart();
        
    	try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("SELECT * from chart WHERE ID=?");
            
            preparedStatement.setLong(1, chart.getId());
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
            	chartOutput.setId(rs.getLong("ID"));
            	chartOutput.setName(rs.getString("Happy"));
            	chartOutput.setEmail(rs.getString("Sad"));
            	//chartOutput.setDob(rs.getDate("DOB"));
            	chartOutput.setColor(rs.getString("Confused"));

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