package com.gusedu.util;

import java.util.ArrayList;

public class Distritos {

	public static ArrayList<String> distritos;

	public Distritos() {
		distritos = llenarDistritos();
	}

	public ArrayList<String> llenarDistritos() {
		ArrayList<String> result = new ArrayList<>();
		String[] distritos = { "Ancón", "Ate", "Barranco", "Breña",
				"Carabayllo", "Chaclacayo", "Chosica", "Cieneguilla", "Comas",
				"Chorrillos", "El Agustino", "Independencia", "Jesus María",
				"La Molina", "La Victoria", "Lima", "Lince", "Los Olivos",
				"Lurín", "Magalena", "Miraflores", "Pachacámac", "Pucusana",
				"Pueblo Libre", "Puente Piedra", "Punta Hermosa",
				"Punta Negra", "Rímac", "San Bartolo", "San Borja",
				"San Isidro", "San Juan de Lurigancho",
				"San Juan de Miraflores", "San Luis", "San Martín de Porres",
				"San Miguel", "Santa Anita", "Santa María del Mar",
				"Santa Rosa", "Santiago de Surco", "Surquillo",
				"Villa El Salvador", "Villa María del Triunfo" };
		for (String d : distritos) {
			result.add(d);
		}
		return result;
	}

}
