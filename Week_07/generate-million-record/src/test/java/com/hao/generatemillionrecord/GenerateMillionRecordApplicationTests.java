package com.hao.generatemillionrecord;

import com.hao.generatemillionrecord.jdbc.GenerateRecord;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GenerateMillionRecordApplicationTests {

    @Test
    void contextLoads() {
        GenerateRecord gr = new GenerateRecord();
        gr.generateMillionRecordWithJDBC();
        // gr.generateMillionRecordWithJDBCBatch();
    }

}
