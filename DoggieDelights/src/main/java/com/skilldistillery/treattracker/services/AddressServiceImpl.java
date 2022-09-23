package com.skilldistillery.treattracker.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.treattracker.entities.Address;
import com.skilldistillery.treattracker.entities.User;
import com.skilldistillery.treattracker.repositories.AddressRepository;
import com.skilldistillery.treattracker.repositories.UserRepository;

@Service
public class AddressServiceImpl implements AddressService{
	
	@Autowired
	private AddressRepository addRepo;

	@Autowired
	private UserRepository userRepo;

	@Override
	public Address getAddress(String username, int addressId) {
		Optional<Address> result = addRepo.findById(addressId);
		if (result.isPresent()) {
			Address address= result.get();
			if (address.getUser().getUsername().equals(username)) {
				return address;
			}
		}
		return null;
	}

	@Override
	public Address addAddress(String username, Address address) {
		  User user = userRepo.findByUsername(username);
		  if (user != null) {
			  address.setUser(user);
		    return addRepo.saveAndFlush(address);
		  }
		  return null;
	}

	@Override
	public Address updateAddress(String username, int addressId, Address address) {
		Optional<Address> result = addRepo.findById(addressId);
		if (result.isPresent()) {
			Address updatedAdd= result.get();
			if (updatedAdd.getUser().getUsername().equals(username)) {
				updatedAdd.setAddress(address.getAddress());
				updatedAdd.setCity(address.getCity());
				updatedAdd.setState(address.getState());
				updatedAdd.setPostalCode(address.getPostalCode());
				updatedAdd.setPhone(address.getPhone());
				return addRepo.saveAndFlush(updatedAdd);
			}
		}
		return null;
	}

//	@Override
//	public boolean deleteAddress(String username, int petId) {
//		Optional<Address> result = addRepo.findById(petId);
//		if (result.isPresent()) {
//			Address address= result.get();
//			if (address.getUser().getUsername().equals(username)) {
//				addRepo.delete(address);
//				return true;
//			}
//		}
//		return false;
//	}

}
