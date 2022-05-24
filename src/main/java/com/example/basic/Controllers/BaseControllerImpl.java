package com.example.basic.Controllers;

import com.example.basic.Entities.Base;
import com.example.basic.Services.BaseServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public abstract class BaseControllerImpl<E extends Base, S extends BaseServiceImpl<E, Long>> implements BaseController<E, Long> {

    @Autowired
    protected S servicio;

   // @CircuitBreaker(name = "getAllCB", fallbackMethod = "fallbackgetAllCB")
    @GetMapping("")
    public CompletableFuture<ResponseEntity> getAll() {
        return servicio.Getall().thenApply(ResponseEntity::ok);
    }

   // @CircuitBreaker(name = "getOneCB", fallbackMethod = "fallbackgetOneCB")
    @GetMapping("/{id}")
    public CompletableFuture<ResponseEntity> getOneAsync(@PathVariable Long id) throws Exception {
        return servicio.findByIdAsync(id).thenApply(ResponseEntity::ok);
    }

  //  @CircuitBreaker(name = "saveCB", fallbackMethod = "fallbacksaveCB")
    @PostMapping("")
    public CompletableFuture<ResponseEntity> save(@RequestBody E entity) throws Exception {
        return servicio.save(entity).thenApply(ResponseEntity::ok);
    }

  //  @CircuitBreaker(name = "updateCB", fallbackMethod = "fallbackupdateCB")
    @PutMapping("/{id}")
    public CompletableFuture<ResponseEntity> update(@PathVariable Long id, @RequestBody E entity) throws Exception {
        return servicio.update(id, entity).thenApply(ResponseEntity::ok);
    }

  //  @CircuitBreaker(name = "deleteCB", fallbackMethod = "fallbackdeleteCB")
    @DeleteMapping("/{id}")
    public CompletableFuture<ResponseEntity> delete(@PathVariable Long id) throws Exception {
            return servicio.delete(id).thenApply(ResponseEntity::ok);

    }

    private ResponseEntity fallbackgetAllCB(RuntimeException e) {
        return new ResponseEntity("getAll no disponible", HttpStatus.OK);
    }

}
