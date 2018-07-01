package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.Basket;


public class BasketService {

    private ObjectMapper mapper = new ObjectMapper();

    public BasketService() {
    }

    public ObjectMapper getMapper() {
        return mapper;
    }

    public void setMapper(ObjectMapper mapper) {
        this.mapper = mapper;
    }
   private String request0="http://localhost:8080/basket/";
    
    public List<Basket> findAll()
    {
    	 String request = request0 + "findAll";

         List<Basket> baskets = new ArrayList<Basket>();
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
                 baskets = mapper.readValue(output, new TypeReference<List<Basket>>() {
                 });


             }


             httpClient.getConnectionManager().shutdown();
         } catch (ClientProtocolException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         }
         return baskets;
    }

    Basket findById(Long idbasket)
    {
    	  String request = request0 + "findById?idpet=" + idbasket.toString();

          Basket basket = new Basket();
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
                  basket = mapper.readValue(output, new TypeReference<Basket>() {
                  });
                  httpClient.getConnectionManager().shutdown();
              }


          } catch (Exception e) {
              e.printStackTrace();
              return null;
          }

          return basket;
    }

    public Basket saveBasket(String email, String address, String phone, String petname)
    {
    	 String request = request0+"save?email=%s&petName=%s&address=%s&phone=%s";

    	 
    	 String requestEnc = null;
			try {
				requestEnc = String.format(request,
				  URLEncoder.encode(email, "UTF-8"),
				  URLEncoder.encode(petname, "UTF-8"),
				  URLEncoder.encode(address, "UTF-8"),
				  URLEncoder.encode(phone, "UTF-8")
			
				);
				
				System.out.println(requestEnc);
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	  
    	 
    	 

         DefaultHttpClient httpClient = new DefaultHttpClient();


         HttpPost postRequest = new HttpPost(requestEnc);

         Basket basket = new Basket();
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
                 basket= mapper.readValue(output,  new TypeReference<Basket>(){});
                 httpClient.getConnectionManager().shutdown();
             }



         } catch (ClientProtocolException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
         } catch (IOException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
         }
         return basket;
    }


//    public void removeAnimals(String email);

 //   public Basket addAnimals(Long idbasket, List<String> names);

    public Basket updateBasket(Long idbasket, String address, String phone) 
    {
    	
 String request = request0+"update?address=%s&phone=%s";

    	 
    	 String requestEnc = null;
			try {
				requestEnc = String.format(request,
				  URLEncoder.encode(address, "UTF-8"),
				  URLEncoder.encode(phone, "UTF-8")
			
				);
				
				System.out.println(requestEnc);
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	  
    	 
    	 

         DefaultHttpClient httpClient = new DefaultHttpClient();


         HttpPut putRequest = new HttpPut(requestEnc);

         Basket basket = new Basket();
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
                 basket= mapper.readValue(output,  new TypeReference<Basket>(){});
                 httpClient.getConnectionManager().shutdown();
             }



         } catch (ClientProtocolException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
         } catch (IOException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
         }
         return basket;
    }


   public  String delete(Long basketId)
    {
    	String url = request0 +"deleteById?idbasket="+basketId.toString();
    	  
    	  
    	  
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
    

  //  void deleteBasketByIdBenefiter(Long BasketId)
  

    List<Basket> findBasketsByBenefiterEmail(String email)
    {
    	//http://localhost:8080/basket/findAllByBenefiter?email=admin%40as.com
    	 String request = request0 + "fiindAllByBenefiter?email=%s";
    	 


    	 String requestEnc = null;
			try {
				requestEnc = String.format(request,
				  URLEncoder.encode(email, "UTF-8")
			
				);
				
				System.out.println(requestEnc);
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    	 
    	 
         List<Basket> baskets = new ArrayList<Basket>();
         try {

             DefaultHttpClient httpClient = new DefaultHttpClient();


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
                 baskets = mapper.readValue(output, new TypeReference<List<Basket>>() {
                 });


             }


             httpClient.getConnectionManager().shutdown();
         } catch (ClientProtocolException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         }
         return baskets;
    }
    
    
}
