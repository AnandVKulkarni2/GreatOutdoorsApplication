package com.cg.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cg.entities.Product;
import com.cg.exception.ProductNotPresentException;

@ExtendWith({SpringExtension.class})
@DataJpaTest
@Import(ProductServiceImpl.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductServiceImplTest {
	
	@Autowired
	private ProductService pService;

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
	void testFindProduct() {
		Product prod= new Product(1000, "Golf", 10, 100000, "Golf Equipments");
		Product saveProd= pService.addProduct(prod);
		Product prod1=pService.findProduct(1000);
		assertEquals(prod1.getQuantity(), prod.getQuantity());
		
	}

	@Test
	void testAddProduct() {
		Product prod= new Product(1000, "Golf", 10, 100000, "Golf Equipments");
		Product saveProd= pService.addProduct(prod);
		assertEquals(saveProd.getProdName(),prod.getProdName());
	}

	@Test
	void testUpdateProduct() {
		Product prod= new Product(1000, "Golf", 10, 100000, "Golf Equipments");
		Product saveProd= pService.addProduct(prod);
		Product prod1= new Product(1000, "Cricket", 10, 100000, "Golf Equipments");
		Product prod2= pService.updateProduct(prod1);
		assertEquals(prod2.getProdName(),prod1.getProdName());
	}

	@Test
	void testDeleteProduct() {
		Product prod= new Product(1000, "Golf", 10, 100000, "Golf Equipments");
		Product saveProd= pService.addProduct(prod);
		Product prod1= pService.deleteProduct(1000);
//		Product prod2=pService.findProduct(1000);
		Exception ex= assertThrows(ProductNotPresentException.class, ()->{pService.findProduct(1000);} );
	}

}
