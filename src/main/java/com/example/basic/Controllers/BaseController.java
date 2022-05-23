package com.example.basic.Controllers;

import com.example.basic.Entities.Base;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.Serializable;
import java.util.concurrent.CompletableFuture;

public interface BaseController <E extends Base, ID extends Serializable> {
    //public ResponseEntity<?> getAll();
    //public ResponseEntity<?> getOne(@PathVariable ID id);
    //public ResponseEntity<?> save(@RequestBody E entity);
    public CompletableFuture<ResponseEntity> save(@RequestBody E entity) throws Exception;
    //public ResponseEntity<?> update(@PathVariable ID id, @RequestBody E entity);
    public CompletableFuture<ResponseEntity> update(@PathVariable ID id, @RequestBody E entity) throws Exception;
   // public ResponseEntity<?> delete(@PathVariable ID id);
   public CompletableFuture<ResponseEntity> delete(@PathVariable ID id) throws Exception;
    CompletableFuture<ResponseEntity> getAll();
    public CompletableFuture<ResponseEntity> getOneAsync(@PathVariable ID id) throws Exception;
}
