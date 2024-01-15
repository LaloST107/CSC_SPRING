package centro_soluciones.clicksoft.service.generic;


import centro_soluciones.clicksoft.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

//Se crea una interface Generica que tendrá como parametro un tipo  T ( template)
public interface GenericService<T> {
//Se declaran los nombres de los metodos Generales para cada uno de nuestras ClassImpl
    List<T> findAll() throws ServiceException;
    List<T> findByLikeObject(T t) throws ServiceException;
    T save(T t) throws  ServiceException;
    T update(T t) throws  ServiceException;
    void delete(Integer id) throws ServiceException;
    Optional<T> findById(Integer id) throws ServiceException;



}
