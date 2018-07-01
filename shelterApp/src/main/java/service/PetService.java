package service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Pet;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class PetService {

    private String request0 = "http://localhost:8080/pet/";
    private ObjectMapper mapper = new ObjectMapper();

    public PetService() {
    }

    public ObjectMapper getMapper() {
        return mapper;
    }

    public void setMapper(ObjectMapper mapper) {
        this.mapper = mapper;
    }


    public List<Pet> findAll() {
        String request = new String(request0 + "findAll");

        List<Pet> pets = new ArrayList<Pet>();
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
                pets = mapper.readValue(output, new TypeReference<List<Pet>>() {
                });


            }


            httpClient.getConnectionManager().shutdown();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pets;
    }

    Pet findById(Long petid) {
        System.out.println("In get std by id :" + petid);
        String request =new String( request0 + "findById?idpet=" + petid.toString());

        Pet pet = new Pet();
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
                pet = mapper.readValue(output, new TypeReference<Pet>() {
                });
                httpClient.getConnectionManager().shutdown();
            }


        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return pet;
    }

   public Pet findPetByName(String name) {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        //http://localhost:8080/pet/findByName?name=coco
        String request =new String(request0 + "findByName?name=" + name);
        Pet pet = new Pet();

        
        System.out.println(request0);
        HttpGet getRequest = new HttpGet(request);


        try{
        System.out.println(getRequest);
        System.out.println(getRequest);
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
            pet = mapper.readValue(output, new TypeReference<Pet>() {
            });
            httpClient.getConnectionManager().shutdown();
        }


    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }

        return pet;
    }



    public Pet savePet(String name, String age,  String info,  String health,  String animal)
    {
        String request = new String(request0+"save?name="+name+"&age="+age+"&info="+info + "&health="+health + "&animal="+animal);


        DefaultHttpClient httpClient = new DefaultHttpClient();


        HttpPost postRequest = new HttpPost(request);

        Pet pet = new Pet();
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
                pet= mapper.readValue(output,  new TypeReference<Pet>(){});
                httpClient.getConnectionManager().shutdown();
            }



        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return pet;
    }




    public Pet updateHealth(String name, String newHealth)
    {

        String request=request0+"updateHealth?name="+name+"&newHealth=%s";

        String reqURL = null;

        try {
            reqURL =String.format(request, URLEncoder.encode(newHealth,"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Pet pet = new Pet();

        DefaultHttpClient httpClient = new DefaultHttpClient();


        System.out.print(reqURL);
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
                pet= mapper.readValue(output,  new TypeReference<Pet>(){});
                httpClient.getConnectionManager().shutdown();
            }



        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return pet;
    }

    
    
    public Pet update(String name, String newname,String age,String info,String health,String animal)
    {
    	String url = request0 +"update?name=%s&newname=%s&age=%s&info=%s&health=%s&animal=%s";
  	  
  	  
  	  
  	  Pet pet = new Pet();
  	 
  	  String requestEnc = null;
  			try {
  				requestEnc = String.format(url,
  				  URLEncoder.encode(name,"UTF-8"),
  				  URLEncoder.encode(newname, "UTF-8"),
  				  URLEncoder.encode(age, "UTF-8"),
  				  URLEncoder.encode(info, "UTF-8"),
  				  URLEncoder.encode(health, "UTF-8"),
  				 URLEncoder.encode(animal, "UTF-8")
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
  			pet= mapper.readValue(output,  new TypeReference<Pet>(){});
  			httpClient.getConnectionManager().shutdown();
  		}	
  		
  		
  		
  	} catch (ClientProtocolException e) {
  		// TODO Auto-generated catch block
  		e.printStackTrace();
  	} catch (IOException e) {
  		// TODO Auto-generated catch block
  		e.printStackTrace();
  	}
  	  return pet;
    }
    

    public String delete(Long idpet)
    {
    	String url = new String(request0 +"delete?idpet="+idpet.toString());
    
  	  
   	 String ret=null;
   	  
   	  DefaultHttpClient httpClient = new DefaultHttpClient();
         
         System.out.print(url);
         System.out.print(url);
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

}
