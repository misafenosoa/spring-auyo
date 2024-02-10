package AutoOccazMarket.AutoOccazMarket.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import AutoOccazMarket.AutoOccazMarket.dto.CarburantDTO;
import AutoOccazMarket.AutoOccazMarket.entities.Carburant;
import AutoOccazMarket.AutoOccazMarket.services.CRUDCarburant;



@RestController
@CrossOrigin(origins = "${frontend.url}")
public class CarburantController 
{

    @Autowired
    private CRUDCarburant crudCarburant ;

    @Autowired
    private CarburantDTO carburantsDTO ;


    @GetMapping(path = "/carburants")
    public CarburantDTO getCarburants()
    {
        List<Carburant> carburants = crudCarburant.getCarburantList() ;
        carburantsDTO.setCarburantAsList(carburants);
        return carburantsDTO ;
    }

    @GetMapping(path = "/carburants/{id}")
    public CarburantDTO getCarburantsById(@PathVariable("id") final Integer id)
    {
        Carburant carburants = crudCarburant.getCarburantByID(Integer.valueOf(id)) ;
        carburantsDTO.setCarburant(carburants) ;

        return carburantsDTO ;
    }

    @PostMapping(path = "/carburants")
    public CarburantDTO saveCarburant(@RequestBody CarburantDTO carburantsDTO)
    {
        try {
            crudCarburant.postCarburant(carburantsDTO.getCarburant());
            
        } catch (Exception e) {
            carburantsDTO.setErrors(e.getMessage());
        }

        return carburantsDTO;

    }    

    @PutMapping(path ="/carburants/{id}")
    public CarburantDTO updateCarburant(@PathVariable("id") final Integer id , @RequestBody CarburantDTO carburantsDTO)
    {
        try {
            crudCarburant.updateCarburant(id, carburantsDTO.getCarburant());
            
        } catch (Exception e) {
            carburantsDTO.setErrors(e.getMessage());
        }

        return carburantsDTO;
    }

    @DeleteMapping(path = "/carburants/{id}")
    public void deleteCarburant(@PathVariable("id") final Integer id)
    {
        crudCarburant.deleteCarburantByID(id);
    }    

    @GetMapping(path = "/carburants/{offset}/{pageSize}")
    public CarburantDTO getCarburants(@PathVariable("offset") Integer offset, @PathVariable("pageSize") Integer pageSize) {
        CarburantDTO carburantDTO = new CarburantDTO();
        
        try {
             Page<Carburant> c=crudCarburant.findCarburantsWithPagination(offset, pageSize);
            carburantDTO.setCarburantAsList(c.toList());
            carburantDTO.setPage(c.getTotalPages());
            
        } catch (Exception e) {
            carburantDTO.setErrors(e.getMessage());
        }
        
        return carburantDTO;
    }
    
}
