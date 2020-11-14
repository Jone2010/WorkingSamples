package com.example.ib;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ib.service.EWrapperImpl;

@RestController
public class InteractiveBrokerApiController {
	
	@GetMapping("/getIBMarketData")
	public List<IBMarketData> getIBMarketData() {
		 try
	        {
			  EWrapperImpl eWrapperImpl = new EWrapperImpl();
			  eWrapperImpl.requestMarketDataFromTWS();
			  return eWrapperImpl.getIbMarketData();
			 
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace ();
	        }
		return null;
	}

}
