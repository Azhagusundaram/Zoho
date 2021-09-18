package onlinejobportal;

import java.util.*;

public class JobManagement {
    private Map<Integer,Company>companyDetails=new HashMap<>();
    private Map<Integer,Applicant>applicantDetails=new HashMap<>();
    private Map<Integer,Job> jobDetails =new HashMap<>();
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
        int applicantId=applicant.getApplicantId();
        applicantDetails.put(applicantId,applicant);

    }
    public void addCompanyDetails(Company company){
        int companyId=company.getCompanyId();
        companyDetails.put(companyId,company);
    }
    public void setJobDetails(Job job){
        int jobId=job.getJobId();
        jobDetails.put(jobId,job);
    }
    public Map<Integer, Job> getJobDetails(){
        return jobDetails;
    }

    public Map<Integer, Company> getCompanyDetails(){
        return companyDetails;
    }
    public Map<Integer, Applicant> getApplicantDetails(){
        return applicantDetails;
    }
}
