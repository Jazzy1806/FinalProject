package com.skilldistillery.treattracker.services;

import com.skilldistillery.treattracker.entities.Address;


public interface AddressService {

    public Address getAddress(String username, int addressId);

    public Address addAddress(String username, Address address);

    public Address updateAddress(String username, int addressId, Address address);

//    public boolean deleteAddress(String username, int addressId);
}
