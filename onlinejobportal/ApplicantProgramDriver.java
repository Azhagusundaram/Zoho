package onlinejobportal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ApplicantProgramDriver {
    JobManagement cache=JobManagement.getInstance();
    private int applicantId =0;

    public int addApplicant(Applicant applicant){
        applicantId++;
        applicant.setApplicantId(applicantId);
        cache.addApplicantDetails(applicant);
        return applicantId;
    }
    public boolean applyJob(int jobId, int applicantId){
        if(cache.getJobDetails().containsKey(jobId)){
            Applicant applicant=cache.getApplicantDetails().get(applicantId);
            Job job=cache.getJobDetails().get(jobId);
            int companyId=job.getCompanyId();
            Company company=cache.getCompanyDetails().get(companyId);
            boolean apply=company.addApplicantList(applicantId,jobId);
            applicant.applyJob(companyId,jobId);
            return apply;
        }else {
            return false;
        }

    }
    public List<Job> getJobs(){
        List<Job>jobs=new ArrayList<>();
        Set<Integer> jobIds=cache.getJobDetails().keySet();
        for(Integer jobId:jobIds){
            Job job=cache.getJobDetails().get(jobId);
            jobs.add(job);
        }
        return jobs ;
    }
    public String getCompanyDetails(int companyId){
        Company company =cache.getCompanyDetails().get(companyId);
        return company.getCompanyDetails();
    }
    public Map<Integer, List<Integer>> getSelectedJobs(Integer applicantId){
        Applicant applicant=cache.getApplicantDetails().get(applicantId);
        return applicant.getSelectedJobs();
    }
    public boolean checkCredentials(int applicantId,String password){
        Applicant applicant=cache.getApplicantDetails().get(applicantId);

        if(applicant==null){
            return false;
        }
        String oldPassword=applicant.getPassword();
        return password.equals(oldPassword);
    }
    public Map<Integer, List<Integer>> getAppliedJobs(int applicantId){
        Applicant applicant=cache.getApplicantDetails().get(applicantId);
        return applicant.getAppliedJobs();
    }
}
