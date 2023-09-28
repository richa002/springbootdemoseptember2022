package com.example.demo;

import com.example.demo.entity.*;
import com.example.demo.service.CustomerService;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.*;

@SpringBootApplication
public class DemoApplication implements  CommandLineRunner{









    @Autowired
    CustomerService service;
    RestTemplate restTemplate = new RestTemplate();
    String fetchAllUrl = "http://localhost:8080/myapp/customers";
    String fetchSingleRecordUrl = "http://localhost:8080/myapp/customers/1";
    String postUrl = "http://localhost:8080/myapp/customers";
    ObjectMapper objectMapper;
    @PostConstruct
    private void postConstruct() {
        objectMapper = new ObjectMapper();
        //  objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);  // to ignore null fields
        objectMapper.configure(JsonGenerator.Feature.IGNORE_UNKNOWN, true);
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

//    @Autowired
//    RestTemplate restTemplate;
    @Override
    public void run(String... args) throws Exception {

       // service.hello();

//        testPostUrl();
//        testPostUrl2();
//
//        testGetUrl();
//        testGetUrlWithObjectMapper();


//        testGetAllRecords();
//
//        testCallingEndpointWithPathParamRequestParam();

    }

    private void testGetAllRecords() {
//        List<Customer> customers = Arrays.asList(restTemplate.getForObject(fetchAllUrl, Customer[].class));
        ResponseEntity<List<Customer>> listResponseEntity =
                restTemplate.exchange(fetchAllUrl, HttpMethod.GET, null, ParameterizedTypeReference.forType(List.class));
        List<Customer> list = listResponseEntity.getBody();
    }

    private void testGetUrlWithObjectMapper() throws JsonProcessingException {
        String stringResponse = restTemplate.getForObject(fetchSingleRecordUrl, String.class);
        Customer customer1 = objectMapper.readValue(stringResponse, Customer.class);
        System.out.println("Deserialized response from service :"+customer1);
    }

    private void testGetUrl() {
        Customer forObject = restTemplate.getForObject(fetchSingleRecordUrl, Customer.class);
        System.out.println(forObject);
    }

    private RequestEntity<String> getRequestEntity(String stringRequest, HttpHeaders headers,HttpMethod httpMethod, String url)
            throws URISyntaxException {
        RequestEntity<String> requestEntity  = new RequestEntity<>(stringRequest, headers, httpMethod, new URI(url));
        return requestEntity;
    }

    private void testPostUrl() throws JsonProcessingException, URISyntaxException {

        Customer customer = prepareCustomerDto();
        String stringRequest = objectMapper.writeValueAsString(customer);

        RequestEntity<String> requestEntity = getRequestEntity(stringRequest,getHttpHeaders(),HttpMethod.POST,postUrl);
        ResponseEntity<String> responseWithHeaders = restTemplate.exchange(requestEntity, String.class);
        System.out.println(responseWithHeaders.getBody());

    }

    private void testPostUrl2() throws JsonProcessingException, URISyntaxException {

        Customer customer = prepareCustomerDto();
        String stringRequest = objectMapper.writeValueAsString(customer);
        ResponseEntity<String> responseWithoutHeaders = restTemplate.postForEntity(postUrl, customer, String.class);
        System.out.println(responseWithoutHeaders.getBody());

    }

    private Customer prepareCustomerDto() {
        // prepare entity
        Customer customer = new Customer();
        customer.setUser_name("richa002");
        customer.setCustomerId(1);
        customer.setPassword("123456");
        customer.setPhone("4432452424");
        customer.setEmail("richa@gmail.com");
        customer.setFirstName("Richa");
        customer.setLastName("Luthra");
        customer.setAddress(new Address(1,11,111,"brampton", "canada","L3T0A4"));
        return customer;
    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.TEXT_HTML));
        headers.set("X-TP-DeviceID", "1234567890");// any custom header
        return headers;
    }

    private ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);  // to ignore null fields
        objectMapper.configure(JsonGenerator.Feature.IGNORE_UNKNOWN, true);
        return objectMapper;
    }

    private void testCallingEndpointWithPathParamRequestParam() {
        String testUrl = "http://localhost:8080/myapp/customers/hello/{id}/{subId}";

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", "1234");
        params.put("subId", "9999");

        URI uri =   UriComponentsBuilder.fromUriString(testUrl)
                        .uriVariables(params)
                        .queryParam("name", "Richa").build().toUri();


        ResponseEntity<String> response4 = restTemplate.exchange(uri, HttpMethod.GET, null, String.class);
        System.out.println(response4.getBody());
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {

        return builder
                .setConnectTimeout(Duration.ofMillis(3000))
                .setReadTimeout(Duration.ofMillis(3000))
                .build();
    }


}