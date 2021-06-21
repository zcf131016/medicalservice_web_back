package com.example.medicalservice;

import com.example.medicalservice.util.RandomUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootTest
class MedicalserviceApplicationTests {

    @Test
    void contextLoads() {
        Set<Integer> set = new HashSet<>();
        for(int i = 0;i < 100;i++) {
            Integer num = RandomUtil.getRandom(9);
            System.out.println(num);
            set.add(num);
        }
        if(set.size() != 100) {
            System.out.println("出现重复");
        }
    }

}
