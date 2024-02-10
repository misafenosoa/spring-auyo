package AutoOccazMarket.AutoOccazMarket.dto;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import AutoOccazMarket.AutoOccazMarket.entities.Carburant;

@Component

public class CarburantDTO 
{
    Carburant [] listCarburant ;    

    Carburant carburant ;

    String errors ;

    Map<String, String> tokenInformation;

    private int page;

    public Map<String, String> getTokenInformation() {
        return tokenInformation;
    }

    public void setTokenInformation(Map<String, String> tokenInformation) {
        this.tokenInformation = tokenInformation;
    }
    
    public void setCarburantAsList(List<Carburant> list)
    {
        listCarburant = list.toArray(new Carburant[list.size()]);
    }

    public Carburant[] getListCarburant() {
        return listCarburant;
    }

    public void setListCarburant(Carburant[] listCarburant) {
        this.listCarburant = listCarburant;
    }

    public Carburant getCarburant() {
        return carburant;
    }

    public void setCarburant(Carburant carburant) {
        this.carburant = carburant;
    }

    public String getErrors() {
        return errors;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }

    public void setPage(int totalPages) {
        this.page=totalPages;
    }

    public int getPage() {
        return page;
    }

}
