package com.GestioneIncendi.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.GestioneIncendi.model.Allarme;
import com.GestioneIncendi.model.GestoreSonde;
import com.GestioneIncendi.model.Sonda;

@Configuration
public class SondeFactory {

	@Bean
	@Scope("prototype")
	public Allarme creaDispositivo(String tipo) {
		if (tipo == null) {
			return null;
		}
		if(tipo.equalsIgnoreCase("sonda")) {
			return new Sonda();
		} else if(tipo.equalsIgnoreCase("gestore")){
			return new GestoreSonde();
		}
		
		return null;
	}
	
}
