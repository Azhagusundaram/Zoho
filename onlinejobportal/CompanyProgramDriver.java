package onlinejobportal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CompanyProgramDriver {
    JobManagement cache=JobManagement.getInstance();
    int companyId=0;
    public int addCompany(Company company){
        companyId++;
        company.setCompanyId(companyId);
        cache.addCompanyDetails(company);
        return companyId;
    }
    public void addJob(Job job,String companyName){
        Company company=cache.getCompanyDetails().get(companyName);
        String jobName= job.getJobName();
        cache.setAllJobs(jobName,companyName);
        company.addVacancyJobs(jobName,job);
    }
    public List<String> getApplicationList(String companyName){

        List<String>applicants=new ArrayList<>();
        Company company=cache.getCompanyDetails().get(companyName);
        Set<String>applicants1=company.getAppliedList().keySet();
        int i=1;
        for(String applicant:applicants1){
            applicants.add(i+"."+applicant);
            i++;
        }
        return applicants;
    }
    public List<String>getJobLists(String companyName,String applicantName){
        List<String>jobLists=new ArrayList<>();
        Company company=cache.getCompanyDetails().get(companyName);
        List<String>jobs=company.getAppliedList().get(applicantName);
        int i=1;
        for(String job:jobs){
            jobLists.add(i+"."+job);
            i++;
        }
        return jobLists;
    }
    public String selectApplicant(String applicantName,String jobName,String companyName){
        Company company=cache.getCompanyDetails().get(companyName);
        Map<String,Applicant>applicantDetails= cache.getApplicantDetails();
        Applicant applicant=applicantDetails.get(applicantName);
        Job job=company.getJobDetails(jobName);
        company.setSelectedList(applicantName,job);
        applicant.addSelectedJobs(companyName,job);
        return "Applicant Selected";
    }
    public Map<String, List<Job>> getSelectedApplicants(String  companyName){
        Company company=cache.getCompanyDetails().get(companyName);
        return company.getSelectedList();
    }
    public boolean checkCredentials(String name,String password){
        Company company=cache.getCompanyDetails().get(name);
        if(company==null){
            return false;
        }
        String oldPassword=company.getPassword();
        if(password.equals(oldPassword)){
            return true;
        }
        return false;
    }
}
