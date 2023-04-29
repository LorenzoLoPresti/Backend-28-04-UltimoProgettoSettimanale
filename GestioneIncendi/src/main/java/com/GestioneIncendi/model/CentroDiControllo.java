package com.GestioneIncendi.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CentroDiControllo implements Allarme {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	@OneToMany(fetch = FetchType.EAGER)
	@JsonIgnoreProperties
	List<GestoreSonde> listaGestoriAssociati = new ArrayList<GestoreSonde>();

	@Override
	public String toString() {
		return "CentroDiControllo [id=" + id + "]";
	}

	@Override
	public String notifica() {
		// TODO Auto-generated method stub
		return "Allerta personale, pericolo incendio";
	}

	public String AllertaPersonale(String url) {
		return url;
	}

}
