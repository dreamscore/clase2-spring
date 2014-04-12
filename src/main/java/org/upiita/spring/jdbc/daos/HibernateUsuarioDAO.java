package org.upiita.spring.jdbc.daos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.upiita.spring.jdbc.entidades.Usuario;

@Component("usuarioDAO")
public class HibernateUsuarioDAO implements UsuarioDAO {

	//ESTA ES UNA CLASE DE UTILERIA DE HIBERNATE
	@Autowired
	private SessionFactory sessionFactory;
	
	public Usuario buscaUsuarioPorId(Integer usuarioId) {

		//Primero creamos una sesion de hibernate
		Session sesion = sessionFactory.openSession();
		//Una vez que tiene la sesion de hibernate deben de abrir una transaccion(conjunto de instrucciones de sql que se ejecuten parte de ese bloque
		sesion.beginTransaction();
		//Inicia la transaccion
		
		
		//Busca por ID en la tabla mapeada por la clase Usuario el renglon cuyo ID sea igual a usuarioId
		//Si no encuentra nada regresa NULL
		Usuario usuario = (Usuario) sesion.get(Usuario.class, usuarioId);
		
		//termina la transaccion
		sesion.getTransaction().commit();
		
		//cerramos la sesion de hibernate
		sesion.close();
		
		return usuario;
	}
	
	public Usuario buscaPorEmail(String email){
		
		//Primero creamos una sesion de hibernate
		Session sesion = sessionFactory.openSession();
		//Una vez que tiene la sesion de hibernate deben de abrir una transaccion(conjunto de instrucciones de sql que se ejecuten parte de ese bloque
		sesion.beginTransaction();
		//Inicia la transaccion
		
		Criteria criterio = sesion.createCriteria(Usuario.class);
		
		criterio.add(Restrictions.eq("email", email));
		
		//SI saben que va a regresar una sola entidad
		Usuario usuario =  (Usuario) criterio.uniqueResult();
		
		//termina la transaccion
		sesion.getTransaction().commit();
		
		//cerramos la sesion de hibernate
		sesion.close();
		
		return usuario;
		
	}

	public void creaUsuario(Usuario usuario) {

				//Primero creamos una sesion de hibernate
				Session sesion = sessionFactory.openSession();
				//Una vez que tiene la sesion de hibernate deben de abrir una transaccion(conjunto de instrucciones de sql que se ejecuten parte de ese bloque
				sesion.beginTransaction();
				//Inicia la transaccion
				
				//Crea un nuevo renglon en la tabla cuyas columnas se llenan con las propiedades de la instancia usuario
				sesion.save(usuario); //Este save es equivalente a un SQL INsert
				
				//termina la transaccion
				sesion.getTransaction().commit();
				
				//cerramos la sesion de hibernate
				sesion.close();	
		
	}
	
	public List<Usuario>obtenPorNombre(String nombre){
		
		//Primero creamos una sesion de hibernate
				Session sesion = sessionFactory.openSession();
				//Una vez que tiene la sesion de hibernate deben de abrir una transaccion(conjunto de instrucciones de sql que se ejecuten parte de ese bloque
				sesion.beginTransaction();
				//Inicia la transaccion
				
				Criteria criterio = sesion.createCriteria(Usuario.class);
				
				//criterio.add(Restrictions.like("nombre","%" + nombre + "%"));
				criterio.add(Restrictions.not(Restrictions.like("nombre","%" + nombre + "%")));
				
				//SI saben que va a regresar una sola entidad
				List<Usuario> usuarios =  criterio.list();
				
				//termina la transaccion
				sesion.getTransaction().commit();
				
				//cerramos la sesion de hibernate
				sesion.close();
				
				return usuarios;
		
	}

}
