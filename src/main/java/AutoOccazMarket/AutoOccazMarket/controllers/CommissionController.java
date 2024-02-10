package AutoOccazMarket.AutoOccazMarket.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import AutoOccazMarket.AutoOccazMarket.dto.CommissionDTO;
import AutoOccazMarket.AutoOccazMarket.entities.Commission;
import AutoOccazMarket.AutoOccazMarket.services.CRUDCommission;


@RestController
@CrossOrigin(origins = "${frontend.url}")
public class CommissionController 
{

    @Autowired
    private CRUDCommission crudCommission ;

    @Autowired
    private CommissionDTO commissionsDTO ;


    @GetMapping(path = "/commissions")
    public CommissionDTO getCommissions()
    {
        List<Commission> commissions = crudCommission.getCommissionList() ;
        commissionsDTO.setCommissionAsList(commissions);
        return commissionsDTO ;
    }

    @GetMapping(path = "/commissions/{id}")
    public CommissionDTO getCommissionsById(@PathVariable("id") final Integer id)
    {
        Commission commissions = crudCommission.getCommissionByID(Integer.valueOf(id)) ;
        commissionsDTO.setCommission(commissions) ;

        return commissionsDTO ;
    }

    @PostMapping(path = "/commissions")
    public CommissionDTO saveCommission(@RequestBody CommissionDTO commissionsDTO)
    {
        try {
            crudCommission.postCommission(commissionsDTO.getCommission());
            
        } catch (Exception e) {
            commissionsDTO.setErrors(e.getMessage());
        }

        return commissionsDTO;

    }    

    @PutMapping(path ="/commissions/{id}")
    public CommissionDTO updateCommission(@PathVariable("id") final Integer id , @RequestBody CommissionDTO commissionsDTO)
    {
        crudCommission.updateCommission(id, commissionsDTO.getCommission());

        return commissionsDTO;
    }

    @DeleteMapping(path = "/commissions/{id}")
    public void deleteCommission(@PathVariable("id") final Integer id)
    {
        crudCommission.deleteCommissionByID(id);
    }    
}
