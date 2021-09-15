package onlinejobportal;

import java.util.*;

public class JobManagement {
    private Map<String, List<String>>allJobs=new HashMap<>();
    private Map<String,Company>companyDetails=new HashMap<>();
    private Map<String,Applicant>applicantDetails=new HashMap<>();
    private static JobManagement cache;
    private JobManagement(){

    }
    public static JobManagement getInstance(){
        if(cache==null){
            cache=new JobManagement();
        }
       return cache;
    }
    public void addApplicantDetails(Applicant applicant){
        String applicantName=applicant.getName();

        applicantDetails.put(applicantName,applicant);

    }
    public void addCompanyDetails(Company company){
        String companyName=company.getName();
        companyDetails.put(companyName,company);
    }
    public Map<String, List<String>> getAllJobs(){
        return allJobs;
    }
    public void setAllJobs(String jobName,String companyName){
        List<String>companyNames=allJobs.get(jobName);
        if(companyNames==null){
            companyNames=new ArrayList<>();
            allJobs.put(jobName,companyNames);
        }
        companyNames.remove(companyName);
        companyNames.add(companyName);
    }
    public Map<String, Company> getCompanyDetails(){
        return companyDetails;
    }
    public Map<String, Applicant> getApplicantDetails(){
        return applicantDetails;
    }
}
