
package com.gabrielsa.repositorys;

import com.gabrielsa.models.Macro;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MacroRepository implements PanacheRepository<Macro> {

}
