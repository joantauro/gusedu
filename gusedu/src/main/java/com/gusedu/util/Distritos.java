package com.gusedu.util;

import java.util.ArrayList;

public class Distritos {

	public static ArrayList<String> distritos;

	public Distritos() {
		distritos = llenarDistritos();
	}

	public ArrayList<String> llenarDistritos() {
		ArrayList<String> result = new ArrayList<>();
		String[] distritos = { "Anc�n", "Ate", "Barranco", "Bre�a",
				"Carabayllo", "Chaclacayo", "Chosica", "Cieneguilla", "Comas",
				"Chorrillos", "El Agustino", "Independencia", "Jesus Mar�a",
				"La Molina", "La Victoria", "Lima", "Lince", "Los Olivos",
				"Lur�n", "Magalena", "Miraflores", "Pachac�mac", "Pucusana",
				"Pueblo Libre", "Puente Piedra", "Punta Hermosa",
				"Punta Negra", "R�mac", "San Bartolo", "San Borja",
				"San Isidro", "San Juan de Lurigancho",
				"San Juan de Miraflores", "San Luis", "San Mart�n de Porres",
				"San Miguel", "Santa Anita", "Santa Mar�a del Mar",
				"Santa Rosa", "Santiago de Surco", "Surquillo",
				"Villa El Salvador", "Villa Mar�a del Triunfo" };
		for (String d : distritos) {
			result.add(d);
		}
		return result;
	}

}
