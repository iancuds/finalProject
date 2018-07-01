import java.util.List;

import model.Basket;
import model.Benefiter;
import model.Pet;
import model.Shelter;
import service.BasketService;
import service.BenefiterService;
import service.PetService;
import service.ShelterService;

public class TestClass {


    public static void main(String[] args)
    {

        BenefiterService benefiterService = new BenefiterService();
        Benefiter savedbenefiter = null;

        Benefiter loginbenefiter = null;



        ShelterService shelterService = new ShelterService();
        
        //shelterService.updateCapital((long)1, (float)10);
        PetService ps = new PetService();
        

      //  ps.updateHealth("sonia", "newHealth");
       Pet p = ps.savePet("fromTest", "age", "info", "health", "animal");
        System.out.println(p);
        
        
       // Shelter sh = shelterService.saveShelter((long)0,(float)0,"bla");

       // System.out.println(sh);

        //savedbenefiter = benefiterService.saveBenefiter("worker@at.com","worker",(long)1);

        //System.out.println(savedbenefiter.toString());

        BasketService basketService = new BasketService();
        

        List<Basket> baskets = basketService.findAll();
        
        for(Basket b:baskets)
        {
        	System.out.println(b);
        }
        
      

    }
}
