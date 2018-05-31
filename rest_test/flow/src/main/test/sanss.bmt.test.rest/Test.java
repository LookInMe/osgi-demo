package sanss.bmt.test.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Test {
    public static void main(String[] args) {
        String res = "{'res':'flow success'}";
        ObjectMapper mapper = new ObjectMapper();
        try {
            res = mapper.writeValueAsString(res);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.print(res);
    }


}
