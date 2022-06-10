package com.thales.tsn.spif;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Element;


public class Classification {
		private String name = "";
		private String lacv = "";
		private String hierarchy = "";
		
		private static final Logger log = LogManager.getLogger(Classification.class);
		
		public Classification(Element node) { 
			name = node.getAttribute("name");
			lacv = node.getAttribute("lacv");
			hierarchy = node.getAttribute("hierarchy");
			
			if (lacv == null) lacv = "";
			if (hierarchy == null) hierarchy = "";

			log.trace("Classification: {} - lacv: {} - hierarchy: {}", 
					name.equals("")?"no value":name, 
					lacv.equals("")?"no value":lacv, 
					hierarchy.equals("")?"no value":hierarchy);
		} // Classification
		
		public String toString() {
			String result = "Classif: " + name;
			if (lacv.length() > 0) result += (" - lacv: " + lacv);
			if (hierarchy.length() > 0) result += (" - Hierarchy: " + hierarchy);
			return  result;
		} // toString
		
} // class
