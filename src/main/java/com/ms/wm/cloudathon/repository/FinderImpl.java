package com.ms.wm.cloudathon.repository;

import com.microsoft.applicationinsights.TelemetryClient;
import com.microsoft.applicationinsights.telemetry.MetricTelemetry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class FinderImpl {

    @Autowired
    TelemetryClient telemetryClient;

    public static final String SELECT_ID_EMP_FNAME_EMP_LNAME_FROM_BYTE_THE_CLOUD_T_1 = "SELECT id,emp_fname,emp_lname from byte_the_cloud_t1";
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void test(){
        long startTime = System.nanoTime();
        jdbcTemplate.query(SELECT_ID_EMP_FNAME_EMP_LNAME_FROM_BYTE_THE_CLOUD_T_1, (rs,rowNum)-> {
           System.out.println("Record - "+rowNum);
           System.out.println("ID - "+rs.getInt(1));
           System.out.println("EMP_FNAME - "+rs.getString(2));
           System.out.println("ID - "+rs.getString(1));

           return rs.getInt(1)+rs.getString(2)+rs.getString(1);
       });
        long endTime = System.nanoTime();
        trackDbQuery(endTime-startTime);
    }
    private void trackDbQuery(long timeTaken){
        MetricTelemetry dbQueryTime = new MetricTelemetry();
        dbQueryTime.setName("eb_query_time");
        dbQueryTime.setValue(timeTaken);
        telemetryClient.trackMetric(dbQueryTime);
    }
}