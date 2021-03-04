package pe.marcolopez.sistemas.vemoapp.service.generic;

import pe.marcolopez.sistemas.vemoapp.service.exception.ServiceException;

import java.util.List;

public interface GenericService<T> {

    List<T> getAll() throws ServiceException;

    T findById(Long id) throws ServiceException;

    T insert(T t) throws ServiceException;

    T update(T t) throws ServiceException;

    T delete(Long id) throws ServiceException;
}
