package guru.learningjournal.examples.kafka.jsonposgen.services.datagenerator;

import java.io.File;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import guru.learningjournal.examples.kafka.jsonposgen.model.LineItem;

@Service
public class ProductGenerator {

	private final Random random;

	private final Random qty;

	private final LineItem[] products;

	public ProductGenerator() {
		String DATAFILE = "src/main/resources/data/products.json";

		ObjectMapper objectMapper = new ObjectMapper();

		this.random = new Random();

		this.qty = new Random();

		try {
			// this.products =
			// objectMapper.readValue(ProductGenerator.class.getResourceAsStream(DATAFILE),
			// LineItem[].class);
			this.products = objectMapper.readValue(new File(DATAFILE), LineItem[].class);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private int getIndex() {
		return this.random.nextInt(100);
	}

	private int getQuantity() {
		return this.qty.nextInt(2) + 1;
	}

	public LineItem getNextProduct() {
		LineItem lineItem = this.products[getIndex()];
		lineItem.setItemQty(getQuantity());
		lineItem.setTotalValue(lineItem.getItemPrice() + lineItem.getItemQty());
		return lineItem;
	}

}
