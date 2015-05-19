package com.gusedu.controller;

import com.gusedu.model.Par;

public class classPar {
		private Par par1;
		private Par par2;
		private Par par3;
		private Par par4;

		
		public classPar()
		{
			
		}
		
		public classPar(Par par1,Par par2,Par par3,Par par4)
		{
			this.setPar1(par1);
			this.setPar2(par2);
			this.setPar3(par3);
			this.setPar4(par4);
		}

		public Par getPar1() {
			return par1;
		}

		public void setPar1(Par par1) {
			this.par1 = par1;
		}

		public Par getPar2() {
			return par2;
		}

		public void setPar2(Par par2) {
			this.par2 = par2;
		}

		public Par getPar3() {
			return par3;
		}

		public void setPar3(Par par3) {
			this.par3 = par3;
		}

		public Par getPar4() {
			return par4;
		}

		public void setPar4(Par par4) {
			this.par4 = par4;
		}
		
		
		 
}
