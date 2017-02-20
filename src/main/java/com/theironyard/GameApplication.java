package com.theironyard;

import com.theironyard.models.Item;
import com.theironyard.models.Room;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

@SpringBootApplication
public class GameApplication {
//    ArrayList<Item> items = new ArrayList<>();
//           items.add()
    private final Log logger = LogFactory.getLog(GameApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(GameApplication.class, args);
	}
}
