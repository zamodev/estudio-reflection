package com.co.estudio.reflection.mapperreflection;

import java.lang.reflect.Field;

public class MapperConReflection {

    //public static: método utilitario que puedes invocar sin crear una instancia de MapperUtil.
    //<S, T>: tipos genéricos:
    //
    //S: tipo del objeto fuente (por ejemplo, PersonaDTO).
    //
    //T: tipo del objeto destino (por ejemplo, Persona).
    public static <S, T> T map(S source, Class<T> targetClass) throws Exception {

        //Obtiene el constructor vacío (sin parámetros) de targetClass
        //.newInstance():
        //Crea una nueva instancia de T en tiempo de ejecución.
        //Equivalente a new Persona() si T es Persona, pero de forma dinámica usando Reflection.

            T target = targetClass.getDeclaredConstructor().newInstance();

            //Obtiene todos los campos declarados en la clase destino (targetClass), incluso los privados.
            for(Field field : targetClass.getDeclaredFields()){
                //Permite acceder y modificar campos privados, ya que por defecto Java no permite acceder a campos private
                field.setAccessible(true);
                try {
                    //Buscar y obtener el campo equivalente en el objeto fuente
                    //source.getClass() obtiene la clase del objeto fuente.
                    //.getDeclaredField(field.getName()) busca en source un campo con el mismo nombre que el campo actual de target.
                   //Si lo encuentra:
                    //Permite acceso con .setAccessible(true).
                    //Puedes leer su valor.
                    Field sourceField = source.getClass().getDeclaredField(field.getName());
                    sourceField.setAccessible(true);
                }catch (NoSuchFieldException e){
                    //ignora si el campo no existe en el origen
                }
            }
            return target; // Retorna el objeto mapeado de tipo T con los valores mapeados autoamticamente
    }

}
