package onlinejobportal;

public class Job {
    private int jobId;
    private String jobName;
    private int vacancy;
    private String eligibilityCriteria;
    private int companyId;

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public int getVacancy() {
        return vacancy;
    }

    public void setVacancy(int vacancy) {
        this.vacancy = vacancy;
    }

    public String getEligibilityCriteria() {
        return eligibilityCriteria;
    }

    public void setEligibilityCriteria(String eligibilityCriteria) {
        this.eligibilityCriteria = eligibilityCriteria;
    }
    @Override
    public String toString(){
        return "JobName : "+jobName+"\tVacancy : "+vacancy+"\t Eligibility Criteria : "+eligibilityCriteria+"\tCompany Id :"+companyId+"\tJob Id : "+jobId;
    }
}
