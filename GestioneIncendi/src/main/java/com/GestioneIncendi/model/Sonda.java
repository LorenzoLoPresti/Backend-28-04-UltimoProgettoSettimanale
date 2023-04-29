package com.GestioneIncendi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sonde")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Sonda implements Allarme{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	@Column(nullable = false)
	String latitudine;
	@Column(nullable = false)
	String longitudine;
	@Column(nullable = false)
	Integer livelloFumo;
	@ManyToOne
	GestoreSonde gestoreSondeAssociato;
	
	@Override
	public String notifica() {
		if(this.livelloFumo > 5) {
			return "http://host/alarm?=idsonda=" + this.getId()
			+ "&lat=" + this.getLatitudine() + "&lon=" + this.getLongitudine()
			+ "&smokelevel=" + this.getLivelloFumo();
		}
		return null;
		// TODO Auto-generated method stub
		
	}
	

}
