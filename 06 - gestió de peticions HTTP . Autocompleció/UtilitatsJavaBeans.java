package edu.fje.daw2;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;



/**
 * Classe que encapsula algunes utilitats per manipular JavaBeans. Necessita els
 * components de la Apache Commons library: beanutils, collections, i logging.
 * Examina tots els parametres per veure quins lliguen amb les propietats del
 * bean (parametre x, setX de l'objecte) i s'assignen els atributs del bean,
 * fent les conversions de tipus de manera automàtica. En el cas que el format
 * del paràemtre sigui incorrecta, s'assigna cadena buida, zero o false,
 * respectivament, sense llençar cap excepció.
 * @author Sergi Grau
 * @version 2.0 31.10.2013
 */

public class UtilitatsJavaBeans {
	/**
	 * omple un Bean basat en el Map (tipus taula de dispersi�, amb clau/valor).
	 * les claus són els noms de les propietats, i els valors de les propietats (atributs).
	 * la conversió de tipus és automàtica.
	 */
	public static void omplirBean(Persona bean, HttpServletRequest peticio) {
		try {
			BeanUtils.populate(bean, peticio.getParameterMap());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
