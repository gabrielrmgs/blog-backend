package com.gabrielsa.repositorys;

import com.gabrielsa.models.Cargo;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
@ApplicationScoped
public class CargoRepository implements PanacheRepository<Cargo>{

}
