package com.example.basic.Services;



import com.example.basic.Entities.Base;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface BaseService<E extends Base, ID extends Serializable> {

    //public List<E> findAll() throws Exception;
    //public E findById(ID id) throws Exception;
    public CompletableFuture<E> save(E entity) throws Exception;
    //public E save(E entity) throws Exception;

    //public E update(ID id, E entity) throws Exception;
    public CompletableFuture<E> update(ID id, E entity) throws Exception;

    //public boolean delete(ID id) throws Exception;
    public CompletableFuture<Boolean> delete(ID id) throws Exception;
    CompletableFuture<List<E>> Getall() throws Exception;
    public CompletableFuture<Optional<E>> findByIdAsync(ID id) throws Exception;
}
