package com.co.estudio.reflection;

import com.co.estudio.reflection.mapperreflection.MapperConReflection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@SpringBootApplication
public class EstudioReflectionApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(EstudioReflectionApplication.class, args);

		ejecutarReflectionSimple();

		ejecutarMapperConReflection();
	}
	private static void ejecutarReflectionSimple() throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
		Persona persona = new Persona();

		// Obtenemos la clase
		Class<?> clazz = persona.getClass();

		// Accedemos a campo privado
		Field campo = clazz.getDeclaredField("nombre");
		campo.setAccessible(true);
		campo.set(persona, "Ana");

		// Invocamos método
		Method metodo = clazz.getDeclaredMethod("saludar");
		metodo.invoke(persona);

		System.out.println("Persona después de Reflection: " + persona);
				/*¿Qué pasó aquí?
		Inspeccionamos la clase Persona.
		Modificamos el campo nombre aunque era private.
		Ejecutamos el método saludar.*/
	}


	private static void ejecutarMapperConReflection() throws Exception {
		PersonaDTO dto = new PersonaDTO();
		dto.nombre = "Luis";
		dto.edad = 28;

		Persona persona = MapperConReflection.map(dto, Persona.class);

		System.out.println("Persona mapeada con Reflection: " + persona);
	}

	}
