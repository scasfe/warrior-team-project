import static org.junit.Assert.assertNotNull;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.warriorteam.dto.NewsResultDTO;
import fr.warriorteam.server.service.NewsService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContext.xml"})
public class Test {

        @Autowired
        NewsService service;

        @org.junit.Test
        public void test() {
        	
        	System.out.println("****************************************");
        	System.out.println("****************************************");
        	System.out.println("****************************************");
        	System.out.println("****************************************");
        	System.out.println("Début du test");
        	System.out.println("****************************************");
        	System.out.println("****************************************");
        	System.out.println("****************************************");
        	System.out.println("****************************************");
        	
        	assertNotNull(service); 
            
        	
        	NewsResultDTO dto; 
        	
        	dto = service.processSuccess();
     
        	assertNotNull(dto.getStock()); 
        	
        
     
        	System.out.println("****************************************");
        	System.out.println("****************************************");
        	System.out.println("****************************************");
        	System.out.println("****************************************");
        	System.out.println("Fin du test");
        	System.out.println("****************************************");
        	System.out.println("****************************************");
        	System.out.println("****************************************");
        	System.out.println("****************************************");
        	
        	
            
        }

        
}
