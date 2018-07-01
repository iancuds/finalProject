package service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Shelter;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class ShelterService {

    private ObjectMapper mapper = new ObjectMapper();
    private String request0 ="http://localhost:8080/shelter/";

    public ShelterService() {
    }

    public ObjectMapper getMapper() {
        return mapper;
    }

    public void setMapper(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public List<Shelter> findAll() {
        String request = request0 + "findAll";

        List<Shelter> shelters = new ArrayList<Shelter>();
        try {

            DefaultHttpClient httpClient = new DefaultHttpClient();


            HttpGet getRequest = new HttpGet(request
            );


            HttpResponse response = httpClient.execute(getRequest);

            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatusLine().getStatusCode());
            }

            BufferedReader br = new BufferedReader(
                    new InputStreamReader((response.getEntity().getContent())));

            String output = null;

            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                shelters = mapper.readValue(output, new TypeReference<List<Shelter>>() {
                });


            }


            httpClient.getConnectionManager().shutdown();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return shelters;

    }

        Shelter findById(Long shelterid)
    {


        System.out.println("In get shelter by id :" + shelterid);
        String request = request0+"getById?studentId="+shelterid.toString();

        Shelter shelter = new Shelter();
        try {DefaultHttpClient httpClient = new DefaultHttpClient();

            HttpGet getRequest = new HttpGet(request
            );



            HttpResponse response = httpClient.execute(getRequest);

            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatusLine().getStatusCode());
            }

            BufferedReader br = new BufferedReader(
                    new InputStreamReader((response.getEntity().getContent())));

            String output = null;

            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                shelter= mapper.readValue(output,  new TypeReference<Shelter>(){});
                httpClient.getConnectionManager().shutdown();
            }


        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return shelter;

    }

    public Shelter updateCapital(Long shelterid, Float capitalAdded)
    {

        //http://localhost:8080/shelter/addCapital?shelterid=1&capitalAdded=200
       String request = request0 + "addCapital?shelterid="+shelterid+"&capitalAdded="+capitalAdded;

        Shelter shelter = new Shelter();

        DefaultHttpClient httpClient = new DefaultHttpClient();


        HttpPut putRequest = new HttpPut(request);


        try {
            HttpResponse response = httpClient.execute(putRequest);

            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatusLine().getStatusCode());
            }

            BufferedReader br = new BufferedReader(
                    new InputStreamReader((response.getEntity().getContent())));

            String output = null;

            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                shelter= mapper.readValue(output,  new TypeReference<Shelter>(){});
                httpClient.getConnectionManager().shutdown();
            }



        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return shelter;
    }

    Shelter updateNrPets(Long shelterid, Long nrPetsAdded)
    {

        //http://localhost:8080/shelter/addAnimals?shelterid=1&nrPetsAdded=2

String  request = request0 + "addAnimals?shelterid="+shelterid+"&nrPetsAdded="+nrPetsAdded;

        Shelter shelter = new Shelter();

        DefaultHttpClient httpClient = new DefaultHttpClient();


        HttpPut putRequest = new HttpPut(request);


        try {
            HttpResponse response = httpClient.execute(putRequest);

            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatusLine().getStatusCode());
            }

            BufferedReader br = new BufferedReader(
                    new InputStreamReader((response.getEntity().getContent())));

            String output = null;

            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                shelter= mapper.readValue(output,  new TypeReference<Shelter>(){});
                httpClient.getConnectionManager().shutdown();
            }



        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return shelter;
    }


    Shelter updateAbout(Long shelterid, String about)
    {

        String request = request0 + "addAnimals?shelterid="+shelterid+"&about=%s";

        String reqURL = null;

        try {
            reqURL =String.format(request, URLEncoder.encode(about,"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Shelter shelter = new Shelter();

        DefaultHttpClient httpClient = new DefaultHttpClient();


        HttpPut putRequest = new HttpPut(reqURL);


        try {
            HttpResponse response = httpClient.execute(putRequest);

            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatusLine().getStatusCode());
            }

            BufferedReader br = new BufferedReader(
                    new InputStreamReader((response.getEntity().getContent())));

            String output = null;

            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                shelter= mapper.readValue(output,  new TypeReference<Shelter>(){});
                httpClient.getConnectionManager().shutdown();
            }



        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return shelter;
    }



    public Shelter saveShelter(Long nrpets, Float capital, String about)
    {
        String request = request0+"save?nrpets="+nrpets.toString()+"&capital="+capital+"&about=%s";

        String reqURL = null;

        try {
            reqURL =String.format(request, URLEncoder.encode(about,"UTF-8"));
            System.out.println(reqURL);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        DefaultHttpClient httpClient = new DefaultHttpClient();


        HttpPost postRequest = new HttpPost(reqURL);

        Shelter shelter = new Shelter();
        try {
            HttpResponse response = httpClient.execute(postRequest);

            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatusLine().getStatusCode());
            }

            BufferedReader br = new BufferedReader(
                    new InputStreamReader((response.getEntity().getContent())));

            String output = null;

            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                shelter= mapper.readValue(output,  new TypeReference<Shelter>(){});
                httpClient.getConnectionManager().shutdown();
            }



        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return shelter;
    }



}
