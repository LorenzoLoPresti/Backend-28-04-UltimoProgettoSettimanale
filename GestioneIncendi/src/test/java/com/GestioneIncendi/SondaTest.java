package com.GestioneIncendi;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.GestioneIncendi.model.Allarme;
import com.GestioneIncendi.model.CentroDiControllo;
import com.GestioneIncendi.model.GestoreSonde;
import com.GestioneIncendi.model.Sonda;


class SondaTest {

	@Test
	void testId() {
		assertEquals(creaSonda().getId(), 1l);
	}

	@Test
	void testRelazioni() {
		List<Allarme> list = creaRelazioni();
		Sonda sonda = (Sonda) list.get(0);
		GestoreSonde gestore = (GestoreSonde) list.get(1);
		CentroDiControllo centro = (CentroDiControllo) list.get(2);

		// RELAZIONE GESTORE-SONDA
		sonda.setGestoreSondeAssociato(gestore);
		gestore.getListaSondeAssociate().add(sonda);

		// RELAZIONE CONTROLLO-GESTORE
		centro.getListaGestoriAssociati().add(gestore);
		gestore.setCentroDiControllo(centro);

		assertEquals(sonda.getGestoreSondeAssociato(), gestore);
		assertEquals(gestore.getListaSondeAssociate().get(0), sonda);
		assertEquals(centro.getListaGestoriAssociati().get(0), gestore);
		assertEquals(gestore.getCentroDiControllo(), centro);

	}

	@Test
	void testControlloFumo() {
		List<Allarme> list = creaRelazioni();
		Sonda sonda = (Sonda) list.get(0);
		GestoreSonde gestore = (GestoreSonde) list.get(1);
		CentroDiControllo controllo = (CentroDiControllo) list.get(2);
		sonda.setLivelloFumo(6);
		
		if (sonda.getLivelloFumo() > 5) {
			gestore.setMessaggioAllarme(sonda.notifica());
			String msg = "http://host/alarm?=idsonda=" + sonda.getId()
			+ "&lat=" + sonda.getLatitudine() + "&lon=" + sonda.getLongitudine()
			+ "&smokelevel=" + sonda.getLivelloFumo();
			
			assertEquals(gestore.notifica(), "Pericolo incendio, segnalazione inviata al centro di controllo");
			assertEquals(controllo.notifica(), "Allerta personale, pericolo incendio");
			assertEquals(sonda.notifica(), msg);
			
		} else {
			assertEquals(sonda.getLivelloFumo() < 5, true);
		}
	}

	Sonda creaSonda() {
		Sonda sonda = new Sonda();
		sonda.setId(1l);
		sonda.setLatitudine("1111");
		sonda.setLongitudine("2222");
		sonda.setLivelloFumo(2);
		return sonda;
	}

	GestoreSonde creaGestore() {
		GestoreSonde gestore = new GestoreSonde();
		gestore.setCitta("Roma");
		return gestore;
	}

	CentroDiControllo creaControllo() {
		CentroDiControllo centro = new CentroDiControllo();
		centro.setId(1l);
		return centro;
	}

	List<Allarme> creaRelazioni() {
		Sonda sonda = creaSonda();
		GestoreSonde gestore = creaGestore();
		CentroDiControllo centro = creaControllo();

		// RELAZIONE GESTORE-SONDA
		sonda.setGestoreSondeAssociato(gestore);
		gestore.getListaSondeAssociate().add(sonda);

		// RELAZIONE CONTROLLO-GESTORE
		centro.getListaGestoriAssociati().add(gestore);
		gestore.setCentroDiControllo(centro);

		List<Allarme> list = new ArrayList<Allarme>();
		list.add(sonda);
		list.add(gestore);
		list.add(centro);
		return list;
	}

}
