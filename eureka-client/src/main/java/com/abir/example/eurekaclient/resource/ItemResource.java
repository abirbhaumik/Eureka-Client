package com.abir.example.eurekaclient.resource;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.abir.example.eurekaclient.model.ItemDetails;


@RestController
@RequestMapping("/api/item")
public class ItemResource {
	
	@Autowired
	RestTemplate restTemplate;
	@GetMapping("/details/{itemID}")
	public ItemDetails getItemDetails(@PathVariable String itemID) throws UnknownHostException {
		ItemDetails it = new ItemDetails();
	//	RestTemplate restTemplate = new RestTemplate();
		Map<String,String> uriVariables = new HashMap<>();
		uriVariables.put("item", itemID);
	//	ResponseEntity<Integer> responseEntity=restTemplate.getForEntity("http://localhost:8081/api/inventory/count/{item}", Integer.class, uriVariables);
		ResponseEntity<Integer> responseEntity=restTemplate.getForEntity("http://item-inventory/api/inventory/count/{item}", Integer.class, uriVariables);
		int count =responseEntity.getBody();

		System.out.println("Input value is...>>>" + itemID);
		if("100".equals(itemID)) {
			System.out.println("Input value is...>>>" + itemID);
			it.setItemCategory("Household");
			it.setItemName("Shampoo");
			it.setAvailableCount(count);
			it.setPort(InetAddress.getLocalHost().getHostName());
		}
		else if ("200".equals(itemID)) {
			it.setItemCategory("Food");
			it.setItemName("Rice");
			it.setAvailableCount(count);
			it.setPort(InetAddress.getLocalHost().getHostName());
		}
		return it;
	}

}
