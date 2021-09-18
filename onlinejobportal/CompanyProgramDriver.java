package onlinejobportal;

import java.util.*;

public class CompanyProgramDriver {
    JobManagement cache=JobManagement.getInstance();
    int companyId=0;
    int jobId=1;
    public int addCompany(Company company){
        companyId++;
        company.setCompanyId(companyId);
        cache.addCompanyDetails(company);
        return companyId;
    }
    public void addJob(Job job,int companyId){
        job.setJobId(jobId);
        job.setCompanyId(companyId);
        Company company=cache.getCompanyDetails().get(companyId);
        cache.setJobDetails(job);
        company.addVacancyJob(jobId);
        jobId++;
    }
    public Map<Applicant, List<Job>> getApplicationList(int  companyId) {

        Map<Applicant, List<Job>> applicantList = new HashMap<>();
        Company company = cache.getCompanyDetails().get(companyId);
        Map<Integer, List<Integer>> applicantLists = company.getAppliedList();
        Set<Integer> applicantIds = applicantLists.keySet();
        for (int applicantId : applicantIds) {
            Applicant applicant = cache.getApplicantDetails().get(applicantId);
            List<Integer> applyJobIds = applicantLists.get(applicantId);
            List<Job> applyJobs = new ArrayList<>();
            for (int applyJobId : applyJobIds) {
                Job job = cache.getJobDetails().get(applyJobId);
                applyJobs.add(job);
            }
            applicantList.put(applicant, applyJobs);
        }
        return applicantList;
    }
    public boolean selectApplicant(int  applicantId, int jobId, int companyId){
        Company company=cache.getCompanyDetails().get(companyId);
        Map<Integer,Applicant>applicantDetails= cache.getApplicantDetails();
        Applicant applicant=applicantDetails.get(applicantId);
        if(applicant!=null&&cache.getJobDetails().containsKey(jobId)){
            boolean select=company.setSelectedList(applicantId,jobId);
            company.removeApplicantList(applicantId,jobId);
            applicant.addSelectedJobs(companyId,jobId);
            return select;
        }else {
            return false;
        }

    }
    public Map<Integer, List<Integer>> getSelectedApplicants(int  companyId){
        Company company=cache.getCompanyDetails().get(companyId);
        return company.getSelectedList();
    }
    public boolean checkCredentials(int companyId,String password){
        Company company=cache.getCompanyDetails().get(companyId);
        if(company==null){
            return false;
        }
        String oldPassword=company.getPassword();
        return password.equals(oldPassword);
    }
}
