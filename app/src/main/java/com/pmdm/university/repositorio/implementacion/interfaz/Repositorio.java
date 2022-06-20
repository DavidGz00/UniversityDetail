package com.pmdm.university.repositorio.implementacion.interfaz;

import com.pmdm.university.entidad.UniversityDetail;

import java.util.List;
import java.util.Optional;

public interface Repositorio{

    void insert(UniversityDetail universityDetail);
    void update(UniversityDetail universityDetail);
    void delete(UniversityDetail universityDetail);


}
