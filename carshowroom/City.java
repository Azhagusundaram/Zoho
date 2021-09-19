package carshowroom;


import java.util.HashMap;
import java.util.Map;

public class City {
    private int cityId;
    private String cityName;
    private Map<Integer,Branch> branches=new HashMap<>();

    public Map<Integer, Branch> getBranches() {
        return branches;
    }
    public void setBranch(Branch branch){
        int branchId=branch.getBranchId();
        branches.put(branchId,branch);
    }
    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
    public String toString(){
        return "\nCity Id : "+cityId+"\tCity Name : "+cityName;
    }
}

//3
//Chennai
//3
//tambaram
//chengalpattu
//vandalur
//madurai
//2
//simmakal
//arapalayam
//karaikudi
//2
//kottaiyur
//managiri
