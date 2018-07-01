package service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Benefiter;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;
import org.omg.IOP.CodecOperations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class BenefiterService {


    private ObjectMapper mapper = new ObjectMapper();

    private final String request0="http://localhost:8080/benefiter/";

    public BenefiterService() {

    }

    public ObjectMapper getMapper() {
        return mapper;
    }

    public void setMapper(ObjectMapper mapper) {
        this.mapper = mapper;
    }




    public Benefiter getBenefiterByEmail(String email)
    {
        String request = "http://localhost:8080/benefiter/findByEmail?email=%s";
        String requestEnc = null;
        try {
            requestEnc = String.format(request,
                    URLEncoder.encode(email, "UTF-8")
            );
        } catch (UnsupportedEncodingException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        Benefiter benefiter = new Benefiter();

        try {DefaultHttpClient httpClient = new DefaultHttpClient();

            HttpGet getRequest = new HttpGet(requestEnc
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
                benefiter= mapper.readValue(output,  new TypeReference<Benefiter>(){});
                httpClient.getConnectionManager().shutdown();
            }


        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return benefiter;
    }

    public List<Benefiter> findAll()
    {
        String request = request0 + "findAll";

        List<Benefiter> benefiters =new ArrayList<Benefiter>();
        try {

            DefaultHttpClient httpClient = new DefaultHttpClient();

            HttpGet getRequest = new HttpGet(request
            );


            HttpResponse response = null;
            try {
                response = httpClient.execute(getRequest);
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatusLine().getStatusCode());
            }

            BufferedReader br = new BufferedReader(
                    new InputStreamReader((response.getEntity().getContent())));

            String output = null;

            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                benefiters= mapper.readValue(output,  new TypeReference<List<Benefiter>>(){});

            }



             httpClient.getConnectionManager().shutdown();

        } catch (ClientProtocolException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();
        }


        return benefiters;

    }


    public Benefiter getBenefiterById( Long benefiterid)
    {
        System.out.println("In get std by id :" + benefiterid);
        String request = request0+"findById?idbenefiter="+benefiterid.toString();

        Benefiter benefiter = new Benefiter();
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
                benefiter= mapper.readValue(output,  new TypeReference<Benefiter>(){});
                httpClient.getConnectionManager().shutdown();
            }


        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return benefiter;
    }

    public boolean logIn( String email, String passwd, String message){

        String request = request0+"logIn?email=%s&passwd=%s";

        String requestEnc = null;
        try {
            requestEnc = String.format(request,
                    URLEncoder.encode(email, "UTF-8"),
                    URLEncoder.encode(passwd, "UTF-8")
            );
        } catch (UnsupportedEncodingException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        boolean result = true;
        String failEmail = "The e-mail address is wrong!";
        String failRegistered="You are not yet registered!";
        String failPassword = "The password or the e-mail address is wrong!";
       // String success = "Successful login!";
        String output;


        DefaultHttpClient httpClient = new DefaultHttpClient();


        HttpGet getRequest = new HttpGet(requestEnc	);


        HttpResponse response;
        try {
            response = httpClient.execute(getRequest);


            BufferedReader br = new BufferedReader(
                    new InputStreamReader((response.getEntity().getContent())));


            System.out.println("Output from Server .... \n");


            message= br.readLine();

            httpClient.getConnectionManager().shutdown();
            System.out.println("In stserv "+message);
            if(message.equals(failEmail)) result =  false;
            else if(message.equals(failPassword)) result= false;
                else if(message.equals(failRegistered)) result = false;

        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }

    public Benefiter register( String token, String passwd)
    {


        String url = request0+"register?token=%s&passwd=%s";



        String requestEnc = null;
        try {
            requestEnc = String.format(url,
                    URLEncoder.encode(token, "UTF-8"),
                    passwd
            );
        } catch (UnsupportedEncodingException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        System.out.println(requestEnc);

        Benefiter benefiter = new Benefiter();

        try {DefaultHttpClient httpClient = new DefaultHttpClient();

            HttpPut putRequest = new HttpPut(requestEnc
            );



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
                benefiter= mapper.readValue(output,  new TypeReference<Benefiter>(){});
                httpClient.getConnectionManager().shutdown();
            }


        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return benefiter;



    }

    public Benefiter saveBenefiter(String email,  String name,  Long kind) {



        String url = request0+"save?email=%s&name=%s&kind="+kind.toString();

        Benefiter benefiter = new Benefiter();


        String requestEnc = null;
        try {
            requestEnc = String.format(url,
                    URLEncoder.encode(email, "UTF-8"),
                    URLEncoder.encode(name, "UTF-8")
            );

            System.out.println(requestEnc);
        } catch (UnsupportedEncodingException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        DefaultHttpClient httpClient = new DefaultHttpClient();


        HttpPost postRequest = new HttpPost(requestEnc);

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
                benefiter= mapper.readValue(output,  new TypeReference<Benefiter>(){});
                httpClient.getConnectionManager().shutdown();
            }



        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return benefiter;
    }

  /*  public Benefiter updateBenefiter(  Long benefiterId,  String email,  String name,  String token,  Long kind) {


        String url = request +"update?benefiterId=%s&email=%s&group=%s&hobby=%s&name=%s";



        Benefiter benefiter = new Benefiter();

        String requestEnc = null;
        try {
            requestEnc = String.format(url,
                    URLEncoder.encode(benefiterId.toString(),"UTF-8"),
                    URLEncoder.encode(email, "UTF-8"),
                    URLEncoder.encode(group, "UTF-8"),
                    URLEncoder.encode(hobby, "UTF-8"),
                    URLEncoder.encode(name, "UTF-8")
            );

            System.out.println(requestEnc);
        } catch (UnsupportedEncodingException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        DefaultHttpClient httpClient = new DefaultHttpClient();


        HttpPut putRequest = new HttpPut(requestEnc);

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
                benefiter= mapper.readValue(output,  new TypeReference<Benefiter>(){});
                httpClient.getConnectionManager().shutdown();
            }



        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return benefiter;

    }
*/
    public String deleteBenefiterById(  Long benefiterId)
    {

        String url = request0 +"deleteById?idbenefiter="+benefiterId.toString();



        String ret=null;

        DefaultHttpClient httpClient = new DefaultHttpClient();


        HttpDelete deleteRequest = new HttpDelete(url);

        try {
            HttpResponse response = httpClient.execute(deleteRequest);

            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatusLine().getStatusCode());
            }

            BufferedReader br = new BufferedReader(
                    new InputStreamReader((response.getEntity().getContent())));



            System.out.println("Output from Server .... \n");
            ret = br.readLine();



        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ret;

    }

    public void delete(Long idbenefiter)
    {
        

    }

    public Benefiter changePassword( String email, String passwd, String newpass)
    {
        //http://localhost:8080/benefiter/changePassword?email=zeEmail&passwd=string&newpasswd=stringNou

        String url = request0 +"changePassword?email=%s&passwd=%s&newpasswd=%s";



        Benefiter benefiter = new Benefiter();

        String requestEnc = null;
        try {
            requestEnc = String.format(url,
                    URLEncoder.encode(email, "UTF-8"),
                    URLEncoder.encode(passwd, "UTF-8"),
                    URLEncoder.encode(newpass, "UTF-8")
            );

            System.out.println(requestEnc);
        } catch (UnsupportedEncodingException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        DefaultHttpClient httpClient = new DefaultHttpClient();


        HttpPut putRequest = new HttpPut(requestEnc);

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
                benefiter= mapper.readValue(output,  new TypeReference<Benefiter>(){});
                httpClient.getConnectionManager().shutdown();
            }



        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return benefiter;

    }




 


}
