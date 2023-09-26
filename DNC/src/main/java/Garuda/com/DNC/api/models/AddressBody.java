package Garuda.com.DNC.api.models;

import Garuda.com.DNC.Model.Vendor;

public class AddressBody {



    private String adress;
    private String pincode;
    private String state;
    private String country;

    public String getAdress() {
        return adress;
    }

    public String getPincode() {
        return pincode;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getMobile() {
        return mobile;
    }

    private String city;
    private String mobile;

}
