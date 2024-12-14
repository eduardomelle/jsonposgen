package guru.learningjournal.examples.kafka.jsonposgen.services.datagenerator;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.learningjournal.examples.kafka.jsonposgen.model.DeliveryAddress;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Random;

@Service
public class InvoiceGenerator {

    private final Random random;

    private final DeliveryAddress[] addresses;

    public InvoiceGenerator() {
        this.random = new Random();

        final ObjectMapper objectMapper = new ObjectMapper();

        final String DATAFILE = "src/main/resources/addresses.json";

        try {
            this.addresses = objectMapper.readValue(new File(DATAFILE), DeliveryAddress[].class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private int getIndex() {
        return this.random.nextInt(100);
    }

    public DeliveryAddress getNextAddress() {
        return this.addresses[getIndex()];
    }

}
