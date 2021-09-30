package com.cg.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cg.entities.Address;
import com.cg.entities.Retailer;
import com.cg.exception.RetailerNotFoundException;

@ExtendWith({SpringExtension.class})
@DataJpaTest
@Import(RetailServiceImpl.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RetailServiceImplTest {
	
	@Autowired
	private RetailService rService;
	
	
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testAddAddress() {
		Address addr= new Address(12567, "xyz", "mno", "pqr", 1111111111);
		Address saveAddr= rService.addAddress(addr);
		assertEquals(saveAddr.getCity(), addr.getCity());
	}

	@Test
	void testUpdateAddress() {
		Address addr= new Address(12567, "xyz", "mno", "pqr", 1111111111);
		Address saveAddr= rService.addAddress(addr);
		Address addr1= new Address(12568, "abc", "qwe", "cvb", 1111111111);
		Address addr2= rService.updateAddress(addr1);
		assertEquals(addr2.getCity(), addr1.getCity());

	}

	@Test
	void testFindRetailer() {
		Retailer ret= new Retailer(1009, "Piyush's Store", new Address(12567, "xyz", "mno", "pqr", 1111111111));
		Retailer saveRet= rService.addRetailer(ret);
		Retailer ret2= rService.findRetailer(1009);
		assertEquals(ret2.getRetailerName(), ret.getRetailerName());
	}

	@Test
	void testAddRetailer() {
		Retailer ret= new Retailer(1009, "Piyush's Store", new Address(12567, "xyz", "mno", "pqr", 1111111111));
		Retailer saveRet= rService.addRetailer(ret);
		Assertions.assertEquals(saveRet.getRetailerName(), ret.getRetailerName());
	}

	@Test
	void testDeleteRetailer() {
		Retailer ret= new Retailer(1009, "Piyush's Store", new Address(12567, "xyz", "mno", "pqr", 1111111111));
		Retailer saveRet= rService.addRetailer(ret);
		Retailer ret1= rService.deleteRetailer(1009);
		Exception ex= assertThrows(RetailerNotFoundException.class, ()->{rService.findRetailer(1009);});
	}

	@Test
	void testUpdateRetailer() {
		Retailer ret= new Retailer(1009, "Piyush's Store", new Address(12567, "xyz", "mno", "pqr", 1111111111));
		Retailer saveRet= rService.addRetailer(ret);
		Retailer ret1= new Retailer(1009, "Mayur's Store", new Address(12345, "abc", "xyz", "mno", 1111111119));
		Retailer ret2= rService.updateRetailer(ret1);
		assertEquals(ret2.getRetailerName(), ret1.getRetailerName());
	}

}
