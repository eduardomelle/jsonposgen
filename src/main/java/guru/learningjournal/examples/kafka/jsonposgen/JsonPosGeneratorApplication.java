package guru.learningjournal.examples.kafka.jsonposgen;

import guru.learningjournal.examples.kafka.jsonposgen.services.KafkaProducerService;
import guru.learningjournal.examples.kafka.jsonposgen.services.datagenerator.InvoiceGenerator;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Log4j2
public class JsonPosGeneratorApplication implements ApplicationRunner {

	@Value("${application.configs.invoice.count}")
	private int INVOICE_COUNT;

	@Autowired
	private KafkaProducerService producerService;

	@Autowired
	private InvoiceGenerator invoiceGenerator;

	public static void main(String[] args) {
		SpringApplication.run(JsonPosGeneratorApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		for (int i = 0; i < INVOICE_COUNT; i++) {
			this.producerService.sendMessage(this.invoiceGenerator.getNextInvoice());
			Thread.sleep(1000);
		}
	}

}
