package AutoOccazMarket.AutoOccazMarket.dto;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import AutoOccazMarket.AutoOccazMarket.entities.ModelesStats;



@Component
public class ModelesStatsDTO 
{
    ModelesStats [] listModelesStats ;    

    ModelesStats ModelesStats ;

    String errors ;

    Map<String, String> tokenInformation;

    public Map<String, String> getTokenInformation() {
        return tokenInformation;
    }

    public void setTokenInformation(Map<String, String> tokenInformation) {
        this.tokenInformation = tokenInformation;
    }

    public void setModelesStatsAsList(List<ModelesStats> list)
    {
        listModelesStats = list.toArray(new ModelesStats[list.size()]);
    }

    public ModelesStats[] getListModelesStats() {
        return listModelesStats;
    }

    public void setListModelesStats(ModelesStats[] listModelesStats) {
        this.listModelesStats = listModelesStats;
    }

    public ModelesStats getModelesStats() {
        return ModelesStats;
    }

    public void setModelesStats(ModelesStats ModelesStats) {
        this.ModelesStats = ModelesStats;
    }

    public String getErrors() {
        return errors;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }

}

