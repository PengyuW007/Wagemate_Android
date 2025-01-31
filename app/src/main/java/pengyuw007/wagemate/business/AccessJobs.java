package pengyuw007.wagemate.business;

import java.util.List;

import pengyuw007.wagemate.application.Main;
import pengyuw007.wagemate.application.Services;
import pengyuw007.wagemate.objects.Job;
import pengyuw007.wagemate.persistence.IPersistenceAccess;

public class AccessJobs {
    private IPersistenceAccess dataAccess;

    public AccessJobs(){
        dataAccess = Services.getDataAccess(Main.dbName);
    }

    public List<Job> getJobs(){
        return dataAccess.getJobs();
    }
    public String addJob(String url,String pos){
        return dataAccess.addJob(new Job(url,pos));
    }

    public boolean deleteJob(Job job){
        return dataAccess.deleteJob(job.getURL());
    }

    public Job getJobByURL(String url){
        return dataAccess.getJobByURL(url);
    }

    public String updateJobPosition(String url,String position){
        return dataAccess.rePosition(url, position);
    }

    public String updateJobURL(String url,String newURL){
        return dataAccess.reURL(url, newURL);
    }

    public void clearJobs(){
        dataAccess.clearJobs();
    }
}
