package com.cybertek;

import com.cybertek.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.converter.json.GsonBuilderUtils;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class DerivedqueryApplication {

	@Autowired
	RegionRepository regionRepository;

	public static void main(String[] args) {
		SpringApplication.run(DerivedqueryApplication.class, args);
	}

	@PostConstruct
	public void testRegions(){

		System.out.println("---------Regions start------------");

		System.out.println("findByCountry: " + regionRepository.findByCountry("Canada"));
		System.out.println("findDistinctByCountry: " + regionRepository.findDistinctByCountry("Canada"));
		System.out.println("findDistinctByCountry: " + regionRepository.findByCountryContaining("United"));









		System.out.println("---------Regions End------------");

	}

}
