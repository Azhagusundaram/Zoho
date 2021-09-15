package onlinejobportal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ApplicantProgramDriver {
    JobManagement cache=JobManagement.getInstance();
    private int applicantNumber=0;

    public int addApplicant(Applicant applicant){
        applicantNumber++;
        applicant.setApplicantNumber(applicantNumber);
        cache.addApplicantDetails(applicant);
        return applicantNumber;
    }
    public String applyJob(String jobName, String companyName, String name){
        Applicant applicant=cache.getApplicantDetails().get(name);
        Map<String,Company> companyDetails= cache.getCompanyDetails();
        Company company=companyDetails.get(companyName);
        company.addApplicantList(name,jobName);
        Job job=company.getJobDetails(jobName);
        applicant.applyJob(companyName,job);
        return "Applied Successfully";
    }
    public List<String> getJobs(){
        List<String>jobNames=new ArrayList<>();
        Set<String> allJobs=cache.getAllJobs().keySet();
        int i=1;
        for(String jobName:allJobs){
            jobNames.add(i+"."+jobName);
            i++;
        }
        return jobNames;
    }
    public List<String> getCompany(String jobName){
        List<String>companyNames=new ArrayList<>();
        List<String> allJobs=cache.getAllJobs().get(jobName);
        int i=1;
        for(String companyName :allJobs){
            companyNames.add(i+"."+ companyName);
            i++;
        }
        return companyNames;
    }
    public Job getJobDetails(String jobName, String companyName){
        Map<String,Company> companyDetails= cache.getCompanyDetails();
        Company company=companyDetails.get(companyName);
        Job job=company.getJobDetails(jobName);
        return job;
    }
    public Map<String, List<Job>> getSelectedJobs(String name){
        Applicant applicant=cache.getApplicantDetails().get(name);
        return applicant.getSelectedJobs();
    }
    public boolean checkCredentials(String name,String password){
        Applicant applicant=cache.getApplicantDetails().get(name);

        if(applicant==null){
            return false;
        }
        String oldPassword=applicant.getPassword();
        if(password.equals(oldPassword)){
            return true;
        }
        return false;
    }
    public Map<String, List<Job>> getAppliedJobs(String name){
        Applicant applicant=cache.getApplicantDetails().get(name);
        return applicant.getAppliedJobs();
    }
}
