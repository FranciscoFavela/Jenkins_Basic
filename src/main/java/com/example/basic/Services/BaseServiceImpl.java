package com.example.basic.Services;


import com.example.basic.Entities.Base;
import com.example.basic.Exeption.ResourceNotFoundException;
import com.example.basic.Repositories.BaseRepository;
import org.springframework.scheduling.annotation.Async;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public abstract class BaseServiceImpl<E extends Base, ID extends Serializable> implements BaseService<E, ID> {
    protected BaseRepository<E, ID> baseRepository;

    public BaseServiceImpl(BaseRepository<E, ID> baseRepository) {
        this.baseRepository = baseRepository;
    }



    @Async("taskExecutor")
    @Transactional
    public CompletableFuture<List<E>> getAlll() {


        return CompletableFuture.completedFuture(baseRepository.findAll());
    }

    @Async("taskExecutor")
    @Transactional
    public CompletableFuture<Optional<E>> findByIdAsync(ID id) throws Exception,ResourceNotFoundException {
        try {

            return CompletableFuture.completedFuture(Optional.of(baseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ID No Encontrado :: "+id))));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Async("taskExecutor")

    @Override
    @Transactional
    public CompletableFuture<List<E>> Getall() {

        return CompletableFuture.completedFuture(baseRepository.findAll());
    }


    @Async("taskExecutor")
    @Transactional
    @Override
    public CompletableFuture<E> update(ID id, E entity) throws Exception,ResourceNotFoundException {
        Optional<E> entityOptional = Optional.of(baseRepository.findById(id)).orElseThrow(() -> new ResourceNotFoundException("ID No Encontrado :: "+id));
        //E entityUpdate = entityOptional.get();
        if (entityOptional.isPresent()) {
            E entityUpdate = entityOptional.get();
        }
        return CompletableFuture.completedFuture(baseRepository.save(entity));
    }

    @Async("taskExecutor")
    @Transactional
    @Override
    public CompletableFuture<E> save(E entity) throws Exception {

        return CompletableFuture.completedFuture(baseRepository.save(entity));
    }


    @Async("taskExecutor")
    @Override
    @Transactional
    public CompletableFuture<Boolean> delete(ID id) throws Exception {
        try {

            if (baseRepository.existsById(id)) {
                baseRepository.deleteById(id);
                return CompletableFuture.completedFuture(true);
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}
