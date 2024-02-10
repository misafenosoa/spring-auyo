package AutoOccazMarket.AutoOccazMarket.dto;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import AutoOccazMarket.AutoOccazMarket.entities.Commission;


@Component
public class CommissionDTO {
    Commission[] listCommission;

    Commission commission;

    String errors;

    Map<String, String> tokenInformation;

    public Map<String, String> getTokenInformation() {
        return tokenInformation;
    }

    public void setTokenInformation(Map<String, String> tokenInformation) {
        this.tokenInformation = tokenInformation;
    }

    public void setCommissionAsList(List<Commission> list) {
        listCommission = list.toArray(new Commission[list.size()]);
    }

    public Commission[] getListCommission() {
        return listCommission;
    }

    public void setListCommission(Commission[] listCommission) {
        this.listCommission = listCommission;
    }

    public Commission getCommission() {
        return commission;
    }

    public void setCommission(Commission commission) {
        this.commission = commission;
    }

    public String getErrors() {
        return errors;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }

}
