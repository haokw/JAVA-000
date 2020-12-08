package com.hongyuchang.generatemillionrecord.jdbc;

import java.io.Console;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class GenerateRecord {
//    public int count=0;
    // 耗时 406587 毫秒
    public void generateMillionRecordWithJDBC(){
        Connection conn = null;
        Statement stmt = null;
        int loopTimes = 1000000;
        try {
            long startTime = Calendar.getInstance().getTimeInMillis();
            conn = JDBCHelper.getConnection();
            stmt = conn.createStatement();
            int i = loopTimes - 1000000;
            for(i=0;i< loopTimes;i++){
                String sql = "insert into tbl_order(price, utime, address, number, user_id)"
                        + "values(" + "'" + i + "', UNIX_TIMESTAMP(), '~'," + i + ","+ i +")";
                stmt.execute(sql);
            }
            long endTime = Calendar.getInstance().getTimeInMillis();
            System.out.println("耗时："+(endTime - startTime));
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if(stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if(conn!= null) {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }

    }
    // 耗时：446490
    public void generateMillionRecordWithJDBCBatch(){
        String sql = "insert into tbl_order(price,utime,address,number,user_id)values(?,UNIX_TIMESTAMP(),'~',?,?)";
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        int loopTimes = 1000000;
        try {
            long startTime = Calendar.getInstance().getTimeInMillis();
            conn = JDBCHelper.getConnection();
            preparedStatement = conn.prepareStatement(sql);
            int i = loopTimes - 1000000;
            for(i = 0; i < loopTimes; i++){
                // preparedStatement.setLong(1, i);;
                preparedStatement.setDouble(1, Math.random());
                preparedStatement.setInt(2, i);
                preparedStatement.setLong(3, i);;
                preparedStatement.execute();
            }
            long endTime = Calendar.getInstance().getTimeInMillis();
            System.out.println("耗时："+(endTime - startTime));
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if(preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException throwable) {
                    throwable.printStackTrace();
                }
            }
            if(conn!= null) {
                try {
                    conn.close();
                } catch (SQLException throwable) {
                    throwable.printStackTrace();
                }
            }
        }
    }
}
