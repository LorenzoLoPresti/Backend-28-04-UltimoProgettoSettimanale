package com.GestioneIncendi.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "gestori_sonde")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class GestoreSonde implements Allarme{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	@Column(nullable = false)
	String citta;
	@Column(nullable = false)
	boolean allarme;
	@Column
	String messaggioAllarme;
	@OneToMany(fetch = FetchType.EAGER)
	@Builder.Default
	@JsonIgnoreProperties
	List<Sonda> listaSondeAssociate = new ArrayList<Sonda>();
	@ManyToOne
	CentroDiControllo centroDiControllo;
	
	@Override
	public String notifica() {
		this.setAllarme(true);
		String allarme = "Pericolo incendio, segnalazione inviata al centro di controllo";
		System.out.println(allarme);
		return allarme;
	}

	@Override
	public String toString() {
		return "GestoreSonde [id=" + id + ", citta=" + citta + "]";
	}
	

}
