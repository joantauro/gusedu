package com.gusedu.controller;

import com.gusedu.model.Punto;

public class classPunto {
		private Punto punto1;
		private Punto punto2;
		private Punto punto3;
		private Punto punto4;

		
		public classPunto()
		{
			
		}
		
		public classPunto(Punto punto1,Punto punto2,Punto punto3,Punto punto4)
		{
			this.punto1=punto1;
			this.punto2=punto2;
			this.punto3=punto3;
			this.punto4=punto4;
		}
		
		
		public Punto getPunto1() {
			return punto1;
		}

		public void setPunto1(Punto punto1) {
			this.punto1 = punto1;
		}

		public Punto getPunto2() {
			return punto2;
		}

		public void setPunto2(Punto punto2) {
			this.punto2 = punto2;
		}

		public Punto getPunto3() {
			return punto3;
		}

		public void setPunto3(Punto punto3) {
			this.punto3 = punto3;
		}

		public Punto getPunto4() {
			return punto4;
		}

		public void setPunto4(Punto punto4) {
			this.punto4 = punto4;
		}
}
