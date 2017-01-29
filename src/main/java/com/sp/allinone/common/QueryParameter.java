package com.sp.allinone.common;

/**
 * Created by i82298 on 1/8/2017.
 */
public class QueryParameter {

    /**
     * Sort order ASC or DESC
     */
    private String order;

    /**
     * Column to sort by. Contains ColumnName of the column to sort by
     */
    private String orderField;

    // fields for pagination
    /**
     * Page No required
     */
    private int pageNo;

    /**
     * records per page
     */
    private int pageSize;

    private String recordCountQuery;

    /**
     * Contains main SQL query
     */
    private String mainQuery;

    /**
     * Time taken to execute record count query
     */
    private int recordCountQueryExecutionTime;

    /**
     * Time taken to execute main query
     */
    private int mainQueryExecutionTime;

    /**
     * Total Query Execution Time=
     * recordCountQueryExecutionTime+mainQueryExecutionTime
     */
    private int totalQueryExecutionTime;

    // connection parameters
    /**
     * Database Alias string
     */
    private String dataSourceName;

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getOrderField() {
        return orderField;
    }

    public void setOrderField(String orderField) {
        this.orderField = orderField;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getRecordCountQuery() {
        return recordCountQuery;
    }

    public void setRecordCountQuery(String recordCountQuery) {
        this.recordCountQuery = recordCountQuery;
    }

    public String getMainQuery() {
        return mainQuery;
    }

    public void setMainQuery(String mainQuery) {
        this.mainQuery = mainQuery;
    }

    public int getRecordCountQueryExecutionTime() {
        return recordCountQueryExecutionTime;
    }

    public void setRecordCountQueryExecutionTime(int recordCountQueryExecutionTime) {
        this.recordCountQueryExecutionTime = recordCountQueryExecutionTime;
    }

    public int getMainQueryExecutionTime() {
        return mainQueryExecutionTime;
    }

    public void setMainQueryExecutionTime(int mainQueryExecutionTime) {
        this.mainQueryExecutionTime = mainQueryExecutionTime;
    }

    public int getTotalQueryExecutionTime() {
        return totalQueryExecutionTime;
    }

    public void setTotalQueryExecutionTime(int totalQueryExecutionTime) {
        this.totalQueryExecutionTime = totalQueryExecutionTime;
    }

    public String getDataSourceName() {
        return dataSourceName;
    }

    public void setDataSourceName(String dataSourceName) {
        this.dataSourceName = dataSourceName;
    }
}